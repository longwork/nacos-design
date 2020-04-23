package org.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @TableField(value = "birth")
    private LocalDate birth;

    /**
     * 预编译姓名检测
     */
    private static final Pattern NAME_REGEX = Pattern.compile("[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+");
    /**
     * 预编译邮箱检测
     */
    private static final Pattern EMAIL_REGEX = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");
    /**
     * 预编译手机号检测
     */
    private static final Pattern PHONE_REGEX = Pattern.compile("0?(13|14|15|18|17)[0-9]{9}");

    public UserManagement(String name, String email, String phone, LocalDate birth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
    }

    /**
     * 检测传入的字符串是否为用户名(不是以特殊字符开头)
     *
     * @return true代表传入的是用户名, false代表传入的不是用户名
     */
    public boolean nameDetection() {
        Matcher m = NAME_REGEX.matcher(this.name);
        return !m.matches();
    }

    /**
     * 检测传入的字符串是否为邮箱
     *
     * @return true代表传入的是邮箱, false代表传入的不是邮箱
     */
    public boolean emailDetection() {
        Matcher m = EMAIL_REGEX.matcher(this.email);
        return !m.matches();
    }

    /**
     * 检测传入的字符串是否为手机号码
     *
     * @return true代表传入的是手机号, false代表传入的不是手机号
     */
    public boolean phoneDetection() {
        Matcher m = PHONE_REGEX.matcher(this.phone);
        return !m.matches();
    }

    /**
     * 判断传入的字符串转化为时间之后是否是在当前时间之前
     *
     * @return 是不是在当前时间之前
     */
    public boolean birthDetection() {
        LocalDate localDate = LocalDate.now();
        int compare = localDate.compareTo(this.birth);
        return compare <= 0;
    }
}
