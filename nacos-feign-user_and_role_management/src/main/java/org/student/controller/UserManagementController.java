package org.student.controller;

import org.springframework.web.bind.annotation.*;
import org.student.client.UserAndRoleRemoteClient;
import org.student.client.UserManagementRemoteClient;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.dto.UserAndRoleEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserAndRole;
import org.student.entity.UserManagement;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/user-feign")
public class UserManagementController {

    @Resource
    private UserManagementRemoteClient remoteClient;
    @Resource
    private UserAndRoleRemoteClient urRemoteClient;

    @GetMapping("/select-all-user-list")
    public List<UserManagement> selectAllUserList() {
        return remoteClient.selectAllUserList();
    }

    @GetMapping("/select-user-by-id")
    public UserManagement selectUserById(@RequestParam("id") Integer id) {
        return remoteClient.selectUserById(id);
    }

    @GetMapping("/select-user-by-fieldname-and-fieldvalue")
    public List<UserManagement> selectUserByFileNameAndValue(@RequestParam("fieldName") String fieldName,
                                                             @RequestParam("fieldValue") String fieldValue) {
        return remoteClient.selectUserByFileNameAndValue(fieldName, fieldValue);
    }

    @PostMapping("/insert-user")
    public String insertUser(@RequestBody UserAndRoleEncapsulation u1) {
        Integer id = u1.getId();
        String name = u1.getName();
        String email = u1.getEmail();
        String phone = u1.getPhone();
        Date date = u1.getDate();
        UserAddEncapsulation u = new UserAddEncapsulation(name, email, phone, date);
        String s1 = remoteClient.insertUser(u);

        List<Integer> roleIdList = u1.getRoleIdList();
        StringBuilder s2 = new StringBuilder();

        for (Integer integer : roleIdList) {
            UserAndRoleAddEncapsulation ur = new UserAndRoleAddEncapsulation(id, integer);
            s2.append(urRemoteClient.insertUserRole(ur));
        }

        return "UserManagement表：" + s1 + "," + "关联表：" + s2;
    }

    @DeleteMapping("/delete-user-by-id")
    public String deleteUserById(@RequestParam("id") Integer id,
                                 @RequestParam("fieldName") String fileName,
                                 @RequestParam("fieldValue") Integer fieldValue) {
        String s1 = remoteClient.deleteUserById(id);
        String s = urRemoteClient.deleteUserRoleByFieldNameAndValue(fileName, fieldValue);
        return "UserManagement表：" + s1 + "," + "关联表" + s;
    }

    @DeleteMapping("/delete-user-by-fieldname-and-fieldvalue")
    public String deleteUserByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                @RequestParam("fieldValue") String fieldValue) {
        List<UserManagement> userManagements = remoteClient.selectUserByFileNameAndValue(fieldName, fieldValue);
        if (userManagements.isEmpty()) {
            return "数据库没有这个值";
        }
        String str = "data";
        String s1;
        String s2 = null;
        if (!str.equals(fieldName)) {
            int userId = userManagements.get(0).getId();
            s1 = remoteClient.deleteUserByFieldNameAndValue(fieldName, fieldValue);
            s2 = urRemoteClient.deleteUserRoleByFieldNameAndValue("userId", userId);
        } else {
            s1 = remoteClient.deleteUserByFieldNameAndValue(fieldName, fieldValue);
            for (UserManagement userManagement : userManagements) {
                int userId = userManagement.getId();
                s2 = urRemoteClient.deleteUserRoleByFieldNameAndValue("userId", userId);
            }
        }
        return "UserManagement表：" + s1 + "," + "关联表" + s2;
    }

    @PutMapping("/update-user-by-id")
    public String updateUserById(@RequestBody UserAndRoleEncapsulation u1) {
        Integer id = u1.getId();
        String name = u1.getName();
        String email = u1.getEmail();
        String phone = u1.getPhone();
        Date date = u1.getDate();

        UserUpdateEncapsulation u = new UserUpdateEncapsulation(id, name, email, phone, date);
        String s1 = remoteClient.updateUserById(u);

        List<Integer> roleIdList = u1.getRoleIdList();
        StringBuilder s2 = new StringBuilder();
        int i = 0;

        List<UserAndRole> userId = urRemoteClient.selectUserRoleByFieldNameAndValue("userId", id);
        for (UserAndRole userAndRole : userId) {
            UserAndRole ur = new UserAndRole(userAndRole.getId(), id, roleIdList.get(i++));
            s2.append(urRemoteClient.updateUserRole(ur));
        }
        return "UserManagement表：" + s1 + "," + "关联表" + s2;
    }

}
