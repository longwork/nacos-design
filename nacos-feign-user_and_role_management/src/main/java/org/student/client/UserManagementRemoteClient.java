package org.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;

import java.util.List;

/**
 * @author Administrator
 */
@FeignClient(name = "nacos-userManagement")
public interface UserManagementRemoteClient {

    /**
     * 查询全部的UserManagement
     *
     * @return List集合，里面为所有的UserManagement
     */
    @GetMapping("/select-all-user-list")
    List<UserManagement> selectAllUserList();

    /**
     * 通过传入的条件查询
     *
     * @param birth1    第一个生日，用String类型传入数据库(条件1)
     * @param birth2    第二个生日，用String类型传入数据库(条件2)
     * @param condition 需要满足的条件(条件3)
     * @return List集合，返回所有满足条件的
     */
    @GetMapping("/select-user-by-condition-list")
    List<UserManagement> selectUserByConditionList(@RequestParam("b1") String birth1,
                                                   @RequestParam("b2") String birth2,
                                                   @RequestParam("c") String condition);

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的UserManagement
     */
    @GetMapping("/select-user-by-id")
    UserManagement selectUserById(@RequestParam("id") Integer id);

    /**
     * 通过FieldName和FieldValue值来查询
     *
     * @param fieldName  传入的字段名
     * @param fieldValue 传入的字段值
     * @return 返回通过FieldName和FieldValue值查询的UserManagement
     */
    @GetMapping("/select-user-by-fieldname-and-fieldvalue")
    List<UserManagement> selectUserByFileNameAndValue(@RequestParam("fieldName") String fieldName,
                                                @RequestParam("fieldValue") String fieldValue);

    /**
     * 插入数据
     *
     * @param u 传入的封装好的UserManagement
     * @return 插入的结果
     */
    @PostMapping("/insert-user")
    String insertUser(@RequestBody UserAddEncapsulation u);

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键
     * @return 删除的结果
     */
    @DeleteMapping("/delete-user-by-id")
    String deleteUserById(@RequestParam("id") Integer id);

    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 删除的结果
     */
    @DeleteMapping("/delete-user-by-fieldname-and-fieldvalue")
    String deleteUserByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                         @RequestParam("fieldValue") String fieldValue);

    /**
     * 通过Id修改数据
     *
     * @param u 传入的封装好的UserManagement
     * @return 修改的结果
     */
    @PutMapping("/update-user-by-id")
    String updateUserById(@RequestBody UserUpdateEncapsulation u);

}
