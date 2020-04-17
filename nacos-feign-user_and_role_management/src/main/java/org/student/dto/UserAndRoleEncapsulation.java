package org.student.dto;

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
public class UserAndRoleEncapsulation {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Date date;
    private List<Integer> roleIdList;
}
