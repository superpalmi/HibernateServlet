package com.palmieri.hibernate.controller;

import com.palmieri.hibernate.dao.ReservationDAO;
import com.palmieri.hibernate.dao.VehicleDAO;
import com.palmieri.hibernate.model.Reservation;
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

@WebServlet("/ReservationControllerServlet")
public class ReservationControllerServlet extends HttpServlet {

    private static String EDIT_JSP = "/reservation-edit.jsp";
    private static String SHOWALL_JSP = "/reservation-showall.jsp?action=showAll";
    private static String REGISTER_JSP="/reservation-register.jsp";
    private ReservationDAO reservationDao;
    public ReservationControllerServlet() throws ServletException {
        super();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            reservationDao = new ReservationDAO();
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //inserisco i valori nel middleware DAO

        //String action = request.getParameter("action");
        if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("edit")){
            String action = request.getParameter("action");
            editReservation(request,response);

        }else insertReservation(request, response);
        request.setAttribute("reservations", reservationDao.getAllReservations());
        RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);

        view.forward(request, response);


        //inserisco i valori nella richiesta



    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("create")){
            forward = REGISTER_JSP;
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            request.setAttribute("vehicleId", vehicleId);
            //reservationDao.saveReservation(vehicleId);
            request.setAttribute("reservations", reservationDao.getAllReservations());

        }
        if (action.equalsIgnoreCase("delete")){
            forward = SHOWALL_JSP;
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            reservationDao.deleteReservation(reservationId);
            request.setAttribute("reservations", reservationDao.getAllReservations());

        } else if (action.equalsIgnoreCase("edit")){
            forward = EDIT_JSP;
            int i=Integer.parseInt(request.getParameter("reservationId"));
            //editUser(request, response);
            request.setAttribute("reservationId", reservationDao.getReservation(i));
        } else if (action.equalsIgnoreCase("showAll")){
            forward = SHOWALL_JSP;
            request.setAttribute("reservations", reservationDao.getAllReservations());
        }else if (action.equalsIgnoreCase("getuser")){
            forward = SHOWALL_JSP;

            int i=Integer.parseInt(request.getParameter("reservationId"));
            User user = reservationDao.getUser(i);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                // reading the user input
                out.append("<ul>");
                out.append("<li>" + user.getUserName() + " " + user.getPhone()
                        + "<br>Città:" + user.getCity()
                        + "<br>Age:" + user.getPhone() + "</li>");
                out.append("</ul>");
                //request.setAttribute("reservations", reservationDao.getUser(i));
                request.setAttribute("user", user);
            }

        }else if (action.equalsIgnoreCase("getvehicle")){

            int i=Integer.parseInt(request.getParameter("reservationId"));
            Vehicle vehicle = reservationDao.getVehicle(i);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                // reading the user input
                out.append("<ul>");
                out.append("<li>" + vehicle.getModel() + " " + vehicle.getBrand()
                        + "<br>Targa:" + vehicle.getPlate()
                        + "<br>Data di Immatricolazione:" + vehicle.getImmdate() + "</li>");
                out.append("</ul>");
                //request.setAttribute("reservations", reservationDao.getUser(i));
            }
        } else {
            forward = SHOWALL_JSP;
            request.setAttribute("reservations", reservationDao.getAllReservations());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);


    }

    private void editReservation(HttpServletRequest request, HttpServletResponse response) {
        String dataInizio = request.getParameter("dataInizio");
        String dataFine = request.getParameter("dataFine");
        int userId= Integer.parseInt(request.getParameter("userId"));
        int vehicleId= Integer.parseInt(request.getParameter("vehicleId"));
        int id = Integer.parseInt(request.getParameter("reservationId"));
        User user = reservationDao.getUser(userId);
        Vehicle vehicle= reservationDao.getVehicle(vehicleId);
        //creo l'entità reservation
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setDataInizio(dataInizio);
        reservation.setDataFine(dataFine);
        reservation.setUser(user);
        reservation.setVehicle(vehicle);
        reservationDao.updateReservation(reservation);




    }

    private void insertReservation(HttpServletRequest request, HttpServletResponse response) {

        String dataInizio = request.getParameter("dataInizio");
        String dataFine = request.getParameter("dataFine");
        int userId= Integer.parseInt(request.getParameter("userId"));
        int vehicleId= Integer.parseInt(request.getParameter("vehicleId"));
        User user = reservationDao.getUser(userId);
        Vehicle vehicle= reservationDao.getVehicle(vehicleId);
        //creo l'entità reservation
        Reservation reservation = new Reservation();
        reservation.setDataInizio(dataInizio);
        reservation.setDataFine(dataFine);
        reservation.setUser(user);
        reservation.setVehicle(vehicle);
        reservationDao.saveReservation(reservation);



    }
}
