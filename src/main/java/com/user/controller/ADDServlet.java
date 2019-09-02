package com.user.controller;


import com.user.entity.User;
import com.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/addUserServlet")
public class ADDServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        User user = new User();
        String username = request.getParameter("username");
        user.setName(username);
        String pwd = request.getParameter("pwd");
        user.setPwd(pwd);
        String age = request.getParameter("age");
        user.setAge(Integer.parseInt(age));
        String birthday = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthday(sdf.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        /*response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h2>增加用户成功</h2>");
        out.println("user"+user);*/
        UserService userService = new UserService();
        userService.save(user);
        //request.setAttribute("uname",username);
        //请求转发
        request.getRequestDispatcher("/listUserServlet").forward(request,response);
        System.out.println("user:"+user);
        //out.close();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
