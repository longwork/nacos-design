package org.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("usermanagement")
public class UserManagement {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "email")
    private String email;
    @TableField(value = "phone")
    private String phone;
    @TableId(value = "birth")
    private LocalDate birth;

    public UserManagement(String name, String email, String phone, LocalDate birth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
    }
}
