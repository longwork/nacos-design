package org.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.student.entity.RoleManagement;
import org.student.mapper.RoleManagementMapper;
import org.student.service.RoleManagementService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
@Service
public class RoleManagementServerImpl implements RoleManagementService {
    private static final int ROLE_DESCRIBE_LENGTH = 20;

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
     * @return 返回数值(1代表插入成功, 0代表插入失败, 1001代表修改成功, 1000代表修改失败, - 1代表不做任何动作算作插入失败)
     */
    @Override
    public String insertRole(RoleManagement role) {
        Integer id = role.getId();
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        String detection = detection(id, roleName, roleDescribe);
        if (detection != null) {
            return detection;
        }
        int insert = roleMapper.insert(role);
        return insert == 1 ? "插入成功" : "插入失败";
    }

    /**
     * 通过ID删除数据
     *
     * @param id 传入的主键ID
     * @return 返回数值(1代表成功, 0代表失败, - 1代表不存在算删除失败)
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
            return delete == 1 ? "删除成功" : "删除失败";
        }
    }

    /**
     * 通过FieldName和FieldValue删除数据
     *
     * @param fieldName  字段值
     * @param fieldValue 字段名
     * @return 返回数值(1代表成功, 0代表失败, - 1代表不存在算删除失败)
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
            return delete == 1 ? "删除成功" : "删除失败";
        }
    }

    /**
     * 通过Id修改数据
     *
     * @param role 要更新的Role
     * @return 返回数值(1代表修改成功, 0代表修改失败, 1001代表插入成功, 1000插入失败, - 1不做任何修改算修改失败)
     */
    @Override
    public String updateRoleById(RoleManagement role) {
        Integer id = role.getId();
        String roleName = role.getRoleName();
        String roleDescribe = role.getRoleDescribe();
        if (selectRoleById(id)==null){
            String detection = updateDetection(id, roleName, roleDescribe);
            if (detection != null) {
                return detection;
            }
            int insert = roleMapper.insert(role);
            return insert == 1 ? "数据不存在，已插入" : "插入失败";
        } else {
            String updateDetection = updateDetection(id, roleName, roleDescribe);
            if (updateDetection != null) {
                return updateDetection;
            }
            int update = roleMapper.updateById(role);
            return update == 1 ? "修改成功" : "修改失败";
        }
    }

    /**
     * 检测Id重复
     *
     * @param id 传入的id
     * @return true重复，false不重复
     */
    @Override
    public boolean idDetection(Integer id) {
        RoleManagement roleManagement = selectRoleById(id);
        return roleManagement != null;
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
        RoleManagement role = selectRoleByFieldNameAndValue("roleName", roleName);
        return role != null;
    }

    @Override
    public boolean roleDescribeRepetition(String roleDescribe) {
        RoleManagement role = selectRoleByFieldNameAndValue("roleDescribe", roleDescribe);
        return role != null;
    }

    /**
     * 检测属性合格
     *
     * @param id           传入的id
     * @param roleName     传入的roleName
     * @param roleDescribe 传入的roleDescribe
     * @return 返回不符合的语句
     */
    @Override
    public String detection(Integer id, String roleName, String roleDescribe) {
        if (idDetection(id)) {
            return "ID主键唯一，请换一个ID";
        }
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
        RoleManagement r = selectRoleById(id);
        RoleManagement roleName1 = selectRoleByFieldNameAndValue("roleName", roleName);
        RoleManagement roleDescribe1 = selectRoleByFieldNameAndValue("roleDescribe", roleDescribe);
        if (roleName1 != null && !roleName1.getRoleName().equals(r.getRoleName())) {
            return "角色名已存在";
        }
        if (roleDescribe.length() > ROLE_DESCRIBE_LENGTH) {
            return "角色描述太长";
        }
        if (roleDescribe1 != null && !roleDescribe1.getRoleDescribe().equals(r.getRoleDescribe())) {
            return "描述已存在";
        }
        return null;
    }
}
