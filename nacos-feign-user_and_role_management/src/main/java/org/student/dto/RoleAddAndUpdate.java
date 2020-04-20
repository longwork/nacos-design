package org.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAddAndUpdate {
    private Integer roleId;
    private String roleName;
    private String roleDescribe;
    private List<Integer> userId;
}
