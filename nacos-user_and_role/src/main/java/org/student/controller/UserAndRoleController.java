package org.student.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.student.appservice.UserAndRoleAppService;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.dto.UserAndRoleUpdateEncapsulation;
import org.student.entity.UserAndRole;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@AllArgsConstructor
public class UserAndRoleController {

    private final UserAndRoleAppService userAndRoleAppService;

    @GetMapping("/select-all-user-role-list")
    public List<UserAndRole> selectAllUserRoleList() {
        return userAndRoleAppService.selectAllUserRoleList();
    }

    @GetMapping("/select-user-role-by-id")
    public UserAndRole selectUserRoleById(@RequestParam("id") Integer id) {
        return userAndRoleAppService.selectUserRoleById(id);
    }

    @GetMapping("/select-user-role-by-fieldname-and-fieldvalue")
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                               @RequestParam("fieldValue") Integer fieldValue) {
        return userAndRoleAppService.selectUserRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    @PostMapping("/insert-user-role")
    public String insertUserRole(@RequestBody UserAndRoleAddEncapsulation userAndRoleAddEncapsulation) {
        return userAndRoleAppService.insertUserRole(userAndRoleAddEncapsulation);
    }

    @DeleteMapping("/delete-user-role")
    public String deleteUserRole(@RequestParam("id") Integer id) {
        return userAndRoleAppService.deleteUserRole(id);
    }

    @DeleteMapping("/delete-user-role-by-fieldname-and-fieldvalue")
    public String deleteUserRoleByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                    @RequestParam("fieldValue") Integer fieldValue) {
        return userAndRoleAppService.deleteUserRoleByFieldNameAndValue(fieldName, fieldValue);
    }

    @PutMapping("/update-user-role")
    public String updateUserRole(@RequestBody UserAndRoleUpdateEncapsulation userAndRoleUpdateEncapsulation) {
        return userAndRoleAppService.updateUserRole(userAndRoleUpdateEncapsulation);
    }
}
