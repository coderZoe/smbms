package com.coderzoe.service.role;

import com.coderzoe.dao.BaseDao;
import com.coderzoe.dao.role.RoleDao;
import com.coderzoe.dao.role.RoleDaoImpl;
import com.coderzoe.entity.Role;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author yhs
 * @date 2020/6/18 14:37
 * @description
 */
public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection connection = BaseDao.getConnection();
        return roleDao.getRoleList(connection);
    }

    @Test
    public void test(){
        System.out.println(this.getRoleList());
    }
}

