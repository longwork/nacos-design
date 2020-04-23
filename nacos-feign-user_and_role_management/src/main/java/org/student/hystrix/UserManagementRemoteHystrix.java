package org.student.hystrix;

import org.springframework.stereotype.Component;
import org.student.client.UserManagementRemoteClient;
import org.student.dto.UserAddEncapsulation;
import org.student.dto.UserUpdateEncapsulation;
import org.student.entity.UserManagement;

import java.util.List;


/**
 * @author Administrator
 */
@Component
public class UserManagementRemoteHystrix implements UserManagementRemoteClient {

    @Override
    public List<UserManagement> selectAllUserList() {
        return null;
    }

    @Override
    public List<UserManagement> selectUserByConditionList(String birth1, String birth2, String condition) {
        return null;
    }

    @Override
    public UserManagement selectUserById(Integer id) {
        return null;
    }

    @Override
    public UserManagement selectUserByFieldNotBirth(String fieldName, String fieldValue) {
        return null;
    }

    @Override
    public List<UserManagement> selectUserByBirth(String fieldValue) {
        return null;
    }


    @SuppressWarnings("SpringMVCViewInspection")
    @Override
    public String insertUser(UserAddEncapsulation u) {
        return "请求超时！";
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @Override
    public String deleteUserById(Integer id) {
        return "请求超时！";
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @Override
    public String deleteUserByFieldNameAndValue(String fieldName, String fieldValue) {
        return "请求超时！";
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @Override
    public String updateUserById(UserUpdateEncapsulation u) {
        return "请求超时！";
    }

}
