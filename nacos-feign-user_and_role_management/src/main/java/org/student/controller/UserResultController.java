package org.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.student.client.RoleManagementRemoteClient;
import org.student.client.UserAndRoleRemoteClient;
import org.student.client.UserManagementRemoteClient;
import org.student.entity.UserResult;
import org.student.entity.RoleManagement;
import org.student.entity.UserAndRole;
import org.student.entity.UserManagement;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class UserResultController {

    @Resource
    UserManagementRemoteClient userRemoteClient;
    @Resource
    RoleManagementRemoteClient roleRemoteClient;
    @Resource
    UserAndRoleRemoteClient urRemoteClient;

    @GetMapping("/select-all-user-list")
    public List<UserResult> selectAllResult() {
        return getResults(userRemoteClient.selectAllUserList());
    }

    @GetMapping("/select-user-by-condition-list")
    public List<UserResult> selectByCondition(@RequestParam("b1") String birth1,
                                              @RequestParam("b2") String birth2,
                                              @RequestParam("c") String condition) {
        List<UserManagement> us = userRemoteClient.selectUserByConditionList(birth1, birth2, condition);
        return getResults(us);
    }

    /**
     * selectAllResult和selectUserManagementByCondition相同的代码，拿出来重构。不会过多的冗余代码
     *
     * @param us 传入查询到的UserManagement集合
     * @return 返回最后的Result集合
     */
    private List<UserResult> getResults(List<UserManagement> us) {
        List<RoleManagement> rs = roleRemoteClient.selectAllRoleList();
        List<UserAndRole> uRs = urRemoteClient.selectAllUserRoleList();
        List<UserResult> list = new ArrayList<>(uRs.size());
        for (UserAndRole uR : uRs) {
            int userId = uR.getUserId();
            int roleId = uR.getRoleId();
            for (UserManagement u : us) {
                if (userId == u.getId()) {
                    for (RoleManagement r : rs) {
                        if (roleId == r.getId()) {
                            list.add(new UserResult(userId, u.getName(), u.getEmail(), u.getPhone(), u.getBirth(), r.getRoleName()));
                        }
                    }
                }
            }
        }
        return list;
    }
}
