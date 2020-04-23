package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.student.entity.UserManagement;
import org.student.mapper.UserManagementMapper;
import org.student.service.UserManagementService;

/**
 * @author Administrator
 */
@Service
public class UserManagementServiceImpl extends ServiceImpl<UserManagementMapper, UserManagement>
        implements UserManagementService {

    @Override
    public boolean nameRepetition(String name) {
        Wrapper<UserManagement> query = new QueryWrapper<UserManagement>().eq("name", name);
        return getOne(query) != null;
    }

    @Override
    public boolean emailRepetition(String email) {
        Wrapper<UserManagement> query = new QueryWrapper<UserManagement>().eq("email", email);
        return getOne(query) != null;
    }

    @Override
    public boolean phoneRepetition(String phone) {
        Wrapper<UserManagement> query = new QueryWrapper<UserManagement>().eq("phone", phone);
        return getOne(query) != null;
    }
}
