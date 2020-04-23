package org.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.student.entity.UserManagement;

import java.util.List;

/**
 * @author Long
 * @date 10:18 2020-04-21 周二
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageSelect {
    /**
     * 当前页
     */
    private Long current;
    /**
     * 当前页条数
     */
    private Long size;
    /**
     * 总数
     */
    private Long total;
    /**
     * 查询到的列表集合
     */
    private List<UserManagement> records;
}
