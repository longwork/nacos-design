package org.student.appservice.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.student.appservice.UserManagementAppService;
import org.student.dto.PageSelect;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;
import org.student.service.UserManagementService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Long
 * @date 14:15 2020-04-20 周一
 */
@Service
@AllArgsConstructor
public class UserManagementAppServiceImpl implements UserManagementAppService {

    private static final int NAME_LENGTH = 20;

    private final UserManagementService userManagementService;

    @Override
    public List<UserManagement> selectAllUserList() {
        return userManagementService.list();
    }

    @Override
    public List<UserManagement> selectUserByConditionList(String birth1, String birth2, String condition) {
        Wrapper<UserManagement> query = new QueryWrapper<UserManagement>()
                .between("birth", birth1, birth2)
                .and(i -> i.like("name", condition)
                        .or().like("email", condition)
                        .or().like("phone", condition)
                );
        return userManagementService.list(query);
    }

    @Override
    public UserManagement selectUserById(Integer id) {
        return userManagementService.getById(id);
    }

    @Override
    public UserManagement selectUserByFieldNotBirth(String fieldName, String fieldValue) {
        Wrapper<UserManagement> query = new QueryWrapper<UserManagement>().eq(fieldName, fieldValue);
        return userManagementService.getOne(query);
    }

    @Override
    public List<UserManagement> selectUserByBirth(String fieldValue) {
        Wrapper<UserManagement> query = new QueryWrapper<UserManagement>().eq("birth", fieldValue);
        return userManagementService.list(query);
    }

    @Override
    public String insertUser(UserAddEncapsulation userAddEncapsulation) {
        UserManagement userManagement = addUserDtoToUserManagement(userAddEncapsulation);
        String detection = detection(userManagement);
        if (detection != null) {
            return detection;
        }
        return userManagementService.save(userManagement) ? "插入成功,对应的id" + userManagement.getId() : "插入失败";
    }

    @Override
    public String deleteUserById(Integer id) {
        return userManagementService.removeById(id) ? "删除成功" : "删除失败";
    }

    @Override
    public String deleteUserByFieldNameAndValue(String fieldName, String fieldValue) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(fieldName, fieldValue);
        return userManagementService.removeByMap(map) ? "删除成功" : "删除失败";
    }

    @Override
    public String updateUserById(UserUpdateEncapsulation userUpdateEncapsulation) {
        UserManagement userManagement = updateUserDtoToUserManagement(userUpdateEncapsulation);
        Integer id = userManagement.getId();
        if (selectUserById(id) == null) {
            return "数据不存在，修改失败";
        } else {
            String updateDetection = updateDetection(userManagement);
            if (updateDetection != null) {
                return updateDetection;
            }
            return userManagementService.updateById(userManagement) ? "修改成功" : "修改失败";
        }
    }

    @Override
    public IPage<UserManagement> selectPage(PageSelect pages) {
        Long current = pages.getCurrent();
        Long size = pages.getSize();
        Page<UserManagement> page = new Page<>(current, size);
        return userManagementService.page(page);
    }

    /**
     * 将前端传来的dto类转换为entity实体类
     *
     * @param userAddEncapsulation 传入要插入的user
     * @return 返回一个转换的entity实体类
     */
    private UserManagement addUserDtoToUserManagement(UserAddEncapsulation userAddEncapsulation) {
        String name = userAddEncapsulation.getName();
        String email = userAddEncapsulation.getEmail();
        String phone = userAddEncapsulation.getPhone();
        Date date = userAddEncapsulation.getDate();
        LocalDate localDate = dateTimeFormat(date);
        return new UserManagement(name, email, phone, localDate);
    }

    /**
     * 将前端传来的dto类转换为entity实体类
     *
     * @param userUpdateEncapsulation 传入要修改的user
     * @return 返回一个转换的entity实体类
     */
    private UserManagement updateUserDtoToUserManagement(UserUpdateEncapsulation userUpdateEncapsulation) {
        Integer id = userUpdateEncapsulation.getId();
        String name = userUpdateEncapsulation.getName();
        String email = userUpdateEncapsulation.getEmail();
        String phone = userUpdateEncapsulation.getPhone();
        Date date = userUpdateEncapsulation.getDate();
        LocalDate localDate = dateTimeFormat(date);
        return new UserManagement(id, name, email, phone, localDate);
    }

    /**
     * 将Date类型转化为LocalDate类型
     *
     * @param date 传入的生日
     * @return 返回一个LocalDate类型的生日
     */
    private LocalDate dateTimeFormat(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * 检测数据是否合格
     *
     * @param userManagement 要插入的UserManagement
     * @return 返回不符合的语句
     */
    private String detection(UserManagement userManagement) {
        String name = userManagement.getName();
        String email = userManagement.getEmail();
        String phone = userManagement.getPhone();
        if (userManagement.nameDetection()) {
            return "姓名格式不能用特殊符号开头";
        }
        if (name.length() > NAME_LENGTH) {
            return "姓名长度太长";
        }
        if (userManagementService.nameRepetition(name)) {
            return "姓名已存在";
        }
        if (userManagement.emailDetection()) {
            return "邮箱格式不对";
        }
        if (userManagementService.emailRepetition(email)) {
            return "邮箱已存在";
        }
        if (userManagement.phoneDetection()) {
            return "手机号码格式不对";
        }
        if (userManagementService.phoneRepetition(phone)) {
            return "手机号码重复";
        }
        if (userManagement.birthDetection()) {
            return "输入的生日超过当前时间";
        }
        return null;
    }

    /**
     * 检测修改数据是否合格
     *
     * @param userManagement 要修改的UserManagement
     * @return 返回不符合的语句
     */
    private String updateDetection(UserManagement userManagement) {
        Integer id = userManagement.getId();
        String name = userManagement.getName();
        String email = userManagement.getEmail();
        String phone = userManagement.getPhone();
        UserManagement u = userManagementService.getById(id);

        if (name.length() > NAME_LENGTH) {
            return "姓名长度太长";
        }
        if (u.nameDetection()) {
            return "姓名格式不能用特殊符号开头";
        }
        if (userManagementService.nameRepetition(name) && !name.equals(u.getName())) {
            return "姓名已存在";
        }
        if (u.emailDetection()) {
            return "邮箱格式不对";
        }
        if (userManagementService.emailRepetition(email) && !email.equals(u.getEmail())) {
            return "邮箱已存在";
        }
        if (u.phoneDetection()) {
            return "手机号码格式不对";
        }
        if (userManagementService.phoneRepetition(phone) && !phone.equals(u.getPhone())) {
            return "手机号码重复";
        }
        if (u.birthDetection()) {
            return "输入的生日超过当前时间";
        }
        return null;
    }
}
