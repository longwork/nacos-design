package org.student.service;

import org.student.entity.RoleManagement;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoleManagementService {
    /**
     * 查询所有的RoleManagement
     *
     * @return List集合，里面为所有的RoleManagement
     */
    List<RoleManagement> selectAllRoleList();

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的RoleManagement
     */
    RoleManagement selectRoleById(Integer id);

    /**
     * 通过FieldName和FieldValue值来查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回通过FieldName和FieldValue值查询的RoleManagement
     */
    RoleManagement selectRoleByFieldNameAndValue(String fieldName, String fieldValue);

    /**
     * 插入数据
     *
     * @param role 想要插入的Role
     * @return 返回结果字符串
     */
    String insertRole(RoleManagement role);

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果字符串
     */
    String deleteRole(Integer id);

    /**
     * 通过fieldName和fieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回结果字符串
     */
    String deleteRoleByFieldNameAndValue(String fieldName, String fieldValue);

    /**
     * 通过Id修改数据
     *
     * @param role 要更新的Role
     * @return 返回结果字符串
     */
    String updateRoleById(RoleManagement role);

    /**
     * 检测Id重复
     *
     * @param id 传入的id
     * @return true重复，false不重复
     */
    boolean idDetection(Integer id);

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @param roleName 传入的用户名
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    boolean roleNameDetection(String roleName);

    /**
     * 检测用户名是否存在
     *
     * @param name 传入的Name
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    boolean roleNameRepetition(String name);

    /**
     * 检测描述重复
     *
     * @param roleDescribe 传入的roleDescribe
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    boolean roleDescribeRepetition(String roleDescribe);

    /**
     * 检测属性合格
     *
     * @param id           传入的id
     * @param roleName     传入的roleName
     * @param roleDescribe 传入的roleDescribe
     * @return 返回不符合的语句
     */
    String detection(Integer id, String roleName, String roleDescribe);

    /**
     * 检测修改数据是否合格
     *
     * @param id           传入的id
     * @param roleName     传入的roleName
     * @param roleDescribe 传入的roleDescribe
     * @return 返回不符合的语句
     */
    String updateDetection(Integer id, String roleName, String roleDescribe);
}
