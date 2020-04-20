package org.student.controller;

import org.springframework.web.bind.annotation.*;
import org.student.appservice.UserAndRoleAppService;
import org.student.dto.FieldCollection;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.entity.UserAndRole;
import org.student.service.UserAndRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class UserAndRoleController {

    @Resource
    UserAndRoleService urService;
    @Resource
    UserAndRoleAppService urAppService;

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

    @GetMapping("/select-user-role-by-fieldCollection")
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(@RequestBody FieldCollection fieldCollection) {
        return urService.selectUserRoleByFieldNameAndValue(fieldCollection.getFieldName(), fieldCollection.getCollections());
    }

    @PostMapping("/insert-user-role")
    public String insertUserRole(@RequestBody UserAndRoleAddEncapsulation ur) {
        UserAndRole userAndRole = urAppService.entityTransaction(ur);
        return urService.insertUserRole(userAndRole);
    }

    @DeleteMapping("/delete-user-role")
    public String deleteUserRole(@RequestParam("id") Integer id) {
        return urService.deleteUserRole(id);
    }

    @DeleteMapping("/delete-user-role-by-fieldname-and-fieldvalue")
    public String deleteUserRoleByFieldNameAndValue(@RequestBody FieldCollection fieldCollection) {
        return urService.deleteUserRoleByFieldNameAndValue(fieldCollection);
    }

    @PutMapping("/update-user-role")
    public String updateUserRole(@RequestBody UserAndRole ur) {
        return urService.updateUserRole(ur);
    }
}
