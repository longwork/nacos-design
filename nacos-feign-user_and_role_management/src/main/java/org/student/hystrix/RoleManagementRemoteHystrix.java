package org.student.hystrix;

import org.springframework.stereotype.Component;
import org.student.client.RoleManagementRemoteClient;
import org.student.entity.RoleManagement;

import java.util.List;


/**
 * @author Administrator
 */
@Component
public class RoleManagementRemoteHystrix implements RoleManagementRemoteClient {
    @Override
    public List<RoleManagement> selectAllRoleList() {
        return null;
    }

    @Override
    public RoleManagement selectRoleById(Integer id) {
        return null;
    }

    @Override
    public RoleManagement selectRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        return null;
    }

    @Override
    public String insertRole(RoleManagement role) {
        return null;
    }

    @Override
    public String deleteRole(Integer id) {
        return null;
    }

    @Override
    public String deleteRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        return null;
    }

    @Override
    public String updateRoleById(RoleManagement role) {
        return null;
    }
}
