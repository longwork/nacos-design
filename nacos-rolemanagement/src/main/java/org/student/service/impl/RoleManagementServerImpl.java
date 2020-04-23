package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.student.entity.RoleManagement;
import org.student.mapper.RoleManagementMapper;
import org.student.service.RoleManagementService;

/**
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class RoleManagementServerImpl extends ServiceImpl<RoleManagementMapper, RoleManagement>
        implements RoleManagementService {

    @Override
    public boolean roleNameRepetition(String roleName) {
        Wrapper<RoleManagement> query = new QueryWrapper<RoleManagement>().eq("rolename", roleName);
        return getOne(query) != null;
    }

    @Override
    public boolean roleDescribeRepetition(String roleDescribe) {
        Wrapper<RoleManagement> query = new QueryWrapper<RoleManagement>().eq("roledescribe", roleDescribe);
        return getOne(query) != null;
    }
}
