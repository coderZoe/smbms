package com.coderzoe.service.user;

import com.coderzoe.dao.BaseDao;
import com.coderzoe.dao.user.UserDao;
import com.coderzoe.dao.user.UserDaoImpl;
import com.coderzoe.entity.User;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author yhs
 * @date 2020/6/18 14:37
 * @description
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection connection = BaseDao.getConnection();
        return userDao.getLoginUser(connection, userCode);
    }

    @Override
    public boolean updateUserPassword(long id, String password) {
        Connection connection = BaseDao.getConnection();
        int result = userDao.updateUserPassword(connection, id, password);
        if(result>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int getUserCount(String userName, long role) {
        Connection connection = BaseDao.getConnection();
        return userDao.getUserCount(connection, userName, role);
    }

    @Override
    public List<User> getUserListByPage(String userName, long role, int currentPageNum, int pageSize) {
        Connection connection = BaseDao.getConnection();
        return userDao.getUserList(connection,userName,role,currentPageNum,pageSize);
    }

    /**
     * @data: 2020/06/18 22:12
     * @author: yhs
     * @return:
     * @description: 单元测试
     */
    @Test
    public void test(){
        System.out.println(getUserListByPage(null,0,1,10));
    }
}
