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
    private String roleDescribe;

    public RoleManagement(String roleName, String roleDescribe) {
        this.roleName = roleName;
        this.roleDescribe = roleDescribe;
    }
}
