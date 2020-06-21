package com.coderzoe.service.user;

import com.coderzoe.entity.User;

import java.util.List;

/**
 * @author yhs
 * @date 2020/6/18 14:37
 * @description
 */
public interface UserService {

    /**
     * @param userCode 用户代码
     * @param password 密码
     * @data: 2020/06/18 21:58
     * @author: yhs
     * @return: {@link User }
     * @description: 得到要登陆的用户
     */
    User login(String userCode,String password);

    /**
     * @param id       id
     * @param password 密码
     * @data: 2020/06/20 11:30
     * @author: yhs
     * @return: int
     * @description: 修改用户密码
     */
    boolean updateUserPassword(long id, String password);

    /**
     * @param userName 用户名
     * @param role     角色
     * @data: 2020/06/20 19:36
     * @author: yhs
     * @return: int
     * @description: 查询用户数量
     */
    int getUserCount(String userName,long role);

    /**
     * @param userName       用户名
     * @param role           角色
     * @param currentPageNum 当前页面num
     * @param pageSize       页面大小
     * @data: 2020/06/21 10:46
     * @author: yhs
     * @return: {@link List<User> }
     * @description: 按条件 分页查询用户
     */
    List<User> getUserListByPage(String userName,long role,int currentPageNum,int pageSize);
}
