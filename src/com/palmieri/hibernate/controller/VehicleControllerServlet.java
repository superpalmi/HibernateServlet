package com.palmieri.hibernate.controller;

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

@WebServlet("/registerVehicle")
public class VehicleControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private VehicleDAO vehicleDAO;
    public void init(){
        this.vehicleDAO=new VehicleDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
        dispatcher.forward(request, response);
    }


}
