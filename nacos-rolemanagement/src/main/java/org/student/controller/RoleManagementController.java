package org.student.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.student.appservice.RoleManagementAppService;
import org.student.dto.RoleAddEncapsulation;
import org.student.dto.RoleUpdateEncapsulation;
import org.student.entity.RoleManagement;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@AllArgsConstructor
public class RoleManagementController {
    private final RoleManagementAppService roleManagementAppService;

    /**
     * 测试config
     *
     * @return config对应的命名空间中的值
     */
    @GetMapping("/config")
    public String getConfig(@Value("${nacos.config}") String config) {
        return config;
    }

    /**
     * 查询所有的role角色
     *
     * @return 返回所有的role角色的List集合
     */
    @GetMapping("/select-all-role-list")
    public List<RoleManagement> selectAllRoleList() {
        return roleManagementAppService.selectAllRoleList();
    }

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return id查询到的角色
     */
    @GetMapping("/select-role-by-id")
    public RoleManagement selectRoleById(@RequestParam("id") Integer id) {
        return roleManagementAppService.selectRoleById(id);
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
        return roleManagementAppService.selectRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    /**
     * 添加数据
     *
     * @param roleAddEncapsulation 要添加的角色
     * @return 是否成功
     */
    @PostMapping("/insert-role")
    public String insertRole(@RequestBody RoleAddEncapsulation roleAddEncapsulation) {
        return roleManagementAppService.insertRole(roleAddEncapsulation);
    }

    /**
     * 通过Id删除数据
     *
     * @param id 传入的id
     * @return 删除成功与否的语句
     */
    @DeleteMapping("/delete-role")
    public String deleteRole(@RequestParam("id") Integer id) {
        return roleManagementAppService.deleteRole(id);
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
        return roleManagementAppService.deleteRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    /**
     * 修改角色
     *
     * @param roleUpdateEncapsulation 要修改的role
     * @return 修改之后的描述
     */
    @PutMapping("/update-role-by-id")
    public String updateRoleById(@RequestBody RoleUpdateEncapsulation roleUpdateEncapsulation) {
        return roleManagementAppService.updateRoleById(roleUpdateEncapsulation);
    }

}
