package org.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.student.client.RoleManagementRemoteClient;
import org.student.client.UserAndRoleRemoteClient;
import org.student.entity.RoleManagement;
import org.student.entity.RoleResult;
import org.student.entity.UserAndRole;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class RoleResultController {

    @Resource
    RoleManagementRemoteClient roleRemoteClient;
    @Resource
    UserAndRoleRemoteClient urRemoteClient;

    @GetMapping("/role-result")
    public List<RoleResult> roleResult() {
        List<RoleManagement> rs = roleRemoteClient.selectAllRoleList();
        List<UserAndRole> uRs = urRemoteClient.selectAllUserRoleList();
        List<RoleResult> list = new ArrayList<>(rs.size());
        for (RoleManagement r : rs) {
            int id = r.getId();
            int count = 0;
            for (UserAndRole uR : uRs) {
                if (id == uR.getRoleId()) {
                    count++;
                }
            }
            list.add(new RoleResult(id, r.getRoleName(), r.getRoleDescribe(), count));
        }
        return list;
    }
}
