package com.palmieri.hibernate.controller;

import com.palmieri.hibernate.dao.ReservationDAO;
import com.palmieri.hibernate.dao.UserDAO;
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

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/ReservationControllerServlet")
public class ReservationControllerServlet extends HttpServlet {

    private static String EDIT_JSP = "/reservation-edit.jsp";
    private static String SHOWALL_JSP = "/reservation-showall.jsp?action=showAll";
    private static String LOGIN_JSP="/user-login.jsp";
    private ReservationDAO reservationDao;
    private VehicleDAO vehicleDAO;
    private UserDAO userDAO;

    public ReservationControllerServlet() throws ServletException {
        super();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            reservationDao = new ReservationDAO();
            vehicleDAO = new VehicleDAO();
            userDAO=new UserDAO();

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }
        //da semplificare
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation res=readForm(request, response);
        Date dataInizio = res.getDataInizio();
        Date dataFine=res.getDataFine();
        String action=request.getParameter("action");
        String vehicleId = request.getParameter("vehicleId");

        User user = (User) request.getSession().getAttribute("user");
        if(user!=null) {
            boolean error;
            if((dataInizio!=null)&&(dataFine!=null)) {
                if (action != null && action.equalsIgnoreCase("edit")) {
                    if (checkDate(request, response) && checkVehicle(request, response)) {
                        editReservation(request, response);
                        request.setAttribute("reservations", user.getReservations());
                        RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);
                        view.forward(request, response);
                    } else try (PrintWriter out = response.getWriter()) {
                        request.setAttribute("action", "edit");
                        request.setAttribute("reservationId", request.getParameter("reservationId"));
                        request.setAttribute("message", "Veicolo non disponibile nelle date scelte");
                        RequestDispatcher view = request.getRequestDispatcher(EDIT_JSP);

                        view.forward(request, response);

                    }


                } else if (action != null && action.equalsIgnoreCase("create") && vehicleId != null) {

                    if (user.getReservations() == null) {
                        insertReservation(request, response);
                        request.setAttribute("reservations", user.getReservations());
                        RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);
                        view.forward(request, response);

                    } else if (checkDate(request, response) && checkVehicle(request, response)) {

                       insertReservation(request, response);
                        request.setAttribute("reservations", user.getReservations());
                        RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);

                        view.forward(request, response);

                    } else try (PrintWriter out = response.getWriter()) {
                        request.setAttribute("action", "create");
                        request.setAttribute("vehicleId", vehicleId);
                        request.setAttribute("message", "Veicolo non disponibile nelle date scelte");
                        RequestDispatcher view = request.getRequestDispatcher("reservation-edit.jsp");

                        view.forward(request, response);


                    }
                }
            }else {

                if(action.equalsIgnoreCase("create")) {
                    request.setAttribute("action", "create");
                    request.setAttribute("vehicleId", vehicleId);
                }else {
                    request.setAttribute("action", "edit");
                    request.setAttribute("reservationId", request.getParameter("reservationId"));

                }

                request.setAttribute("message", "Le date non possono essere lasciate in bianco");
                RequestDispatcher view = request.getRequestDispatcher(EDIT_JSP);
                view.forward(request, response);
            }




        }else {
            RequestDispatcher view = request.getRequestDispatcher(LOGIN_JSP);
        }





    }

    private boolean checkDate(HttpServletRequest request, HttpServletResponse response){

        User user = (User) request.getSession().getAttribute("user");
        Reservation res = readForm(request,response);

        List<Reservation> userReservations = user.getReservations();

        boolean result = checkReservation(res, userReservations);
        return result;


    }

    private boolean checkReservation(Reservation res, List<Reservation> reservations){
        boolean result = false;

        Date dataInizio = res.getDataInizio();
        Date dataFine = res.getDataFine();


        for (int i=0; i<reservations.size(); i++){
            Date dI =reservations.get(i).getDataInizio();
            Date dF = reservations.get(i).getDataFine();

            if(((dataInizio.after(dI) && dataInizio.before(dF)) || (dataFine.after(dI) && dataFine.before(dF)))||((dataInizio.equals(dI))&&(dataFine.equals(dF)))){
                return result;

            }


        }
        return result = true;



    }

    private boolean checkVehicle(HttpServletRequest request, HttpServletResponse response){
        Vehicle vehicle;
        List<Reservation> vehicleReservations;
        boolean result = false;
        Reservation res = readForm(request, response);
        if(request.getParameter("action").equalsIgnoreCase("create")){
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            vehicle=vehicleDAO.getVehicle(vehicleId);
            vehicleReservations = vehicle.getReservations();
            result=checkReservation(res, vehicleReservations);
            return result;


        }else if(request.getParameter("action").equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("reservationId"));
            res = reservationDao.getReservation(id);
            vehicle = res.getVehicle();
            vehicleReservations = vehicle.getReservations();
            result = checkReservation(res, vehicleReservations);
            return result;
        }

        return result = true;


        }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        if(user!=null) {

            if (action.equalsIgnoreCase("create")) {
                forward = EDIT_JSP;
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                request.setAttribute("action", "create");
                request.setAttribute("vehicleId", vehicleId);
                request.setAttribute("message", "");

            } else if (action.equalsIgnoreCase("delete")) {
                int reservationId = Integer.parseInt(request.getParameter("reservationId"));

                if(user.getRole().equalsIgnoreCase("superuser")) {
                    forward = SHOWALL_JSP;

                    reservationDao.deleteReservation(reservationId);
                    request.setAttribute("reservations", reservationDao.getAllReservations());
                }else{

                    forward = SHOWALL_JSP;

                    reservationDao.deleteReservation(reservationId);
                    request.setAttribute("reservations", reservationDao.getAllReservations());


                }

            } else if (action.equalsIgnoreCase("edit")) {
                forward = EDIT_JSP;
               int i = Integer.parseInt(request.getParameter("reservationId"));
               Reservation res = reservationDao.getReservation(i);
                request.setAttribute("message", "");
                request.setAttribute("reservationId", res.getId());


            } else if (action.equalsIgnoreCase("showAll")) {
                if(user.getRole().equalsIgnoreCase("superuser")){
                    forward = SHOWALL_JSP;
                    request.setAttribute("reservations", reservationDao.getAllReservations());
                }else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Utente non autorizzato');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                }
            }
        }else forward=LOGIN_JSP;


        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);


    }

    private Reservation readForm(HttpServletRequest request, HttpServletResponse response){
        String StartTimeStr = request.getParameter("dataInizio");
        String EndTimeStr = request.getParameter("dataFine");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dataInizio = null;
        try {
            dataInizio = dateFormat.parse(StartTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dataFine = null;
        try {
            dataFine = dateFormat.parse(EndTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Reservation reservation = new Reservation();

        reservation.setDataInizio(dataInizio);
        reservation.setDataFine(dataFine);

        return reservation;





    }

    private void editReservation(HttpServletRequest request, HttpServletResponse response)  {


        int id = Integer.parseInt(request.getParameter("reservationId"));
        Reservation old = reservationDao.getReservation(id);


        Reservation reservation = readForm(request, response);
        reservation.setId(id);
        reservation.setUser(old.getUser());
        reservation.setVehicle(old.getVehicle());
        //old.getUser().setReservations(reservation);
        //old.getVehicle().setReservations(reservation);
        reservationDao.updateReservation(reservation);




    }

    private void insertReservation(HttpServletRequest request, HttpServletResponse response) {
            User user = (User) request.getSession().getAttribute("user");
            String v = request.getParameter("vehicleId");
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            Vehicle vehicle = vehicleDAO.getVehicle(vehicleId);

            Reservation reservation = readForm(request, response);
            reservation.setVehicle(vehicle);
            reservation.setUser(user);

            //vehicle.setReservations(reservation);
            //user.setReservations(reservation);
            //userDAO.updateUser(user);
            //vehicleDAO.updateVehicle(vehicle);
            reservationDao.saveReservation(reservation);




    }
}
