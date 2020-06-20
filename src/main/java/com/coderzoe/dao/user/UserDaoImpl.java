package com.coderzoe.dao.user;

import com.coderzoe.dao.BaseDao;
import com.coderzoe.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yhs
 * @date 2020/6/18 14:33
 * @description
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String userCode) {
        User user = null;
        try {
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            ResultSet resultSet = BaseDao.executeQuery(connection, sql, params);
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getLong("userRole"));
                user.setCreatedBy(resultSet.getLong("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getLong("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                return user;
            }
            BaseDao.release(resultSet,null,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUserPassword(Connection connection, long id, String password) {
        try {
            String sql = "update smbms_user set userPassword =? where id = ?";
            Object[] params ={password,id};
            return BaseDao.executeUpdate(connection,sql,params);
        } catch (SQLException e) {
            BaseDao.release(null,connection);
            e.printStackTrace();
            return -1;
        }
    }
}
