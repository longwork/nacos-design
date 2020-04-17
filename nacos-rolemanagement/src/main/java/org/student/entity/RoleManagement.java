package org.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rolemanagement")
public class RoleManagement {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("rolename")
    private String roleName;
    @TableField("roledescribe")
    private String roleDescribe;

    public RoleManagement(String roleName, String roleDescribe) {
        this.roleName = roleName;
        this.roleDescribe = roleDescribe;
    }
}
