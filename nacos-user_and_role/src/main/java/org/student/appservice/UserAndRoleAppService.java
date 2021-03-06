package org.student.appservice;

import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.dto.UserAndRoleUpdateEncapsulation;
import org.student.entity.UserAndRole;

import java.util.List;

/**
 * @author Long
 * @date 15:36 2020-04-20 周一
 */
public interface UserAndRoleAppService {

    /**
     * 查询UserManagement和RoleManagement关联表的数据
     *
     * @return 返回关联表的List集合数据
     */
    List<UserAndRole> selectAllUserRoleList();

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的关联数据
     */
    UserAndRole selectUserRoleById(Integer id);

    /**
     * 插入数据
     *
     * @param userAndRoleAddEncapsulation 想要插入的UserAndRole
     * @return 返回结果的字符串
     */
    String insertUserRole(UserAndRoleAddEncapsulation userAndRoleAddEncapsulation);

    /**
     * 通过fieldName和fieldValue值来查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回通过fieldName和fieldValue值查询的RoleManagement
     */
    List<UserAndRole> selectUserRoleByFieldNameAndValue(String fieldName, Integer fieldValue);


    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果的字符串
     */
    String deleteUserRole(Integer id);

    /**
     * 通过fieldName和fieldValue删除数据
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回结果的字符串
     */
    String deleteUserRoleByFieldNameAndValue(String fieldName, Integer fieldValue);

    /**
     * 通过Id修改数据
     *
     * @param userAndRoleUpdateEncapsulation 要更新的UserAndRole
     * @return 返回结果的字符串
     */
    String updateUserRole(UserAndRoleUpdateEncapsulation userAndRoleUpdateEncapsulation);

}
