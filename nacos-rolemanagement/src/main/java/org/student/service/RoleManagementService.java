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


}
