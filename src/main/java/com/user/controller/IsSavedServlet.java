package com.user.controller;

import com.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/isSavedServlet")
public class IsSavedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserService userService = new UserService();
        PrintWriter out = response.getWriter();
        if(username==""){
            out.println("用户名不能为空");
        }else {
            boolean isSaved = userService.isSaved(username);
            if(isSaved){
                out.println("用户名已存在");
            }else {
                out.println("用户名可用");
            }
            //System.out.println(username+isSaved);
        }
        out.close();

    }
}
