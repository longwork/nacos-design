package org.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.student.entity.UserAndRole;

/**
 * @author Administrator
 */
public interface UserAndRoleService extends IService<UserAndRole> {
    /**
     * 通过两个ID查询唯一值
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 查询到的关联表中的数据
     */
    UserAndRole selectUserRoleByUserIdAndRoleId(Integer userId, Integer roleId);

}
