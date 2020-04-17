package org.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResult {
    private int id;
    private String roleName;
    private String roleDescribe;
    private int count;
}
