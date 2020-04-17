package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.student.entity.UserAndRole;
import org.student.mapper.UserAndRoleMapper;
import org.student.service.UserAndRoleService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class UserAndRoleServiceImpl implements UserAndRoleService {
    @Resource
    UserAndRoleMapper urMapper;

    @Override
    public List<UserAndRole> selectAllUserRoleList() {
        return urMapper.selectList(null);
    }

    @Override
    public UserAndRole selectUserRoleById(Integer id) {
        return urMapper.selectById(id);
    }

    @Override
    public List<UserAndRole> selectUserRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        return urMapper.selectList(
                new QueryWrapper<UserAndRole>().eq(fieldName, fieldValue)
        );
    }

    @Override
    public String insertUserRole(UserAndRole ur) {
        Integer id = ur.getId();
        if (idDetection(id)) {
            return "ID主键唯一，请换一个ID";
        }
        int insert = urMapper.insert(ur);
        return insert == 1 ? "插入成功" : "插入失败";
    }

    @Override
    public String deleteUserRole(Integer id) {
        //先进行查询是否存在
        UserAndRole userandrole = urMapper.selectById(id);
        //如果不存在，则不进行删除
        if (userandrole == null) {
            return "数据不存在，删除失败";
        } else {
            int delete = urMapper.deleteById(id);
            return delete == 1 ? "删除成功" : "删除失败";
        }
    }

    @Override
    public String deleteUserRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        //先进行查询是否存在
        List<UserAndRole> userAndRoles = selectUserRoleByFieldNameAndValue(fieldName, fieldValue);
        //如果不存在，则不进行删除
        if (userAndRoles.isEmpty()) {
            return "数据不存在，删除失败";
        } else {
            Map<String, Object> map = new HashMap<>(1);
            //如果不指定，则全部删除
            map.put(fieldName, fieldValue);
            int delete = urMapper.deleteByMap(map);
            //如果删除成功就返回1，失败返回0
            return delete == 1 ? "删除成功" : "删除失败";
        }
    }

    @Override
    public String updateUserRole(UserAndRole ur) {
        Integer id = ur.getId();
        Integer userId = ur.getUserId();
        Integer roleId = ur.getRoleId();
        if (selectUserRoleById(id) == null) {
            if (idDetection(id)) {
                return "ID主键唯一，请换一个ID";
            }
            int insert = urMapper.insert(ur);
            return insert == 1 ? "数据不存在，已插入" : "插入失败";
        } else {
            if (idDetection(id)) {
                return "ID主键唯一，请换一个ID";
            }
            int update = urMapper.updateById(ur);
            return update == 1 ? "修改成功" : "修改失败";
        }
    }

    @Override
    public boolean idDetection(Integer id) {
        UserAndRole userAndRole = selectUserRoleById(id);
        return userAndRole != null;
    }
}
