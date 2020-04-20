package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.student.appservice.UserManagementAppService;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;
import org.student.mapper.UserManagementMapper;
import org.student.service.UserManagementService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Resource
    UserManagementMapper userMapper;

    @Resource
    UserManagementAppService uAppService;

    /**
     * 查询全部的UserManagement
     *
     * @return List集合，里面为所有的UserManagement
     */
    @Override
    public List<UserManagement> selectAllUserList() {
        return userMapper.selectList(null);
    }

    /**
     * 通过传入的条件查询
     *
     * @param birth1    第一个生日，用String类型传入数据库(条件1)
     * @param birth2    第二个生日，用String类型传入数据库(条件2)
     * @param condition 需要满足的条件(条件3)
     * @return List集合，返回所有满足条件的
     */
    @Override
    public List<UserManagement> selectUserByConditionList(String birth1, String birth2, String condition) {
        return userMapper.selectList(
                new QueryWrapper<UserManagement>()
                        .between("birth", birth1, birth2)
                        .and(i -> i.like("name", condition)
                                .or().like("email", condition)
                                .or().like("phone", condition)
                        )
        );
    }

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的UserManagement
     */
    @Override
    public UserManagement selectUserById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 通过FieldName和FieldValue值来查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回通过FieldName和FieldValue值查询的UserManagement
     */
    @Override
    public List<UserManagement> selectUserByFileNameAndValue(String fieldName, String fieldValue) {
        return userMapper.selectList(
                new QueryWrapper<UserManagement>().eq(fieldName, fieldValue)
        );
    }

    /**
     * 插入数据
     *
     * @param u 想要插入的User
     * @return 返回结果的字符串
     */
    @Override
    public String insertUser(UserAddEncapsulation u) {
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        Date date = u.getDate();
        LocalDate localDate = uAppService.dateTimeFormat(date);
        String detection = uAppService.detection(name, email, phone, localDate);
        if (detection != null) {
            return detection;
        }
        UserManagement user = new UserManagement(name, email, phone, localDate);
        int insert = userMapper.insert(user);
        return insert > 0 ? "插入成功" : "插入失败";
    }

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果的字符串
     */
    @Override
    public String deleteUserById(Integer id) {
        //先进行查询是否存在
        UserManagement userManagement = userMapper.selectById(id);
        //不存在
        if (userManagement == null) {
            return "数据不存在，删除失败";
        } else {
            int delete = userMapper.deleteById(id);
            return delete > 0 ? "删除成功" : "删除失败";
        }
    }


    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回结果的字符串
     */
    @Override
    public String deleteUserByFieldNameAndValue(String fieldName, String fieldValue) {
        //先进行查询是否存在
        List<UserManagement> userManagements = selectUserByFileNameAndValue(fieldName, fieldValue);
        //如果不存在，则不进行删除
        if (userManagements.isEmpty()) {
            return "数据不存在，删除失败";
        } else {
            Map<String, Object> map = new HashMap<>(1);
            //如果这里不放入FieldName和FieldValue，会删除数据库所有的数据
            map.put(fieldName, fieldValue);
            int delete = userMapper.deleteByMap(map);
            //如果删除成功就返回1，失败返回0
            return delete > 0 ? "删除成功" : "删除失败";
        }
    }

    /**
     * 通过Id修改数据
     *
     * @param u 要更新的User
     * @return 返回结果的字符串
     */
    @Override
    public String updateUserById(UserUpdateEncapsulation u) {
        Integer id = u.getId();
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        Date date = u.getDate();
        LocalDate localDate = uAppService.dateTimeFormat(date);
        if (selectUserById(id) == null) {
            return "数据不存在，修改失败";
        } else {
            String updateDetection = uAppService.updateDetection(id, name, email, phone, localDate);
            if (updateDetection != null) {
                return updateDetection;
            }
            UserManagement user = new UserManagement(id, name, email, phone, localDate);
            int update = userMapper.updateById(user);
            return update > 1 ? "修改成功" : "修改失败";
        }
    }


}
