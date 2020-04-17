package org.student.controller;

import org.springframework.web.bind.annotation.*;
import org.student.client.UserAndRoleRemoteClient;
import org.student.client.UserManagementRemoteClient;
import org.student.entity.UserAndRole;
import org.student.entity.UserEncapsulation;
import org.student.entity.UserEncapsulation1;
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
    public String insertUser(@RequestBody UserEncapsulation1 u1) {
        Integer id = u1.getId();
        String name = u1.getName();
        String email = u1.getEmail();
        String phone = u1.getPhone();
        Date date = u1.getDate();
        List<Integer> roleIdList = u1.getRoleIdList();
        StringBuilder s2 = new StringBuilder();
        List<UserAndRole> userAndRoles = urRemoteClient.selectAllUserRoleList();
        int size = userAndRoles.size();
        UserEncapsulation u = new UserEncapsulation(id, name, email, phone, date);
        for (Integer integer : roleIdList) {
            UserAndRole ur = new UserAndRole(++size, id, integer);
            s2.append(urRemoteClient.insertUserRole(ur));
        }
        String s1 = remoteClient.insertUser(u);
        return "UserManagement表：" + s1 + "," + "关联表" + s2;
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
        if (userManagements == null) {
            return "数据库没有这个值";
        }
        String s1 = null;
        String s2 = null;
        for (UserManagement userManagement : userManagements) {
            int userId = userManagement.getId();
            s1 = remoteClient.deleteUserByFieldNameAndValue(fieldName, fieldValue);
            s2 = urRemoteClient.deleteUserRoleByFieldNameAndValue("userId", userId);
        }
        return "UserManagement表：" + s1 + "," + "关联表" + s2;
    }

    @PutMapping("/update-user-by-id")
    public String updateUserById(@RequestBody UserEncapsulation1 u1) {
        Integer id = u1.getId();
        String name = u1.getName();
        String email = u1.getEmail();
        String phone = u1.getPhone();
        Date date = u1.getDate();
        List<Integer> roleIdList = u1.getRoleIdList();

        UserEncapsulation u = new UserEncapsulation(id, name, email, phone, date);
        String s1 = remoteClient.updateUserById(u);
        StringBuilder s2 = new StringBuilder();
        List<UserAndRole> userAndRoleList = urRemoteClient.selectUserRoleByFieldNameAndValue("userId", id);
        int i = 0;
        for (Integer integer : roleIdList) {
            UserAndRole ur = new UserAndRole(userAndRoleList.get(i++).getId(), id, integer);
            s2.append(urRemoteClient.updateUserRole(ur));
        }
        return "UserManagement表：" + s1 + "," + "关联表" + s2;
    }

}
