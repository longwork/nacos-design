package org.student.appservice.impl;

import org.student.appservice.UserManagementAppService;
import org.student.entity.UserManagement;
import org.student.service.UserManagementService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Long
 * @date 14:15 2020-04-20 周一
 */
public class UserManagementAppServiceImpl implements UserManagementAppService {

    private static final int NAME_LENGTH = 20;
    @Resource
    UserManagementService uService;

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
        List<UserManagement> userManagement = uService.selectUserByFileNameAndValue("name", name);
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
        List<UserManagement> userManagement = uService.selectUserByFileNameAndValue("email", email);
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
        List<UserManagement> userManagement = uService.selectUserByFileNameAndValue("phone", phone);
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
     * @param name      传入的name
     * @param email     传入的email
     * @param phone     传入的phone
     * @param localDate 传入的localDate
     * @return 返回不符合的语句
     */
    @Override
    public String detection(String name, String email, String phone, LocalDate localDate) {
        if (nameDetection(name)) {
            return "姓名格式不能用特殊符号开头";
        }
        if (name.length() > NAME_LENGTH) {
            return "姓名长度太长";
        }
        if (nameRepetition(name)) {
            return "姓名已存在";
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
        UserManagement u = uService.selectUserById(id);
        if (name.length() > NAME_LENGTH) {
            return "姓名长度太长";
        }
        if (nameDetection(name)) {
            return "姓名格式不能用特殊符号开头";
        }
        if (nameRepetition(name) && !name.equals(u.getName())) {
            return "姓名已存在";
        }
        if (emailDetection(email)) {
            return "邮箱格式不对";
        }
        if (emailRepetition(email) && !email.equals(u.getEmail())) {
            return "邮箱已存在";
        }
        if (phoneDetection(phone)) {
            return "手机号码格式不对";
        }
        if (phoneRepetition(phone) && !phone.equals(u.getPhone())) {
            return "手机号码重复";
        }
        if (birthDetection(localDate)) {
            return "输入的生日超过当前时间";
        }
        return null;
    }
}
