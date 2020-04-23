package org.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.student.appservice.UserManagementAppService;
import org.student.dto.PageSelect;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@RestController
@AllArgsConstructor
public class UserManagementController {

    private final UserManagementAppService userManagementAppService;

    /**
     * 测试
     *
     * @return config对应的值(在NacosConfig命名空间中的值)
     */
    @GetMapping("/config")
    public String getConfig(@Value("${nacos.config") String config) {
        return config;
    }

    /**
     * 测试
     *
     * @return hello
     */
    @GetMapping("/hello")
    public String helloNacos() {
        return "hello";
    }

    /**
     * 在初始化的时候给传入的String类型转化为Date类型
     *
     * @param binder URL传来的要转换的值
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 查询全部的UserManagement
     *
     * @return List集合，里面为所有的UserManagement
     */
    @GetMapping("/select-all-user-list")
    public List<UserManagement> selectAllUserList() {
        return userManagementAppService.selectAllUserList();
    }

    /**
     * 通过传入的条件查询
     *
     * @param birth1    第一个生日，用String类型传入数据库(条件1)
     * @param birth2    第二个生日，用String类型传入数据库(条件2)
     * @param condition 需要满足的条件(条件3)
     * @return List集合，返回所有满足条件的
     */
    @GetMapping("/select-user-by-condition-list")
    public List<UserManagement> selectUserByConditionList(@RequestParam("b1") String birth1,
                                                          @RequestParam("b2") String birth2,
                                                          @RequestParam("c") String condition) {
        return userManagementAppService.selectUserByConditionList(birth1, birth2, condition);
    }

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的UserManagement
     */
    @GetMapping("/select-user-by-id")
    public UserManagement selectUserById(@RequestParam("id") Integer id) {
        return Optional.ofNullable(userManagementAppService.selectUserById(id)).orElse(new UserManagement());
    }

    /**
     * 通过FieldName和FieldValue值来查询
     *
     * @param fieldName  传入的字段名
     * @param fieldValue 传入的字段值
     * @return 返回通过FieldName和FieldValue值查询的UserManagement
     */
    @GetMapping("/select-user-by-field-not-birth")
    public UserManagement selectUserByFieldNotBirth(@RequestParam("fieldName") String fieldName,
                                                    @RequestParam("fieldValue") String fieldValue) {
        return userManagementAppService.selectUserByFieldNotBirth(fieldName, fieldValue);
    }

    /**
     * 通过生日对应的值来查询
     *
     * @param fieldValue 字段值
     * @return 查询的对应的用户
     */
    @GetMapping("/select-user-by-birth")
    public List<UserManagement> selectUserByBirth(@RequestParam("fieldValue") String fieldValue) {
        return userManagementAppService.selectUserByBirth(fieldValue);
    }

    /**
     * 插入数据
     *
     * @param userAddEncapsulation 传入前台封装过的UserAddEncapsulation
     * @return 插入的结果
     */
    @PostMapping("/insert-user")
    public String insertUser(@RequestBody UserAddEncapsulation userAddEncapsulation) {
        return userManagementAppService.insertUser(userAddEncapsulation);
    }

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键
     * @return 删除的结果
     */
    @DeleteMapping("/delete-user-by-id")
    public String deleteUserById(@RequestParam("id") Integer id) {
        return userManagementAppService.deleteUserById(id);
    }

    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 删除的结果
     */
    @DeleteMapping("/delete-user-by-fieldname-and-fieldvalue")
    public String deleteUserByFieldNameAndValue(@RequestParam("fieldName") String fieldName,
                                                @RequestParam("fieldValue") String fieldValue) {
        return userManagementAppService.deleteUserByFieldNameAndValue(fieldName, fieldValue);
    }

    /**
     * 通过Id修改数据
     *
     * @param userUpdateEncapsulation 传入的封装好的UserManagement
     * @return 修改的结果
     */
    @PutMapping("/update-user-by-id")
    public String updateUserById(@RequestBody UserUpdateEncapsulation userUpdateEncapsulation) {
        return userManagementAppService.updateUserById(userUpdateEncapsulation);
    }

    @GetMapping("/select-page")
    public IPage<UserManagement> selectUserPage(@RequestBody PageSelect pages) {
        return userManagementAppService.selectPage(pages);
    }
}
