package org.student.hystrix;

import org.springframework.stereotype.Component;
import org.student.client.UserAndRoleRemoteClient;
import org.student.dto.FieldCollection;
import org.student.dto.UserAndRoleAddEncapsulation;
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
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(String fieldName, Integer fieldValue) {
        return null;
    }

    @Override
    public String insertUserRole(UserAndRoleAddEncapsulation ur) {
        return null;
    }

    @Override
    public String deleteUserRole(Integer id) {
        return null;
    }

    @Override
    public String deleteUserRoleByFieldNameAndValue(FieldCollection fieldCollections) {
        return null;
    }

    @Override
    public String updateUserRole(UserAndRole ur) {
        return null;
    }
}
