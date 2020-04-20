package org.student.service;

import org.student.entity.UserManagement;

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
    String insertUser(UserManagement u);

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
    String updateUserById(UserManagement u);

}
