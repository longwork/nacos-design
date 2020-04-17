package org.student.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("usermanagement")
public class UserManagement {
    @TableId
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "email")
    @Email
    private String email;
    @TableField(value = "phone")
    private String phone;
    @Past
    @TableId(value = "birth")
    private LocalDate birth;
}
