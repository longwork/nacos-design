package org.student.appservice.impl;

import org.springframework.stereotype.Service;
import org.student.appservice.UserAndRoleAppService;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.entity.UserAndRole;
import org.student.service.UserAndRoleService;

import javax.annotation.Resource;

/**
 * @author Long
 * @date 15:36 2020-04-20 周一
 */
@Service
public class UserAndRoleAppServiceImpl implements UserAndRoleAppService {
    @Resource
    UserAndRoleService urService;

    @Override
    public UserAndRole entityTransaction(UserAndRoleAddEncapsulation ur) {
        Integer userId = ur.getUserId();
        Integer roleId = ur.getRoleId();
        return new UserAndRole(userId, roleId);
    }

    @Override
    public boolean detection(UserAndRole ur) {
        Integer roleId = ur.getRoleId();
        Integer userId = ur.getUserId();
        UserAndRole userAndRole = urService.selectUserRoleById(roleId, userId);
        return userAndRole == null;
    }
}
