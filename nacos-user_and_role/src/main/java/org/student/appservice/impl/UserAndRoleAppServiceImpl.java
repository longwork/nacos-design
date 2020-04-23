package org.student.appservice.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.student.appservice.UserAndRoleAppService;
import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.dto.UserAndRoleUpdateEncapsulation;
import org.student.entity.UserAndRole;
import org.student.service.UserAndRoleService;

import java.util.List;

/**
 * @author Long
 * @date 15:36 2020-04-20 周一
 */
@Service
@AllArgsConstructor
public class UserAndRoleAppServiceImpl implements UserAndRoleAppService {

    private final UserAndRoleService userAndRoleService;

    @Override
    public List<UserAndRole> selectAllUserRoleList() {
        return userAndRoleService.list();
    }

    @Override
    public UserAndRole selectUserRoleById(Integer id) {
        return userAndRoleService.getById(id);
    }

    @Override
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(String fieldName, Integer fieldValue) {
        Wrapper<UserAndRole> query = new QueryWrapper<UserAndRole>()
                .eq(fieldName, fieldValue);
        return userAndRoleService.list(query);
    }

    @Override
    public String insertUserRole(UserAndRoleAddEncapsulation userAndRoleAddEncapsulation) {
        UserAndRole userAndRole = addUserAndRoleDtoToUserAndRole(userAndRoleAddEncapsulation);
        boolean detection = detection(userAndRole);
        if (!detection) {
            return "数据已存在";
        }
        return userAndRoleService.save(userAndRole) ? "插入成功" : "插入失败";
    }

    @Override
    public String deleteUserRole(Integer id) {
        return userAndRoleService.removeById(id) ? "删除成功" : "删除失败";
    }

    @Override
    public String deleteUserRoleByFieldNameAndValue(String fieldName, Integer fieldValue) {
        Wrapper<UserAndRole> query = new QueryWrapper<UserAndRole>().eq(fieldName, fieldValue);
        return userAndRoleService.remove(query) ? "删除成功" : "删除失败";
    }

    @Override
    public String updateUserRole(UserAndRoleUpdateEncapsulation userAndRoleUpdateEncapsulation) {
        UserAndRole userAndRole = updateUserAndRoleDtoToUserAndRole(userAndRoleUpdateEncapsulation);
        Integer id = userAndRole.getId();
        UserAndRole byId = userAndRoleService.getById(id);
        if (byId == null) {
            return "数据不存在，修改失败";
        }
        Integer roleId = userAndRole.getRoleId();
        Integer userId = userAndRole.getUserId();
        UserAndRole userAndRole1 = userAndRoleService.selectUserRoleByUserIdAndRoleId(userId, roleId);
        if (userAndRole1 == null) {
            boolean b = userAndRoleService.updateById(userAndRole);
            return b ? "修改成功" : "修改失败";
        }
        return "数据存在，修改失败";
    }

    /**
     * 将前端dto转化为entity实体类
     *
     * @param userAndRoleAddEncapsulation 转换类
     * @return 返回一个UserAndRole
     */
    private UserAndRole addUserAndRoleDtoToUserAndRole(UserAndRoleAddEncapsulation userAndRoleAddEncapsulation) {
        Integer userId = userAndRoleAddEncapsulation.getUserId();
        Integer roleId = userAndRoleAddEncapsulation.getRoleId();
        return new UserAndRole(userId, roleId);
    }

    /**
     * 将前端dto转化为entity实体类
     *
     * @param userAndRoleUpdateEncapsulation 转换类
     * @return 返回一个UserAndRole
     */
    private UserAndRole updateUserAndRoleDtoToUserAndRole(UserAndRoleUpdateEncapsulation userAndRoleUpdateEncapsulation) {
        Integer id = userAndRoleUpdateEncapsulation.getId();
        Integer userId = userAndRoleUpdateEncapsulation.getUserId();
        Integer roleId = userAndRoleUpdateEncapsulation.getRoleId();
        return new UserAndRole(id, userId, roleId);
    }

    /**
     * 判断插入的数据是否合格
     *
     * @param userAndRole 要插入的数据
     * @return true代表合格，false不合格
     */
    private boolean detection(UserAndRole userAndRole) {
        Integer roleId = userAndRole.getRoleId();
        Integer userId = userAndRole.getUserId();
        UserAndRole userAndRole1 = userAndRoleService.selectUserRoleByUserIdAndRoleId(roleId, userId);
        return userAndRole1 == null;
    }
}
