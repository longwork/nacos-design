package org.student.appservice;

import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Long
 * @date 14:15 2020-04-20 周一
 */
public interface UserManagementAppService {
    /**
     * 将前端传来的dto类转换为entity实体类
     *
     * @param user 传入要修改的user
     * @return 返回一个转换的entity实体类
     */
    UserManagement entityTransaction(UserUpdateEncapsulation user);

    /**
     * 将前端传来的dto类转换为entity实体类
     *
     * @param user 传入要插入的user
     * @return 返回一个转换的entity实体类
     */
    UserManagement entityTransaction(UserAddEncapsulation user);

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @param name 传入的用户名
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    boolean nameDetection(String name);

    /**
     * 检测传入的Name重复
     *
     * @param name 传入的Name
     * @return true为数据库没有这个值，false代表数据库中存在这个值
     */
    boolean nameRepetition(String name);

    /**
     * 检测传入的字符串是否为邮箱
     *
     * @param email 传入的字符串
     * @return true代表传入的是邮箱, false代表传入的不是邮箱
     */
    boolean emailDetection(String email);

    /**
     * 检测邮箱重复
     *
     * @param email 传入的邮箱
     * @return true代表重复，false不重复
     */
    boolean emailRepetition(String email);

    /**
     * 检测传入的字符串是否为手机号码
     *
     * @param phone 传入的手机号
     * @return true代表传入的是手机号, false代表传入的不是手机号
     */
    boolean phoneDetection(String phone);

    /**
     * 检测手机号重复
     *
     * @param phone 传入的手机号
     * @return true代表重复，false不重复
     */
    boolean phoneRepetition(String phone);

    /**
     * 判断传入的字符串转化为时间之后是否是在当前时间之前
     *
     * @param localDate 传入的生日
     * @return 是不是在当前时间之前
     */
    boolean birthDetection(LocalDate localDate);

    /**
     * 将Date类型转化为LocalDate类型
     *
     * @param date 传入的生日
     * @return 返回一个LocalDate类型的生日
     */
    LocalDate dateTimeFormat(Date date);

    /**
     * 检测数据是否合格
     *
     * @param u 要插入的UserManagement
     * @return 返回不符合的语句
     */
    String detection(UserManagement u);

    /**
     * 检测修改数据是否合格
     *
     * @param id        传入的id
     * @param name      传入的name
     * @param email     传入的email
     * @param phone     传入的phone
     * @param localDate 传入的localDate
     * @return 返回不符合的语句
     */
    String updateDetection(Integer id, String name, String email, String phone, LocalDate localDate);
}
