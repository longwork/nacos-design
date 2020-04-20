package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.student.appservice.RoleManagementAppService;
import org.student.entity.RoleManagement;
import org.student.mapper.RoleManagementMapper;
import org.student.service.RoleManagementService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class RoleManagementServerImpl implements RoleManagementService {
    @Resource
    RoleManagementAppService roleAppService;

    @Resource
    RoleManagementMapper roleMapper;

    /**
     * 查询所有的RoleManagement
     *
     * @return List集合，里面为所有的RoleManagement
     */
    @Override
    public List<RoleManagement> selectAllRoleList() {
        return roleMapper.selectList(null);
    }

    /**
     * 通过ID查询
     *
     * @param id 传入的id
     * @return 返回通过id查询的RoleManagement
     */
    @Override
    public RoleManagement selectRoleById(Integer id) {
        return roleMapper.selectById(id);
    }

    /**
     * 通过FieldName和FieldValue值来查询
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 返回通过FieldName和FieldValue值查询的RoleManagement
     */
    @Override
    public RoleManagement selectRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        return roleMapper.selectOne(
                new QueryWrapper<RoleManagement>().eq(fieldName, fieldValue)
        );
    }


    /**
     * 插入数据
     *
     * @param role 想要插入的Role
     * @return 返回结果字符串
     */
    @Override
    public String insertRole(RoleManagement role) {
        String detection = roleAppService.detection(role);
        if (detection != null) {
            return detection;
        }

        int insert = roleMapper.insert(role);
        return insert > 0 ? "插入成功" : "插入失败";
    }

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回结果字符串
     */
    @Override
    public String deleteRole(Integer id) {
        //先进行查询是否存在
        RoleManagement rolemanagement = roleMapper.selectById(id);
        //如果不存在
        if (rolemanagement == null) {
            return "数据不存在，删除失败";
        } else {
            int delete = roleMapper.deleteById(id);
            return delete > 0 ? "删除成功" : "删除失败";
        }
    }

    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回结果字符串
     */
    @Override
    public String deleteRoleByFieldNameAndValue(String fieldName, String fieldValue) {
        //先进行查询是否存在
        RoleManagement rolemanagement = selectRoleByFieldNameAndValue(fieldName, fieldValue);
        //如果不存在，则不进行删除
        if (rolemanagement == null) {
            return "数据不存在，删除失败";
        } else {
            Map<String, Object> map = new HashMap<>(1);
            //如果这里不放入FieldName和FieldValue，会删除数据库所有的数据
            map.put(fieldName, fieldValue);
            int delete = roleMapper.deleteByMap(map);
            //如果删除成功就返回1，失败返回0
            return delete > 0 ? "删除成功" : "删除失败";
        }
    }

    /**
     * 通过Id修改数据
     *
     * @param role 要更新的Role
     * @return 返回结果字符串
     */
    @Override
    public String updateRoleById(RoleManagement role) {
        Integer id = role.getId();
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        if (selectRoleById(id) == null) {
            return "数据不存在,修改失败";
        } else {
            String updateDetection = roleAppService.updateDetection(id, roleName, roleDescribe);
            if (updateDetection != null) {
                return updateDetection;
            }
            int update = roleMapper.updateById(role);
            return update > 0 ? "修改成功" : "修改失败";
        }
    }


}
