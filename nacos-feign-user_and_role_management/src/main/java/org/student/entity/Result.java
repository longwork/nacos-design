package org.student.entity;

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
public class Result {
    private int id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birth;
    private String roleName;
}
