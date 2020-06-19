package com.coderzoe.controller.user;

import com.coderzoe.entity.User;
import com.coderzoe.service.user.UserService;
import com.coderzoe.service.user.UserServiceImpl;
import com.coderzoe.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yhs
 * @date 2020/6/18 22:15
 * @description
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        UserService service = new UserServiceImpl();
        User user = service.login(userCode, userPassword);
        if(user != null && user.getUserPassword().equals(userPassword)){
            req.getSession().setAttribute(Constant.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            req.setAttribute("error","用户名或者密码不正确!!");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
