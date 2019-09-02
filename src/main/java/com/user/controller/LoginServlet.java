package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        User testuser = new User();
        String username = request.getParameter("username");
        testuser.setName(username);
        String pwd = request.getParameter("pwd");
        testuser.setPwd(pwd);
        String autoLogin = request.getParameter("autoLogin");
        String vcode = request.getParameter("vcode");
        String rand = session.getAttribute("rand").toString();
        session.setAttribute("testuser",testuser);
        session.setAttribute("err",null);
        if(!vcode.equals(rand)){
            session.setAttribute("err","验证码错误");
            response.sendRedirect("/UserManage/login.jsp");
        }else{
            if(userService.canLogin(username,pwd)){
                //request.setAttribute("uname",username);
                if(autoLogin!=null){
                    Cookie c1 = new Cookie(username,pwd);
                    c1.setMaxAge(2*7*24*60*60);
                    response.addCookie(c1);
                    session.setAttribute("User",userService.getOne(username,pwd));
                }
                request.getRequestDispatcher("/listUserServlet").forward(request,response);
            }else{
            /*PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            out.println("<h2>登录失败</h2>");*/
                session.setAttribute("err","账号密码错误");
                response.sendRedirect("/UserManage/login.jsp");
            }
        }

        /*Cookie[] cs = request.getCookies();
        UserService userService = new UserService();
        if(cs==null){

        }else {
            for(Cookie c:cs){
                //out.println(c.getName()+","+c.getValue()+"<br/>");
                if(userService.canLogin(c.getName(),c.getValue())){
                    request.getRequestDispatcher("/listUserServlet").forward(request,response);
                    break;
                }else{
                    if(c.equals(cs[cs.length-1])){
                        if(userService.canLogin(username,pwd)){
                            request.setAttribute("uname",username);
                            request.getRequestDispatcher("/listUserServlet").forward(request,response);
                            break;
                        }else{
                            PrintWriter out = response.getWriter();
                            response.setContentType("text/html;charset=utf-8");
                            out.println("<h2>登录失败</h2>");
                            break;
                        }
                    }
                }
            }

        }*/



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
