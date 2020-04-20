package org.student.appservice;

import org.student.dto.RoleAddEncapsulation;
import org.student.dto.RoleUpdateEncapsulation;
import org.student.entity.RoleManagement;

/**
 * @author Long
 * @date 15:07 2020-04-20 周一
 */
public interface RoleManagementAppService {
    /**
     * 将前端传来的dto类转换为实体类
     *
     * @param role 插入的RoleAddEncapsulation实体类
     * @return 返回的RoleManagement
     */
    RoleManagement entityTransaction(RoleAddEncapsulation role);

    /**
     * 将前端传来的dto类转换为实体类
     *
     * @param role 修改的RoleAddEncapsulation实体类
     * @return 返回的RoleManagement
     */
    RoleManagement entityTransaction(RoleUpdateEncapsulation role);

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @param roleName 传入的用户名
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    boolean roleNameDetection(String roleName);

    /**
     * 检测用户名是否存在
     *
     * @param name 传入的Name
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    boolean roleNameRepetition(String name);

    /**
     * 检测描述重复
     *
     * @param roleDescribe 传入的roleDescribe
     * @return true为数据库存在这个值，false代表数据库中没有这个值
     */
    boolean roleDescribeRepetition(String roleDescribe);

    /**
     * 检测属性合格
     *
     * @param role 传入的RoleManagement
     * @return 返回不符合的语句
     */
    String detection(RoleManagement role);

    /**
     * 检测修改数据是否合格
     *
     * @param id           传入的id
     * @param roleName     传入的roleName
     * @param roleDescribe 传入的roleDescribe
     * @return 返回不符合的语句
     */
    String updateDetection(Integer id, String roleName, String roleDescribe);
}
