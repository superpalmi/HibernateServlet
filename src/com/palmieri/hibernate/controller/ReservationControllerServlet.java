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
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/ReservationControllerServlet")
public class ReservationControllerServlet extends HttpServlet {

    private static String EDIT_JSP = "/reservation-edit.jsp";
    private static String SHOWALL_JSP = "/reservation-showall.jsp?action=showAll";
    private static String REGISTER_JSP="/reservation-register.jsp";
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //inserisco i valori nel middleware DAO
        String v=request.getParameter("vehicleId");
        //String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null) {
            if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("edit")) {
                String action = request.getParameter("action");
                if(checkDate(request, response)&&checkVehicle(request, response)){
                    editReservation(request, response);
                }else try(PrintWriter out = response.getWriter()){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Veicolo non disponibile nelle date scelte');");
                    out.println("location='reservation-edit.jsp';");
                    out.println("</script>");
                }



            } else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("insert") && request.getParameter("vehicleId") != null ) {
                if(user.getReservations()==null) {

                        String action = request.getParameter("action");
                        insertReservation(request, response);

                }else if(checkDate(request, response)&&checkVehicle(request, response)){
                    String action = request.getParameter("action");
                    insertReservation(request, response);

                }else try(PrintWriter out = response.getWriter()){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Veicolo non disponibile nelle date scelte');");
                    out.println("location='reservation-register.jsp';");
                    out.println("</script>");


                }
            }

            request.setAttribute("reservations", user.getReservations());
            RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);

            view.forward(request, response);
        }else {
            RequestDispatcher view = request.getRequestDispatcher(LOGIN_JSP);
        }


        //inserisco i valori nella richiesta



    }

    private boolean checkDate(HttpServletRequest request, HttpServletResponse response){

        User user = (User) request.getSession().getAttribute("user");
        Reservation res = readForm(request, response);

        Date dataInizio = res.getDataInizio();
        Date dataFine = res.getDataFine();
        List<Reservation> userReservations = user.getReservations();
        boolean result = false;

        for (int i=0; i<userReservations.size(); i++){

            if(((dataInizio.after(userReservations.get(i).getDataInizio()) && dataInizio.before(user.getReservations().get(i).getDataFine())) || (dataFine.after(user.getReservations().get(i).getDataInizio()) && dataFine.before(user.getReservations().get(i).getDataFine())))||((dataInizio.equals(user.getReservations().get(i).getDataInizio()))&&(dataFine.equals(user.getReservations().get(i).getDataFine())))){
                    return result;

            }else return result=true;


        }

        return result = true;




    }

    private boolean checkVehicle(HttpServletRequest request, HttpServletResponse response){
        Reservation res = readForm(request, response);
        int v=Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle=vehicleDAO.getVehicle(v);

        Date dataInizio = res.getDataInizio();
        Date dataFine = res.getDataFine();
        List<Reservation> vehicleReservations = vehicle.getReservations();
        boolean result = false;
        for (int i=0; i<vehicleReservations.size(); i++){

            if(((dataInizio.after(vehicleReservations.get(i).getDataInizio()) && dataInizio.before(vehicle.getReservations().get(i).getDataFine())) || (dataFine.after(vehicle.getReservations().get(i).getDataInizio()) && dataFine.before(vehicle.getReservations().get(i).getDataFine())))||((dataInizio.equals(vehicle.getReservations().get(i).getDataInizio()))&&(dataFine.equals(vehicle.getReservations().get(i).getDataFine())))){
                return result;

            }else return result=true;


        }

        return result = true;

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        if(user!=null) {
            if (action.equalsIgnoreCase("create")) {
                forward = REGISTER_JSP;
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                request.setAttribute("vehicleId", vehicleId);

            } else if (action.equalsIgnoreCase("delete")) {
                forward = SHOWALL_JSP;
                int reservationId = Integer.parseInt(request.getParameter("reservationId"));
                reservationDao.deleteReservation(reservationId);
                request.setAttribute("reservations", reservationDao.getAllReservations());

            } else if (action.equalsIgnoreCase("edit")) {
                forward = EDIT_JSP;
                int i = Integer.parseInt(request.getParameter("reservationId"));
                //editUser(request, response);
                request.setAttribute("reservationId", reservationDao.getReservation(i));
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
            } else if (action.equalsIgnoreCase("getuser")) {
                forward = SHOWALL_JSP;

                int i = Integer.parseInt(request.getParameter("reservationId"));
                User u = reservationDao.getUser(i);
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

            } else if (action.equalsIgnoreCase("getvehicle")) {

                int i = Integer.parseInt(request.getParameter("reservationId"));
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
            vehicle.setReservations(reservation);
            user.setReservations(reservation);
            userDAO.updateUser(user);
            vehicleDAO.updateVehicle(vehicle);
            reservationDao.saveReservation(reservation);




    }
}
