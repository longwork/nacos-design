package org.student.service;

import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public interface UserManagementService {
    /**
     * 查询全部的UserManagement
     *
     * @return List集合，里面为所有的UserManagement
     */
    List<UserManagement> selectAllUserList();

    /**
     * 通过传入的条件查询
     *
     * @param birth1    第一个生日，用String类型传入数据库(条件1)
     * @param birth2    第二个生日，用String类型传入数据库(条件2)
     * @param condition 需要满足的条件(条件3)
     * @return List集合，返回所有满足条件的
     */
    List<UserManagement> selectUserByConditionList(String birth1, String birth2, String condition);

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的UserManagement
     */
    UserManagement selectUserById(Integer id);

    /**
     * 通过FieldName和FieldValue值来查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回通过FieldName和FieldValue值查询的UserManagement
     */
    List<UserManagement> selectUserByFileNameAndValue(String fieldName, String fieldValue);

    /**
     * 插入数据
     *
     * @param u 想要插入的User
     * @return 返回结果的字符串
     */
    String insertUser(UserAddEncapsulation u);

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果的字符串
     */
    String deleteUserById(Integer id);

    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回结果的字符串
     */
    String deleteUserByFieldNameAndValue(String fieldName, String fieldValue);

    /**
     * 通过Id修改数据
     *
     * @param u 要更新的User
     * @return 返回结果的字符串
     */
    String updateUserById(UserUpdateEncapsulation u);

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
     * @param name      传入的name
     * @param email     传入的email
     * @param phone     传入的phone
     * @param localDate 传入的localDate
     * @return 返回不符合的语句
     */
    String detection(String name, String email, String phone, LocalDate localDate);

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
