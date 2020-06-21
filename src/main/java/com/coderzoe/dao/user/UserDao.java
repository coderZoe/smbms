package com.coderzoe.dao.user;

import com.coderzoe.entity.User;

import java.sql.Connection;
import java.util.List;

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

    /**
     * @data: 2020/06/20 11:22
     * @author: yhs
     * @return: int
     * @description: 修改用户密码
     */
    int updateUserPassword(Connection connection,long id, String password);

    /**
     * @param connection 连接
     * @param userName   用户名
     * @param role       角色
     * @data: 2020/06/20 18:11
     * @author: yhs
     * @return: int
     * @description: 根据用户名或者角色查询用户总数
     */
    int getUserCount(Connection connection,String userName,long role);

    /**
     * @param connection     连接
     * @param userName       用户名
     * @param role           角色
     * @param currentPageNum 当前页面num
     * @param pageSize       页面大小
     * @data: 2020/06/21 10:27
     * @author: yhs
     * @return: {@link List<User> }
     * @description: 按分页显示当前用户列表信息
     */
    List<User> getUserList(Connection connection,String userName,long role,int currentPageNum,int pageSize);
}
