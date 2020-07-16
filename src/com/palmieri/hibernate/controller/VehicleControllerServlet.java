package com.palmieri.hibernate.controller;

import com.palmieri.hibernate.dao.UserDAO;
import com.palmieri.hibernate.dao.VehicleDAO;
import com.palmieri.hibernate.model.User;
import com.palmieri.hibernate.model.Vehicle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/VehicleControllerServlet")
public class VehicleControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String EDIT_JSP = "/vehicle-edit.jsp";
    private static String SHOWALL_JSP = "/vehicle-showall.jsp?action=showAll";
    private static String REGISTER_JSP="/vehicle-register.jsp";
    private static String LOGIN_JSP="/user-login.jsp";


    private VehicleDAO vehicleDao;
    public VehicleControllerServlet() throws ServletException {
        super();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            vehicleDao = new VehicleDAO();
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prendo l'utente della sessione
        User user = (User) request.getSession().getAttribute("user");

        //se l'user non è  nullo allora posso registrare e modificare i veicoli
            if (user != null) {
                //se action=edit allora modifico il veicolo già esistente
                if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("edit")) {

                    editVehicle(request, response);

                } else insertVehicle(request, response);
                request.setAttribute("vehicles", vehicleDao.getAllVehicles());
                RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);

                view.forward(request, response);
            } else {
                RequestDispatcher view = request.getRequestDispatcher(LOGIN_JSP);

                view.forward(request, response);
            }










    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");

        if(request.getSession().getAttribute("user")!=null) {

            if (action.equalsIgnoreCase("delete")) {
                forward = SHOWALL_JSP;
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                vehicleDao.deleteVehicle(vehicleId);
                request.setAttribute("vehicles", vehicleDao.getAllVehicles());

            } else if (action.equalsIgnoreCase("edit")) {

                if(user.getRole().equalsIgnoreCase("superuser")){
                    forward = EDIT_JSP;
                    int i = Integer.parseInt(request.getParameter("vehicleId"));
                    //editUser(request, response);
                    request.setAttribute("vehicleId", vehicleDao.getVehicle(i));
                }else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Utente non autorizzato');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                }



            } else if(action.equalsIgnoreCase("showAll")){
                forward = SHOWALL_JSP;
                request.setAttribute("vehicles", vehicleDao.getAllVehicles());

            }




        }else forward = LOGIN_JSP;

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    private Vehicle readForm(HttpServletRequest request, HttpServletResponse response){
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String plate = request.getParameter("plate");
        String type = request.getParameter("type");

        String regDate = request.getParameter("registrationDate");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date immdate = null;
        try {
            immdate = dateFormat.parse(regDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //creo l'entità veicolo con i valori
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setPlate(plate);
        vehicle.setImmdate(immdate);
        vehicle.setType(type);
        return vehicle;
    }

    private void editVehicle(HttpServletRequest request, HttpServletResponse response) {
       //leggo il veicolo da modificare dalla form
        Vehicle vehicle = readForm(request,response);
        //siccome è una modifica devo modificare il veicolo con quell'id preso dalla richiesta
        int id = Integer.parseInt(request.getParameter("vehicleId"));
        vehicle.setId(id);
        //passo al middleware DAO per la mappatura in hibernate
        vehicleDao.updateVehicle(vehicle);
    }

    private void insertVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prendo i valori dalla form di register
        Vehicle vehicle=readForm(request, response);
        //passo al middleware DAO per la mappatura in hibernate
        VehicleDAO.saveVehicle(vehicle);
    }


}
