package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listUserServlet")
public class ListUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        /*UserService userService = new UserService();
        List users = userService.getAll();
        request.setAttribute("length",users.size());
        int page = 3;
        request.setAttribute("page",page);
        Object nowPage = request.getAttribute("nowPage");
        if(nowPage==null){
            request.setAttribute("nowPage",0);
            request.setAttribute("endPage",2);
        }else {
            request.setAttribute("nowPage",Integer.parseInt(nowPage.toString())-1);
            request.setAttribute("endPage",Integer.parseInt(nowPage.toString())+page-1);
        }
        /*List<User> userss = null;
        for(int i = nowPage - 1;i < users.size();i++){
            if(i < nowPage+page-1){
               userss.add((User)users.get(i));
            }else{
                break;
            }

        }*/

       /* request.setAttribute("users",users);
        System.out.println(users.size());
        request.getRequestDispatcher("/index.jsp").forward(request,response);*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String uname = request.getAttribute("uname")==null?null:request.getAttribute("uname").toString();
        //request.setAttribute("uname",uname);
        String pag = request.getParameter("page");
        UserService userService = new UserService();
        HttpSession session  = request.getSession();
        int PAGE_SIZE = 4;
        String pageSize = request.getParameter("pageSize");
        if(pageSize!=null){
            PAGE_SIZE = Integer.parseInt(pageSize);
            session.setAttribute("pageSize",PAGE_SIZE);
        }else{
            session.setAttribute("pageSize",PAGE_SIZE);
        }

        List users = null;
        int page = pag==null?1:Integer.parseInt(pag);
        users = userService.getPagedUsers(page,PAGE_SIZE);
        request.setAttribute("users",users);
        request.setAttribute("curPage",page);
        int counts =  userService.getCounts();
        int lastPage = 0;
        lastPage = counts/PAGE_SIZE;
        if(counts % PAGE_SIZE != 0 ) {
            lastPage++;
        }
        request.setAttribute("lastPage",lastPage);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
