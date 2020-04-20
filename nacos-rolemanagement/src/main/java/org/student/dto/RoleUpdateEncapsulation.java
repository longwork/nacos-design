package org.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Long
 * @date 15:12 2020-04-20 周一
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateEncapsulation {
    private Integer id;
    private String roleName;
    private String roleDescribe;
}
