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

        //inserisco i valori nel middleware DAO

        //String action = request.getParameter("action");
        if(request.getSession().getAttribute("user")!=null) {
            if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("edit")) {
                String action = request.getParameter("action");
                editVehicle(request, response);

            } else insertVehicle(request, response);
            request.setAttribute("vehicles", vehicleDao.getAllVehicles());
            RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);

            view.forward(request, response);
        }else{
            RequestDispatcher view = request.getRequestDispatcher(LOGIN_JSP);

            view.forward(request, response);
        }









    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        if(request.getSession().getAttribute("user")!=null) {
            if (action.equalsIgnoreCase("delete")) {
                forward = SHOWALL_JSP;
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                vehicleDao.deleteVehicle(vehicleId);
                request.setAttribute("vehicles", vehicleDao.getAllVehicles());

            } else if (action.equalsIgnoreCase("edit")) {
                forward = EDIT_JSP;
                int i = Integer.parseInt(request.getParameter("vehicleId"));
                //editUser(request, response);
                request.setAttribute("vehicleId", vehicleDao.getVehicle(i));
            } else if (action.equalsIgnoreCase("showAll")) {
                forward = SHOWALL_JSP;
                request.setAttribute("vehicles", vehicleDao.getAllVehicles());
            } else {
                forward = SHOWALL_JSP;
                request.setAttribute("vehicles", vehicleDao.getAllVehicles());
            }
        }else forward = LOGIN_JSP;

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    private void editVehicle(HttpServletRequest request, HttpServletResponse response) {
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String plate = request.getParameter("plate");
        String type = request.getParameter("type");
        String immdate = request.getParameter("registrationDate");
        int id = Integer.parseInt(request.getParameter("vehicleId"));
        //creo l'entità veicolo con i valori
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setPlate(plate);
        vehicle.setImmdate(immdate);
        vehicle.setType(type);

        //passo al middleware DAO per la mappatura in hibernate
        vehicleDao.updateVehicle(vehicle);
    }

    private void insertVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prendo i valori dalla form di register
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String plate = request.getParameter("plate");
        String type = request.getParameter("type");
        String immdate = request.getParameter("registrationDate");
        //creo l'entità veicolo con i valori
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setPlate(plate);
        vehicle.setImmdate(immdate);
        //passo al middleware DAO per la mappatura in hibernate
        VehicleDAO.saveVehicle(vehicle);
    }


}
