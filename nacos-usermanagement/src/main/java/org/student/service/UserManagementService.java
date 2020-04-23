package org.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.student.entity.UserManagement;

/**
 * @author Administrator
 */
public interface UserManagementService extends IService<UserManagement> {
    /**
     * 检测传入的Name重复
     *
     * @param name 传入的Name
     * @return true为数据库没有这个值，false代表数据库中存在这个值
     */
    boolean nameRepetition(String name);

    /**
     * 检测邮箱重复
     *
     * @param email 传入的邮箱
     * @return true代表重复，false不重复
     */
    boolean emailRepetition(String email);

    /**
     * 检测手机号重复
     *
     * @param phone 传入的手机号
     * @return true代表重复，false不重复
     */
    boolean phoneRepetition(String phone);
}
