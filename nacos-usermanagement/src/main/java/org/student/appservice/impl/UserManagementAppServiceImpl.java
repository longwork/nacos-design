package org.student.appservice.impl;

import org.springframework.stereotype.Service;
import org.student.appservice.UserManagementAppService;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
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
@Service
public class UserManagementAppServiceImpl implements UserManagementAppService {

    private static final int NAME_LENGTH = 20;
    @Resource
    UserManagementService uService;

    @Override
    public UserManagement entityTransaction(UserUpdateEncapsulation u) {
        Integer id = u.getId();
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        Date date = u.getDate();
        LocalDate localDate = dateTimeFormat(date);
        return new UserManagement(id, name, email, phone, localDate);
    }

    @Override
    public UserManagement entityTransaction(UserAddEncapsulation u) {
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        Date date = u.getDate();
        LocalDate localDate = dateTimeFormat(date);
        return new UserManagement(name, email, phone, localDate);
    }

    @Override
    public boolean nameDetection(String name) {
        String pattern = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(name);
        return !m.matches();
    }

    @Override
    public boolean nameRepetition(String name) {
        List<UserManagement> userManagement = uService.selectUserByFileNameAndValue("name", name);
        return !userManagement.isEmpty();
    }


    @Override
    public boolean emailDetection(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    @Override
    public boolean emailRepetition(String email) {
        List<UserManagement> userManagement = uService.selectUserByFileNameAndValue("email", email);
        return !userManagement.isEmpty();
    }

    @Override
    public boolean phoneDetection(String phone) {
        String pattern = "0?(13|14|15|18|17)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(phone);
        return !m.matches();
    }

    @Override
    public boolean phoneRepetition(String phone) {
        List<UserManagement> userManagement = uService.selectUserByFileNameAndValue("phone", phone);
        return !userManagement.isEmpty();
    }

    @Override
    public boolean birthDetection(LocalDate ldt) {
        LocalDate localDate = LocalDate.now();
        int compare = localDate.compareTo(ldt);
        return compare <= 0;
    }

    @Override
    public LocalDate dateTimeFormat(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    @Override
    public String detection(UserManagement u) {
        String name = u.getName();
        String email = u.getEmail();
        String phone = u.getPhone();
        LocalDate localDate = u.getBirth();
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
