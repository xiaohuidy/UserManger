package com.user.controller;

import com.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/toLoginServlet")
public class ToLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cs = request.getCookies();
        UserService userService = new UserService();
        if(cs!=null){
            for(int i = cs.length - 1;i >= 0;i--) {
                Cookie c = cs[i];
                //out.println(c.getName()+","+c.getValue()+"<br/>");
                if (userService.canLogin(c.getName(), c.getValue())) {
                    System.out.println("ok");
                    //request.setAttribute("uname",c.getName());
                    Cookie c1 = new Cookie(c.getName(),c.getValue());
                    c1.setMaxAge(2*7*24*60*60);
                    response.addCookie(c1);
                    HttpSession session = request.getSession();
                    session.setAttribute("User",userService.getOne(c1.getName(), c1.getValue()));
                    request.getRequestDispatcher("/listUserServlet").forward(request, response);
                    //response.sendRedirect("/UserMange/listUserServlet");
                    break;
                } else {
                    if (c.equals(cs[0])) {
                        response.sendRedirect("/UserManage/login.jsp");
                        break;
                    }
                }
            }
        }
        /*if(!flag){
            response.sendRedirect("/UserManage/login.jsp");
        }*/
    }
}
