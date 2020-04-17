package org.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEncapsulation1 {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Date date;
    List<Integer> roleIdList;
}
