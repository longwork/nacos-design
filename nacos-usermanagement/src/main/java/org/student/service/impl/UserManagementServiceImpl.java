package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.student.entity.UserEncapsulation;
import org.student.entity.UserManagement;
import org.student.mapper.UserManagementMapper;
import org.student.service.UserManagementService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    private static final int NAME_LENGTH = 20;

    @Resource
    UserManagementMapper userMapper;

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
    public String insertUser(UserEncapsulation u) {
        Integer id = u.getId();
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        Date date = u.getDate();
        LocalDate localDate = dateTimeFormat(date);
        String detection = detection(id, name, email, phone, localDate);
        if (detection != null) {
            return detection;
        }
        UserManagement user = new UserManagement(id, name, email, phone, localDate);
        int insert = userMapper.insert(user);
        return insert == 1 ? "插入成功" : "插入失败";
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
            return delete == 1 ? "删除成功" : "删除失败";
        }
    }


    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回数值(1代表成功, 0代表失败, 1000代表不存在算删除失败)
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
            return delete == 1 ? "删除成功" : "删除失败";
        }
    }

    /**
     * 通过Id修改数据
     *
     * @param u 要更新的User
     * @return 返回数值(1代表修改成功, 0代表修改失败)
     */
    @Override
    public String updateUserById(UserEncapsulation u) {
        Integer id = u.getId();
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        Date date = u.getDate();
        LocalDate localDate = dateTimeFormat(date);
        if (selectUserById(id) == null) {
            String detection = detection(id, name, email, phone, localDate);
            if (detection != null) {
                return detection;
            }
            UserManagement user = new UserManagement(id, name, email, phone, localDate);
            int insert = userMapper.insert(user);
            return insert == 1 ? "数据不存在，已插入" : "插入失败";
        } else {
            String updateDetection = updateDetection(id, name, email, phone, localDate);
            if (updateDetection != null) {
                return updateDetection;
            }
            UserManagement user = new UserManagement(id, name, email, phone, localDate);
            int update = userMapper.updateById(user);
            return update == 1 ? "修改成功" : "修改失败";
        }
    }

    /**
     * 检测Id重复
     *
     * @param id 传入的id
     * @return true重复，false不重复
     */
    @Override
    public boolean idDetection(Integer id) {
        UserManagement userManagement = selectUserById(id);
        return userManagement != null;
    }

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @param name 传入的用户名
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    @Override
    public boolean nameDetection(String name) {
        String pattern = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(name);
        return !m.matches();
    }

    /**
     * 检测传入的Name重复
     *
     * @param name 传入的Name
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    @Override
    public boolean nameRepetition(String name) {
        List<UserManagement> userManagement = selectUserByFileNameAndValue("name", name);
        return !userManagement.isEmpty();
    }

    /**
     * 检测传入的字符串是否为邮箱
     *
     * @param email 传入的字符串
     * @return true代表传入的是邮箱, false代表传入的不是邮箱
     */
    @Override
    public boolean emailDetection(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    /**
     * 检测邮箱重复
     *
     * @param email 传入的邮箱
     * @return true代表重复，false不重复
     */
    @Override
    public boolean emailRepetition(String email) {
        List<UserManagement> userManagement = selectUserByFileNameAndValue("email", email);
        return !userManagement.isEmpty();
    }

    /**
     * 检测传入的字符串是否为手机号码
     *
     * @param phone 传入的手机号
     * @return true代表传入的是手机号, false代表传入的不是手机号
     */
    @Override
    public boolean phoneDetection(String phone) {
        String pattern = "0?(13|14|15|18|17)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(phone);
        return !m.matches();
    }

    /**
     * 检测手机号重复
     *
     * @param phone 传入的手机号
     * @return true代表重复，false不重复
     */
    @Override
    public boolean phoneRepetition(String phone) {
        List<UserManagement> userManagement = selectUserByFileNameAndValue("phone", phone);
        return !userManagement.isEmpty();
    }

    /**
     * 判断传入的字符串转化为时间之后是否是在当前时间之前
     *
     * @param ldt 传入的生日
     * @return 是不是在当前时间之前
     */
    @Override
    public boolean birthDetection(LocalDate ldt) {
        LocalDate localDate = LocalDate.now();
        int compare = localDate.compareTo(ldt);
        return compare <= 0;
    }

    /**
     * 将Date类型转化为LocalDate类型
     *
     * @param date 传入的生日
     * @return 返回一个LocalDate类型的生日
     */
    @Override
    public LocalDate dateTimeFormat(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * 检测数据是否合格
     *
     * @param id        传入的id
     * @param name      传入的name
     * @param email     传入的email
     * @param phone     传入的phone
     * @param localDate 传入的localDate
     * @return 返回不符合的语句
     */
    @Override
    public String detection(Integer id, String name, String email, String phone, LocalDate localDate) {
        if (idDetection(id)) {
            return "ID主键唯一，请换一个ID";
        }
        if (nameDetection(name)) {
            return "姓名格式不能用特殊符号开头";
        }
        if (nameRepetition(name)) {
            return "姓名已存在";
        }
        if (name.length() > NAME_LENGTH) {
            return "姓名长度太长";
        }
        if (emailDetection(email)) {
            return "邮箱格式不对";
        }
        if (emailRepetition(email)) {
            return "邮箱已存在";
        }
        if (phoneDetection(phone)) {
            return "手机号码格式不对";
        }
        if (phoneRepetition(phone)) {
            return "手机号码重复";
        }
        if (birthDetection(localDate)) {
            return "输入的生日超过当前时间";
        }
        return null;
    }

    /**
     * 检测修改数据是否合格
     *
     * @param name      传入的name
     * @param email     传入的email
     * @param phone     传入的phone
     * @param localDate 传入的localDate
     * @return 返回不符合的语句
     */
    @Override
    public String updateDetection(Integer id, String name, String email, String phone, LocalDate localDate) {
        UserManagement u = selectUserById(id);
        List<UserManagement> name1 = selectUserByFileNameAndValue("name", name);
        List<UserManagement> email1 = selectUserByFileNameAndValue("email", email);
        List<UserManagement> phone1 = selectUserByFileNameAndValue("phone", phone);

        if (!name1.isEmpty() && !name1.get(0).getName().equals(u.getName())) {
            return "姓名已存在";
        }
        if (name.length() > NAME_LENGTH) {
            return "姓名长度太长";
        }
        if (nameDetection(name)) {
            return "姓名格式不能用特殊符号开头";
        }
        if (!email.isEmpty() && !email1.get(0).getEmail().equals(u.getEmail())) {
            return "邮箱已存在";
        }
        if (emailDetection(email)) {
            return "邮箱格式不对";
        }
        if (!phone1.isEmpty() && !phone1.get(0).getPhone().equals(u.getPhone())) {
            return "手机号码重复";
        }
        if (phoneDetection(phone)) {
            return "手机号码格式不对";
        }
        if (birthDetection(localDate)) {
            return "输入的生日超过当前时间";
        }
        return null;
    }
}
