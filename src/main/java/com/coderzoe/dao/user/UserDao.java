package com.coderzoe.dao.user;

import com.coderzoe.entity.User;

import java.sql.Connection;

/**
 * @author yhs
 * @date 2020/6/17 22:11
 * @description
 */
public interface UserDao {

    /**
     * @param connection 连接
     * @param userCode   用户代码
     * @data: 2020/06/18 21:57
     * @author: yhs
     * @return: {@link User }
     * @description: 得到要登陆的用户
     */
    User getLoginUser(Connection connection,String userCode);
}
