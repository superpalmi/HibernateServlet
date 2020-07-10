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
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    private static String EDIT_JSP = "/edit.jsp";
    private static String SHOWALL_JSP = "/showall.jsp";

    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public UserControllerServlet() throws ServletException {
        super();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            userDao = new UserDAO();
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }



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

        RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);
        request.setAttribute("users", userDao.getAllUsers());
        view.forward(request, response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            forward = SHOWALL_JSP;
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(userId);
            request.setAttribute("users", userDao.getAllUsers());

        } else if (action.equalsIgnoreCase("edit")){
            forward = EDIT_JSP;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userDao.getUser(userId);
            userDao.updateUser(user);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("showAll")){
            forward = SHOWALL_JSP;
            request.setAttribute("users", userDao.getAllUsers());
        }else if(action.equalsIgnoreCase("insert")){
            forward= 

        }


        else {
            forward = EDIT_JSP;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    /*
    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = userDao.readUsers();
        request.setAttribute("USER_LIST", users);
        //System.out.println("<li>" + u.getUserName() + " "+ u.getEmail() + " "+ u.getCity() + " "+ u.getPhone()+ "<li>");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);

    }*/
}