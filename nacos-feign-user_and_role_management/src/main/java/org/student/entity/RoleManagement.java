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
public class RoleManagement {
    private int id;
    private String roleName;
    protected String roleDescribe;
}
