package com.coderzoe.controller.user;

import com.coderzoe.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yhs
 * @date 2020/6/19 20:14
 * @description
 */
public class LogOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除用户的Session
        req.getSession().removeAttribute(Constant.USER_SESSION);
        //返回登陆界面
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
}
