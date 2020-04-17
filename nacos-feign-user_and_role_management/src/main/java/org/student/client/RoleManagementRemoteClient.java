package org.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.student.entity.RoleManagement;
import org.student.hystrix.RoleManagementRemoteHystrix;

import java.util.List;

/**
 * @author Administrator
 */
@FeignClient(name = "nacos-rolemanagement", fallback = RoleManagementRemoteHystrix.class)
public interface RoleManagementRemoteClient {
    /**
     * 查询所有的role角色
     *
     * @return 返回所有的role角色的List集合
     */
    @GetMapping("/select-all-role-list")
    List<RoleManagement> selectAllRoleList();

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return id查询到的角色
     */
    @GetMapping("/select-role-by-id")
    RoleManagement selectRoleById(@RequestParam("id") Integer id);

    /**
     * 通过fieldName和fieldValue值来查询
     *
     * @param fieldName  传入的fieldName
     * @param fieldValue 传入的fieldValue
     * @return 字段查到的角色
     */
    @GetMapping("/select-role-by-fieldname-and-fieldvalue")
    RoleManagement selectRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                 @RequestParam("fieldValue") String fieldValue);

    /**
     * 添加数据
     *
     * @param role 要添加的角色
     * @return 返回结果字符串
     */
    @PostMapping("/insert-role")
    String insertRole(@RequestBody RoleManagement role);

    /**
     * 通过Id删除数据
     *
     * @param id 传入的id
     * @return 返回结果字符串
     */
    @DeleteMapping("/delete-role")
    String deleteRole(@RequestParam("id") Integer id);

    /**
     * 通过字段来删除数据
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回结果字符串
     */
    @DeleteMapping("/delete-role-by-fieldname-and-fieldvalue")
    String deleteRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                         @RequestParam("fieldValue") String fieldValue);

    /**
     * 修改角色
     *
     * @param role 要修改的role
     * @return 返回结果字符串
     */
    @PutMapping("/update-role-by-id")
    String updateRoleById(@RequestBody RoleManagement role);

}
