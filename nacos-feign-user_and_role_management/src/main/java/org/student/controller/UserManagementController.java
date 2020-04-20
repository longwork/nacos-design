package org.student.controller;

import org.springframework.web.bind.annotation.*;
import org.student.client.UserAndRoleRemoteClient;
import org.student.client.UserManagementRemoteClient;
import org.student.dto.*;
import org.student.entity.UserAndRole;
import org.student.entity.UserManagement;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
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
    public String insertUser(@RequestBody UserAddAndUpdate u1) {
        String name = u1.getName();
        String email = u1.getEmail();
        String phone = u1.getPhone();
        Date date = u1.getDate();

        UserAddEncapsulation u = new UserAddEncapsulation(name, email, phone, date);
        String s1 = remoteClient.insertUser(u);

        List<Integer> roleIdList = u1.getRoleIdList();
        StringBuilder s2 = new StringBuilder();
        List<UserManagement> name1 = remoteClient.selectUserByFileNameAndValue("name", name);
        for (Integer integer : roleIdList) {
            UserAndRoleAddEncapsulation ur = new UserAndRoleAddEncapsulation(name1.get(0).getId(), integer);
            s2.append(urRemoteClient.insertUserRole(ur));
        }
        return "UserManagement表：" + s1 + "," + "关联表：" + s2;
    }

    @DeleteMapping("/delete-user-by-id")
    public String deleteUserById(@RequestParam("id") Integer id) {
        String s1 = remoteClient.deleteUserById(id);
        Collection<Integer> collection = new ArrayList<>();
        collection.add(id);
        FieldCollection fieldCollections = new FieldCollection("userId", collection);
        String s = urRemoteClient.deleteUserRoleByFieldNameAndValue(fieldCollections);
        return "UserManagement表：" + s1 + "," + "关联表" + s;
    }

    @DeleteMapping("/delete-user-by-fieldname-and-fieldvalue")
    public String deleteUserByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                @RequestParam("fieldValue") String fieldValue) {
        List<UserManagement> userManagements = remoteClient.selectUserByFileNameAndValue(fieldName, fieldValue);
        if (userManagements.isEmpty()) {
            return "数据库中不存在此字段值";
        }
        Collection<Integer> collections = new ArrayList<>();
        for (UserManagement userManagement : userManagements) {
            collections.add(userManagement.getId());
        }
        String s1 = remoteClient.deleteUserByFieldNameAndValue(fieldName, fieldValue);
        String s2 = urRemoteClient.deleteUserRoleByFieldNameAndValue(new FieldCollection("userId", collections));
        return "UserManagement表：" + s1 + "," + "关联表" + s2;
    }

    @PutMapping("/update-user-by-id")
    public String updateUserById(@RequestBody UserAddAndUpdate u1) {
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
