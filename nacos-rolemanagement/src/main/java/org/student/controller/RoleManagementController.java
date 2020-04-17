package org.student.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.student.dto.RoleAddEncapsulation;
import org.student.entity.RoleManagement;
import org.student.service.RoleManagementService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class RoleManagementController {
    @Value("${nacos.config}")
    String config;

    /**
     * 测试config
     *
     * @return config对应的命名空间中的值
     */
    @GetMapping("/config")
    public String getConfig() {
        return config;
    }

    @Resource
    RoleManagementService roleService;

    /**
     * 查询所有的role角色
     *
     * @return 返回所有的role角色的List集合
     */
    @GetMapping("/select-all-role-list")
    public List<RoleManagement> selectAllRoleList() {
        return roleService.selectAllRoleList();
    }

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return id查询到的角色
     */
    @GetMapping("/select-role-by-id")
    public RoleManagement selectRoleById(@RequestParam("id") Integer id) {
        return roleService.selectRoleById(id);
    }

    /**
     * 通过fieldName和fieldValue值来查询
     *
     * @param fieldName  传入的fieldName
     * @param fieldValue 传入的fieldValue
     * @return 字段查到的角色
     */
    @GetMapping("/select-role-by-fieldname-and-fieldvalue")
    public RoleManagement selectRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                        @RequestParam("fieldValue") String fieldValue) {
        return roleService.selectRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    /**
     * 添加数据
     *
     * @param role 要添加的角色
     * @return 是否成功
     */
    @PostMapping("/insert-role")
    public String insertRole(@RequestBody RoleAddEncapsulation role) {
        return roleService.insertRole(role);
    }

    /**
     * 通过Id删除数据
     *
     * @param id 传入的id
     * @return 删除成功与否的语句
     */
    @DeleteMapping("/delete-role")
    public String deleteRole(@RequestParam("id") Integer id) {
        return roleService.deleteRole(id);
    }

    /**
     * 通过字段来删除数据
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 删除成功与否的语句
     */
    @DeleteMapping("/delete-role-by-fieldname-and-fieldvalue")
    public String deleteRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                @RequestParam("fieldValue") String fieldValue) {
        return roleService.deleteRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    /**
     * 修改角色
     *
     * @param role 要修改的role
     * @return 修改之后的描述
     */
    @PutMapping("/update-role-by-id")
    public String updateRoleById(@RequestBody RoleManagement role) {
        return roleService.updateRoleById(role);
    }

}
