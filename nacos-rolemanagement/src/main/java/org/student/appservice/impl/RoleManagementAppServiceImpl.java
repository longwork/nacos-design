package org.student.appservice.impl;

import org.springframework.stereotype.Service;
import org.student.appservice.RoleManagementAppService;
import org.student.dto.RoleAddEncapsulation;
import org.student.dto.RoleUpdateEncapsulation;
import org.student.entity.RoleManagement;
import org.student.service.RoleManagementService;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Long
 * @date 15:07 2020-04-20 周一
 */
@Service
public class RoleManagementAppServiceImpl implements RoleManagementAppService {
    private static final int ROLE_DESCRIBE_LENGTH = 20;

    @Resource
    RoleManagementService roleService;

    @Override
    public RoleManagement entityTransaction(RoleAddEncapsulation role) {
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        return new RoleManagement(roleName, roleDescribe);
    }

    @Override
    public RoleManagement entityTransaction(RoleUpdateEncapsulation role) {
        Integer id = role.getId();
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        return new RoleManagement(id, roleName, roleDescribe);
    }

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @param roleName 传入的用户名
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    @Override
    public boolean roleNameDetection(String roleName) {
        String pattern = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(roleName);
        return !m.matches();
    }

    /**
     * 检测用户名是否存在
     *
     * @param roleName 传入的Name
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    @Override
    public boolean roleNameRepetition(String roleName) {
        RoleManagement role = roleService.selectRoleByFieldNameAndValue("roleName", roleName);
        return role != null;
    }

    @Override
    public boolean roleDescribeRepetition(String roleDescribe) {
        RoleManagement role = roleService.selectRoleByFieldNameAndValue("roleDescribe", roleDescribe);
        return role != null;
    }

    @Override
    public String detection(RoleManagement role) {
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        if (roleNameDetection(roleName)) {
            return "角色名格式不对";
        }
        if (roleNameRepetition(roleName)) {
            return "角色名称已存在";
        }
        if (roleDescribe.length() > ROLE_DESCRIBE_LENGTH) {
            return "描述过长，请减少描述";
        }
        if (roleDescribeRepetition(roleDescribe)) {
            return "描述存在，请修改描述";
        }
        return null;
    }

    @Override
    public String updateDetection(Integer id, String roleName, String roleDescribe) {
        RoleManagement r = roleService.selectRoleById(id);

        if (roleNameRepetition(roleName) && !roleName.equals(r.getRoleName())) {
            return "角色名已存在";
        }
        if (roleDescribe.length() > ROLE_DESCRIBE_LENGTH) {
            return "角色描述太长";
        }
        if (roleDescribeRepetition(roleDescribe) && !roleDescribe.equals(r.getRoleDescribe())) {
            return "描述已存在";
        }
        return null;
    }
}
