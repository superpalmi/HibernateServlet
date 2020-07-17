package com.palmieri.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
    private static String USERDETAIL_JSP="/user-detail.jsp";
    private static String INDEX_JSP="/index.jsp";

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
       String action=request.getParameter("action");
       User user = (User) request.getSession().getAttribute("user");
       String password1= request.getParameter("password1");
       String password2=  request.getParameter("password2");
        if(user!=null){
            if (action!=null){
                if(action.equalsIgnoreCase("edit")){
                    if (password1.equals(password2)) {
                        editUser(request, response);
                        forward = INDEX_JSP;
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Le password non corrispondono');");
                        out.println("location='user-edit.jsp';");
                        out.println("</script>");

                    }
                }else if (action.equalsIgnoreCase("insert")) {

                    if (password1.equals(password2)) {
                        insertUser(request, response);
                        forward = INDEX_JSP;
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Le password non corrispondono');");
                        out.println("location='user-edit.jsp';");
                        out.println("</script>");

                    }
                }
            }


        }


        RequestDispatcher view = request.getRequestDispatcher(forward);

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

                    if(user.getRole().equalsIgnoreCase("superuser")){
                        forward = SHOWALL_JSP;
                        request.setAttribute("users", userDao.getAllUsers());
                    }else {
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Utente non autorizzato');");
                        out.println("location='index.jsp';");
                        out.println("</script>");
                        forward = INDEX_JSP;

                    }

                } else if(action.equalsIgnoreCase("showUser")){
                    forward = USERDETAIL_JSP;
                    request.setAttribute("user", user);
                }else forward = INDEX_JSP;

                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
        } else if(user==null && action.equalsIgnoreCase("create")) {
            forward=("user-edit.jsp");

        }else if(user==null){

            forward=("user-login.jsp");
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        }else  forward = INDEX_JSP;

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }
        // leggo i dati presi dalla Form di registrazione/modifica
    private User readForm(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password1");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String role = request.getParameter("role");
        User user = new User();
        user.setUserName(userName);
        user.setPassword1(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);
        user.setRole(role);
        return user;


    }



    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        int id = Integer.parseInt(request.getParameter("userId"));

        //creo l'entità utente con i valori
        User user = readForm(request, response);
        user.setId(id);

        //passo al middleware DAO per la mappatura in hibernate
        userDao.updateUser(user);


    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = readForm(request, response);
        userDao.saveUser(user);


    }
}