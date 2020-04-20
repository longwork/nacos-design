package org.student.appservice;

import org.student.dto.UserAndRoleAddEncapsulation;
import org.student.entity.UserAndRole;

/**
 * @author Long
 * @date 15:36 2020-04-20 周一
 */
public interface UserAndRoleAppService {
    /**
     * 将前端dto转化为entity实体类
     *
     * @param ur 转换类
     * @return 返回一个UserAndRole
     */
    UserAndRole entityTransaction(UserAndRoleAddEncapsulation ur);

    /**
     * 判断插入的数据是否合格
     *
     * @param ur 要插入的数据
     * @return true代表合格，false不合格
     */
    boolean detection(UserAndRole ur);
}
