package com.coderzoe.dao.role;

import com.coderzoe.dao.BaseDao;
import com.coderzoe.entity.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhs
 * @date 2020/6/18 14:33
 * @description
 */
public class RoleDaoImpl implements RoleDao{
    /**
     * @data: 2020/06/21 10:55
     * @author: yhs
     * @return: {@link List<Role> }
     * @description: 获取角色列表
     */
    @Override
    public List<Role> getRoleList(Connection connection) {
        List<Role> roleList = new ArrayList<>();
        String sql = "select * from smbms_role";
        Object[] params = new Object[0];
        try {
            ResultSet resultSet = BaseDao.executeQuery(connection, sql, params);
            while (resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getLong("id"));
                role.setRoleCode(resultSet.getString("roleCode"));
                role.setRoleName(resultSet.getString("roleName"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

}
