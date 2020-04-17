package org.student.entity;

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
    @TableId
    private Integer id;
    @TableField("rolename")
    private String roleName;
    @TableField("roledescribe")
    protected String roleDescribe;
}
