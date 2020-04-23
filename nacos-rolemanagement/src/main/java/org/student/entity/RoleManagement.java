package org.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    public boolean roleNameDetection() {
        String pattern = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(this.roleName);
        return !m.matches();
    }
}
