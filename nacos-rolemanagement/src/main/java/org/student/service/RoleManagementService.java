package org.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.student.entity.RoleManagement;

/**
 * @author Administrator
 */
public interface RoleManagementService extends IService<RoleManagement> {
    /**
     * 检测用户名是否存在
     *
     * @param roleName 传入的Name
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    boolean roleNameRepetition(String roleName);

    /**
     * 检测描述重复
     *
     * @param roleDescribe 传入的roleDescribe
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    boolean roleDescribeRepetition(String roleDescribe);
}
