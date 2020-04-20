package org.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.student.dto.FieldCollection;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.entity.UserAndRole;

import java.util.List;

/**
 * @author Administrator
 */
@FeignClient(name = "nacos-userAndrole")
public interface UserAndRoleRemoteClient {
    /**
     * 查询UserManagement和RoleManagement关联表的数据
     *
     * @return 返回关联表的List集合数据
     */
    @GetMapping("/select-all-user-role-list")
    List<UserAndRole> selectAllUserRoleList();

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的关联数据
     */
    @GetMapping("/select-user-role-by-id")
    UserAndRole selectUserRoleById(@RequestParam("id") Integer id);

    /**
     * 通过fieldName和fieldValue值来查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回通过fieldName和fieldValue值查询的RoleManagement
     */
    @GetMapping("/select-user-role-by-fieldname-and-fieldvalue")
    List<UserAndRole> selectUserRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                        @RequestParam("fieldValue") Integer fieldValue);

    /**
     * 插入数据
     *
     * @param ur 想要插入的UserAndRole
     * @return 返回结果字符串
     */
    @PostMapping("/insert-user-role")
    String insertUserRole(@RequestBody UserAndRoleAddEncapsulation ur);

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果字符串
     */
    @DeleteMapping("/delete-user-role")
    String deleteUserRole(@RequestParam("id") Integer id);

    /**
     * 通过fieldName和fieldValue删除数据
     *
     * @param fieldCollections 传入要删除的结果集
     * @return 返回结果字符串
     */
    @DeleteMapping("/delete-user-role-by-fieldname-and-fieldvalue")
    String deleteUserRoleByFieldNameAndValue(@RequestBody FieldCollection fieldCollections);

    /**
     * 通过Id修改数据
     *
     * @param ur 要更新的UserAndRole
     * @return 返回结果字符串
     */
    @PutMapping("/update-user-role")
    String updateUserRole(@RequestBody UserAndRole ur);

}
