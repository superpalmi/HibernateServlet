package com.palmieri.hibernate.controller;

import com.palmieri.hibernate.dao.LoginDAO;
import com.palmieri.hibernate.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
    //fare logout controller
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        LoginDAO loginService = new LoginDAO();
        boolean result = loginService.authenticateUser(userId, password);
        User user = loginService.getUserByUserName(userId);
        if(result == true){
            request.getSession().setAttribute("user", user);
            response.sendRedirect("index.jsp");
        }
        else{
            response.sendRedirect("error.jsp");
        }


    }

}
