package com.coderzoe.service.user;

import com.coderzoe.entity.User;

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
}
