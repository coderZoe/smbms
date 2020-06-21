package com.coderzoe.dao.role;

import com.coderzoe.entity.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @author yhs
 * @date 2020/6/17 22:11
 * @description
 */
public interface RoleDao {
    /**
     * @data: 2020/06/21 10:54
     * @author: yhs
     * @return: {@link List<Role> }
     * @description: 获取角色列表
     */
    List<Role> getRoleList(Connection connection);
}
