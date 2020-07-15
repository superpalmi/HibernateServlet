<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 09/07/2020
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Dashboard</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  </head>
  <body>
  <div class="container pt-3">
    <h1> Benvenuto </h1>
    <br/>
    <div class="btn-group">
      <a href="UserControllerServlet?action=showAll" class="btn btn-primary" > Lista Utenti </a>

      <a href="vehicle-register.jsp" class="btn btn-primary" > Registra Veicolo</a>

      <a href="VehicleControllerServlet?action=showAll" class="btn btn-primary" > Lista Veicoli</a>

      <a href="ReservationControllerServlet?action=showAll" class="btn btn-primary" >Prenotazioni</a>

    </div>
    <a href="LogoutControllerServlet" class="btn btn-secondary"  > Log Out</a>
  </div>

  </body>
</html>
