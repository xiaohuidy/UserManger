package com.user.controller;

import com.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/deleteCheckedServlet")
public class DeleteCheckedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        //int page = Integer.parseInt(request.getParameter("page"));
        String idList[] = request.getParameter("idList").split(",");
        List users = null;
        for(int i = 0;i < idList.length;i++){
            String id = idList[i];
            users = userService.del(Integer.parseInt(id));
        }

        //String id = request.getParameter("id");

        request.setAttribute("users",users);
        request.getRequestDispatcher("/listUserServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
