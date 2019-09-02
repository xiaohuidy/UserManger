package com.user.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ToVCodeServlet")
public class ToVCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vcode = request.getParameter("vcode");
        HttpSession session = request.getSession();
        String rand = session.getAttribute("vcode").toString();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(!vcode.equals(rand)){
            response.sendRedirect("/UserManage/login.jsp");
        }else{

        }


        out.close();
    }
}
