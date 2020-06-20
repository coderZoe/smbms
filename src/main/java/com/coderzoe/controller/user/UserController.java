package com.coderzoe.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.coderzoe.entity.User;
import com.coderzoe.service.user.UserService;
import com.coderzoe.service.user.UserServiceImpl;
import com.coderzoe.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yhs
 * @date 2020/6/20 12:28
 * @description 实现Servlet复用
 */
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("savepwd".equals(req.getParameter("method"))){
            this.updatePassword(req,resp);
        }else if("pwdmodify".equals(req.getParameter("method"))){
            this.verificationPassword(req,resp);
        }
    }

    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("newpassword");
        //从Session里拿用户的id
        User user = (User) req.getSession().getAttribute(Constant.USER_SESSION);
        long id = user.getId();
        UserService userService = new UserServiceImpl();
        boolean result = userService.updateUserPassword(id, password);

        //密码更新成功，移除Session
        if(result){
            req.setAttribute("message","密码修改成功，请退出重新登录");
            req.getSession().removeAttribute(Constant.USER_SESSION);
        }else {
            req.setAttribute("message","密码修改失败");
        }

        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    public void verificationPassword(HttpServletRequest req, HttpServletResponse resp){
        String oldpassword = req.getParameter("oldpassword");
        User user = (User) req.getSession().getAttribute(Constant.USER_SESSION);

        Map<String,String> map = new HashMap<>();
        //用户过期
        if(user==null){
            map.put("result","sessionerror");
        }else {
            //密码正确
            if(oldpassword.equals(user.getUserPassword())){
                map.put("result","true");
            }else if(oldpassword==null||oldpassword.length()==0){   //密码为空
                map.put("result","error");
            }else { //密码输入错误
                map.put("result","false");
            }
        }

        //告诉浏览器返回的信息是JSON
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            //将Map转为Json，写给客户端
            writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(map));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert writer != null;
            writer.close();
        }
    }
}
