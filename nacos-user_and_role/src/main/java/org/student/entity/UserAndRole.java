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
@TableName("userandrole")
public class UserAndRole {
    @TableId
    private Integer id;
    @TableField("userid")
    private Integer userId;
    @TableField("roleid")
    private Integer roleId;
}
