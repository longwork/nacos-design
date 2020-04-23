package org.student.appservice.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.student.appservice.RoleManagementAppService;
import org.student.dto.RoleAddEncapsulation;
import org.student.dto.RoleUpdateEncapsulation;
import org.student.entity.RoleManagement;
import org.student.service.RoleManagementService;

import java.util.List;

/**
 * @author Long
 * @date 15:07 2020-04-20 周一
 */
@Service
@AllArgsConstructor
public class RoleManagementAppServiceImpl implements RoleManagementAppService {
    private static final int ROLE_DESCRIBE_LENGTH = 20;

    private final RoleManagementService roleManagementService;

    @Override
    public List<RoleManagement> selectAllRoleList() {
        return roleManagementService.list();
    }

    @Override
    public RoleManagement selectRoleById(Integer id) {
        return roleManagementService.getById(id);
    }

    @Override
    public RoleManagement selectRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        Wrapper<RoleManagement> query = new QueryWrapper<RoleManagement>().eq(fieldName, fieldValue);
        return roleManagementService.getOne(query);
    }

    @Override
    public String insertRole(RoleAddEncapsulation roleAddEncapsulation) {
        RoleManagement roleManagement = addRoleDtoToRoleManagement(roleAddEncapsulation);
        String detection = detection(roleManagement);
        if (detection != null) {
            return detection;
        }
        return roleManagementService.save(roleManagement) ? "插入成功,对应的id" + roleManagement.getId() : "插入失败";
    }

    @Override
    public String deleteRole(Integer id) {
        return roleManagementService.removeById(id) ? "删除成功" : "删除失败";
    }

    @Override
    public String deleteRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        Wrapper<RoleManagement> query = new QueryWrapper<RoleManagement>().eq(fieldName, fieldValue);
        return roleManagementService.remove(query) ? "删除成功" : "删除失败";
    }

    @Override
    public String updateRoleById(RoleUpdateEncapsulation roleUpdateEncapsulation) {
        RoleManagement roleManagement = updateRoleDtoToRoleManagement(roleUpdateEncapsulation);
        String updateDetection = updateDetection(roleManagement);
        if (updateDetection != null) {
            return updateDetection;
        }
        return roleManagementService.updateById(roleManagement) ? "修改成功" : "修改失败";
    }

    /**
     * 将前端传来的dto类转换为实体类
     *
     * @param roleAddEncapsulation 插入的RoleAddEncapsulation实体类
     * @return 返回的RoleManagement
     */
    private RoleManagement addRoleDtoToRoleManagement(RoleAddEncapsulation roleAddEncapsulation) {
        String roleName = roleAddEncapsulation.getRoleName();
        String roleDescribe = roleAddEncapsulation.getRoleDescribe();
        return new RoleManagement(roleName, roleDescribe);
    }

    private RoleManagement updateRoleDtoToRoleManagement(RoleUpdateEncapsulation roleUpdateEncapsulation) {
        Integer id = roleUpdateEncapsulation.getId();
        String roleName = roleUpdateEncapsulation.getRoleName();
        String roleDescribe = roleUpdateEncapsulation.getRoleDescribe();
        return new RoleManagement(id, roleName, roleDescribe);
    }

    /**
     * 检测属性合格
     *
     * @param roleManagement 传入的RoleManagement
     * @return 返回不符合的语句
     */
    private String detection(RoleManagement roleManagement) {
        String roleName = roleManagement.getRoleName();
        String roleDescribe = roleManagement.getRoleDescribe();
        if (roleManagement.roleNameDetection()) {
            return "角色名格式不对";
        }
        if (roleManagementService.roleNameRepetition(roleName)) {
            return "角色名称已存在";
        }
        if (roleDescribe.length() > ROLE_DESCRIBE_LENGTH) {
            return "描述过长，请减少描述";
        }
        if (roleManagementService.roleDescribeRepetition(roleDescribe)) {
            return "描述存在，请修改描述";
        }
        return null;
    }

    /**
     * 检测修改数据是否合格
     *
     * @param roleManagement 传入的RoleManagement
     * @return 返回不符合的语句
     */
    private String updateDetection(RoleManagement roleManagement) {
        Integer id = roleManagement.getId();
        String roleName = roleManagement.getRoleName();
        String roleDescribe = roleManagement.getRoleDescribe();
        RoleManagement r = roleManagementService.getById(id);

        if (roleManagementService.roleNameRepetition(roleName) && !roleName.equals(r.getRoleName())) {
            return "角色名已存在";
        }
        if (roleDescribe.length() > ROLE_DESCRIBE_LENGTH) {
            return "角色描述太长";
        }
        if (roleManagementService.roleDescribeRepetition(roleDescribe) && !roleDescribe.equals(r.getRoleDescribe())) {
            return "描述已存在";
        }
        return null;
    }
}
