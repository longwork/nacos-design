package org.student.hystrix;

import org.springframework.stereotype.Component;
import org.student.client.UserAndRoleRemoteClient;
import org.student.entity.UserAndRole;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class UserAndRoleRemoteHystrix implements UserAndRoleRemoteClient {
    @Override
    public List<UserAndRole> selectAllUserRoleList() {
        return null;
    }

    @Override
    public UserAndRole selectUserRoleById(Integer id) {
        return null;
    }

    @Override
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        return null;
    }

    @Override
    public String insertUserRole(UserAndRole ur) {
        return null;
    }

    @Override
    public String deleteUserRole(Integer id) {
        return null;
    }

    @Override
    public String deleteUserRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        return null;
    }

    @Override
    public String updateUserRole(UserAndRole ur) {
        return null;
    }
}