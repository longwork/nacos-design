package org.student.appservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.student.dto.PageSelect;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;

import java.util.List;

/**
 * @author Long
 * @date 14:15 2020-04-20 周一
 */
public interface UserManagementAppService {

    /**
     * 查询全部的UserManagement
     *
     * @return List集合，里面为所有的UserManagement
     */
    List<UserManagement> selectAllUserList();

    /**
     * 通过传入的条件查询
     *
     * @param birth1    第一个生日，用String类型传入数据库(条件1)
     * @param birth2    第二个生日，用String类型传入数据库(条件2)
     * @param condition 需要满足的条件(条件3)
     * @return List集合，返回所有满足条件的
     */
    List<UserManagement> selectUserByConditionList(String birth1, String birth2, String condition);

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的UserManagement
     */
    UserManagement selectUserById(Integer id);

    /**
     * 通过除了生日的其他字段名和字段值查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 查询到的用户
     */
    UserManagement selectUserByFieldNotBirth(String fieldName, String fieldValue);

    /**
     * 通过birth值来查询
     *
     * @param fieldValue 字段值
     * @return 返回通过FieldName和FieldValue值查询的UserManagement
     */
    List<UserManagement> selectUserByBirth(String fieldValue);

    /**
     * 插入数据
     *
     * @param userAddEncapsulation 想要插入的User
     * @return 返回结果的字符串
     */
    String insertUser(UserAddEncapsulation userAddEncapsulation);

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果的字符串
     */
    String deleteUserById(Integer id);

    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回结果的字符串
     */
    String deleteUserByFieldNameAndValue(String fieldName, String fieldValue);

    /**
     * 通过Id修改数据
     *
     * @param userUpdateEncapsulation 要更新的User
     * @return 返回结果的字符串
     */
    String updateUserById(UserUpdateEncapsulation userUpdateEncapsulation);

    /**
     * 查询：根据state状态查询用户列表，分页显式
     *
     * @param page 分页对象，xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @return 分页对象
     */
    IPage<UserManagement> selectPage(PageSelect page);
}
