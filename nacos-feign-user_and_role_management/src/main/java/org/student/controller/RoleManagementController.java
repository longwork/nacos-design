package org.student.controller;

import org.springframework.web.bind.annotation.*;
import org.student.client.RoleManagementRemoteClient;
import org.student.client.UserAndRoleRemoteClient;
import org.student.dto.RoleAddEncapsulation;
import org.student.dto.RoleUpdateEncapsulation;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.entity.RoleManagement;
import org.student.entity.UserAndRole;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/role-feign")
public class RoleManagementController {

    @Resource
    RoleManagementRemoteClient remoteClient;
    @Resource
    UserAndRoleRemoteClient urRemoteClient;

    @GetMapping("/select-all-role-list")
    public List<RoleManagement> selectAllRoleList() {
        return remoteClient.selectAllRoleList();
    }

    @GetMapping("/select-role-by-id")
    public RoleManagement selectRoleById(@RequestParam("id") Integer id) {
        return remoteClient.selectRoleById(id);
    }

    @GetMapping("/select-role-by-fieldname-and-fieldvalue")
    public RoleManagement selectRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                        @RequestParam("fieldValue") String fieldValue) {
        return remoteClient.selectRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    @PostMapping("/insert-role")
    public String insertRole(@RequestBody RoleAddEncapsulation re) {
        String roleName = re.getRoleName();
        String roleDescribe = re.getRoleDescribe();
        List<Integer> userId = re.getUserId();
        RoleManagement role = new RoleManagement(roleName, roleDescribe);
        String s1 = remoteClient.insertRole(role);
        String s2 = null;
        for (Integer integer : userId) {
            RoleManagement r = remoteClient.selectRoleByFieldNameAndValue("fieldName", roleName);
            UserAndRoleAddEncapsulation ur = new UserAndRoleAddEncapsulation(integer, r.getId());
            s2 = urRemoteClient.insertUserRole(ur);
        }


        return "RoleManagement表：" + s1 + "," + "关联表" + s2;
    }

    @DeleteMapping("/delete-role")
    public String deleteRole(@RequestParam("id") Integer id) {
        String s1 = remoteClient.deleteRole(id);
        String s2 = urRemoteClient.deleteUserRoleByFieldNameAndValue("userId", id);
        return "RoleManagement表：" + s1 + "," + "关联表" + s2;
    }

    @DeleteMapping("/delete-role-by-fieldname-and-fieldvalue")
    public String deleteRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                @RequestParam("fieldValue") String fieldValue) {
        RoleManagement roleManagement = remoteClient.selectRoleByFieldNameAndValue(fieldName, fieldValue);
        int roleId = roleManagement.getId();
        String s1 = remoteClient.deleteRoleByFieldNameAndValue(fieldName, fieldValue);
        String s2 = urRemoteClient.deleteUserRoleByFieldNameAndValue("userId", roleId);
        return "RoleManagement表：" + s1 + "," + "关联表" + s2;
    }

    @PutMapping("/update-role-by-id")
    public String updateRoleById(@RequestBody RoleUpdateEncapsulation role) {
        Integer roleId = role.getRoleId();
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        List<Integer> userId = role.getUserId();
        RoleManagement r = new RoleManagement(roleId, roleName, roleDescribe);
        String s1 = remoteClient.updateRoleById(r);
        String s2 = null;
        int i = 0;
        for (Integer integer : userId) {
            List<UserAndRole> userId1 = urRemoteClient.selectUserRoleByFieldNameAndValue("userId", integer);
            UserAndRole ur = new UserAndRole(userId1.get(i++).getId(),integer,roleId);
            s2 = urRemoteClient.updateUserRole(ur);
        }
        return "RoleManagement表：" + s1 + "," + "关联表" + s2;
    }
}
