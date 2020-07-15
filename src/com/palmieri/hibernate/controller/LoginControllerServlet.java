package com.palmieri.hibernate.controller;

import com.palmieri.hibernate.dao.LoginDAO;
import com.palmieri.hibernate.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
    //fare logout controller
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        LoginDAO loginService = new LoginDAO();
        //se le password sono valide crea la sessione con l'utente corrente, altrimenti ritorna alla home
        boolean result = loginService.authenticateUser(userId, password);
        User user = loginService.getUserByUserName(userId);
        if(result == true){
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("id", user.getId());
            response.sendRedirect("index.jsp");

        }
        else
        {
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User o password non corrette');");
            out.println("location='user-login.jsp';");
            out.println("</script>");
        }


    }

    //aggiungere una get che ritorna alla index se si è già registrati

}
