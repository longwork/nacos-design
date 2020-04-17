package org.student.controller;

import org.springframework.web.bind.annotation.*;
import org.student.entity.UserAndRole;
import org.student.service.UserAndRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class UserAndRoleController {
    public static final int OK = 1001;

    @Resource
    UserAndRoleService urService;

    @GetMapping("/select-all-user-role-list")
    public List<UserAndRole> selectAllUserRoleList() {
        return urService.selectAllUserRoleList();
    }

    @GetMapping("/select-user-role-by-id")
    public UserAndRole selectUserRoleById(@RequestParam("id") Integer id) {
        return urService.selectUserRoleById(id);
    }

    @GetMapping("/select-user-role-by-fieldname-and-fieldvalue")
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                               @RequestParam("fieldValue") Integer fieldValue) {
        return urService.selectUserRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    @PostMapping("/insert-user-role")
    public String insertUserRole(@RequestBody UserAndRole ur) {
        return urService.insertUserRole(ur);
    }

    @DeleteMapping("/delete-user-role")
    public String deleteUserRole(@RequestParam("id") Integer id) {
        return urService.deleteUserRole(id);
    }

    @DeleteMapping("/delete-user-role-by-fieldname-and-fieldvalue")
    public String deleteUserRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                    @RequestParam("fieldValue") Integer fieldValue) {
        return urService.deleteUserRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    @PutMapping("/update-user-role")
    public String updateUserRole(@RequestBody UserAndRole ur) {
        return urService.updateUserRole(ur);
    }
}
