package com.palmieri.hibernate.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LogoutControllerServlet")
public class LogoutControllerServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("home.jsp").include(request, response);
        request.setAttribute("message", "log out effettuato");

        HttpSession session=request.getSession();
        session.invalidate();





    }
}
