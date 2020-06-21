package com.coderzoe.dao.user;

import com.coderzoe.dao.BaseDao;
import com.coderzoe.entity.User;
import com.mysql.jdbc.StringUtils;

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

    /**
     * @param connection 连接
     * @param userName   用户名
     * @param role       角色
     * @data: 2020/06/20 18:11
     * @author: yhs
     * @return: int
     * @description: 根据用户名或者角色查询用户总数
     */
    @Override
    public int getUserCount(Connection connection, String userName, long role) {

        List<Object> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id ");
        if(!StringUtils.isNullOrEmpty(userName)){
            sql.append("and u.userName like ? ");
            list.add("%"+userName+"%");
        }
        if(role>0){
            sql.append("and u.userRole = ?");
            list.add(role);
        }
        String sql2 = sql.toString();
        Object[] params = list.toArray();

        try {
            ResultSet resultSet = BaseDao.executeQuery(connection, sql2, params);
            if(resultSet.next()){
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param connection     连接
     * @param userName       用户名
     * @param role           角色
     * @param currentPageNum 当前页面num
     * @param pageSize       页面大小
     * @data: 2020/06/21 10:44
     * @author: yhs
     * @return: {@link List<User> }
     * @description: 按分页显示当前用户列表信息
     */
    @Override
    public List<User> getUserList(Connection connection, String userName, long role, int currentPageNum, int pageSize) {
        List<User> userList = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
        if(!StringUtils.isNullOrEmpty(userName)){
            sql.append(" and u.userName like ?");
            list.add("%"+userName+"%");
        }
        if(role>0){
            sql.append(" and u.userRole = ?");
            list.add(role);
        }
        sql.append(" order by creationDate DESC limit ?,?");
        currentPageNum = (currentPageNum-1)*pageSize;
        list.add(currentPageNum);
        list.add(pageSize);
        String sql2 = sql.toString();
        Object[] params = list.toArray();

        try {
            ResultSet resultSet = BaseDao.executeQuery(connection, sql2, params);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setUserRole(resultSet.getLong("userRole"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
