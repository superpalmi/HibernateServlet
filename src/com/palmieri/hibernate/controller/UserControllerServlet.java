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
import com.palmieri.hibernate.model.Reservation;
import com.palmieri.hibernate.model.User;
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    private static String EDIT_JSP = "/user-edit.jsp";
    private static String SHOWALL_JSP = "/user-showall.jsp?action=showAll";
    private static String REGISTER_JSP="/user-register.jsp";

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
       String forward ="";
        if(request.getSession().getAttribute("user")!=null){

            if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("edit")){
                String action = request.getParameter("action");
                editUser(request,response);
                request.setAttribute("users", userDao.getAllUsers());
                forward=SHOWALL_JSP;

            }
        } else  insertUser(request, response);
        forward=("index.jsp");
        RequestDispatcher view = request.getRequestDispatcher(forward);
       // response.sendRedirect("user-showall.jsp");

        view.forward(request, response);




    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        if(user!=null) {
            if (action != null) {
                if (action.equalsIgnoreCase("delete")) {
                    forward = SHOWALL_JSP;
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    userDao.deleteUser(userId);
                    request.setAttribute("users", userDao.getAllUsers());

                } else if (action.equalsIgnoreCase("edit")) {
                    forward = EDIT_JSP;
                    int i = Integer.parseInt(request.getParameter("userId"));
                    //editUser(request, response);
                    request.setAttribute("userId", userDao.getUser(i));
                } else if (action.equalsIgnoreCase("showAll")) {
                    forward = SHOWALL_JSP;
                    request.setAttribute("users", userDao.getAllUsers());
                } else {
                    forward = SHOWALL_JSP;
                    request.setAttribute("users", userDao.getAllUsers());
                }

                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
        } else {
            forward=("user-login.jsp");
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        }
    }



    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password1");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String role = request.getParameter("role");
        int id = Integer.parseInt(request.getParameter("userId"));

        //creo l'entità utente con i valori
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword1(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);
        user.setRole(role);


        //passo al middleware DAO per la mappatura in hibernate
        userDao.updateUser(user);


    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password1");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String role = request.getParameter("role");
        //boolean auth = request.getParameter("auth");
        //creo l'entità utente con i valori
        User user = new User();
        //Reservation reservation=new Reservation();
        user.setUserName(userName);
        user.setPassword1(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);
        user.setRole(role);
        //user.setReservation(reservation);
        //passo al middleware DAO per la mappatura in hibernate
        userDao.saveUser(user);


        //RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);
        //request.setAttribute("users", userDao.getAllUsers());
        //view.forward(request, response);
       //


    }
}