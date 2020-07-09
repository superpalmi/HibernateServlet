package com.palmieri.hibernate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.palmieri.hibernate.dao.UserDAO;
import com.palmieri.hibernate.model.User;
@WebServlet("/registerUser")
public class UserControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDao = new UserDAO();



    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //prendo i valori dalla form di register
        String userName = request.getParameter("userName");
        String password = request.getParameter("password1");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        //creo l'entità utente con i valori
        User user = new User();
        user.setUserName(userName);
        user.setPassword1(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);
        //passo al middleware DAO per la mappatura in hibernate
        userDao.saveUser(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
        dispatcher.forward(request, response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        listUsers(request, response);




    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = userDao.readUsers();
        request.setAttribute("USER_LIST", users);
        //System.out.println("<li>" + u.getUserName() + " "+ u.getEmail() + " "+ u.getCity() + " "+ u.getPhone()+ "<li>");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);

    }
}