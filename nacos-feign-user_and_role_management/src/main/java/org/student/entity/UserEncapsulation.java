package org.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEncapsulation {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Date date;
}
