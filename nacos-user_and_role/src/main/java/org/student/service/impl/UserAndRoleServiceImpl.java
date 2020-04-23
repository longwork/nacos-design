package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.student.entity.UserAndRole;
import org.student.mapper.UserAndRoleMapper;
import org.student.service.UserAndRoleService;

/**
 * @author Administrator
 */
@Service
public class UserAndRoleServiceImpl extends ServiceImpl<UserAndRoleMapper, UserAndRole>
        implements UserAndRoleService {

    @Override
    public UserAndRole selectUserRoleByUserIdAndRoleId(Integer userId, Integer roleId) {
        Wrapper<UserAndRole> query = new QueryWrapper<UserAndRole>()
                .eq("userId", userId)
                .and(i -> i.eq("roleId", roleId));
        return getOne(query);
    }
}
