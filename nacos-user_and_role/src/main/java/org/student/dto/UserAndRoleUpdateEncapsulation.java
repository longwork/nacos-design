package org.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Long
 * @date 14:17 2020-04-22 周三
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndRoleUpdateEncapsulation {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
