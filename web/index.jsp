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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <title>Benvenuto</title>
  </head>
  <body>
  <h1> Benvenuto </h1>
  <br/>
  <a href="user-register.jsp"> Registra un nuovo Utente</a>
  <br/>
  <a href="UserControllerServlet?action=showAll"> Guarda la lista degli Utenti </a>
  <br/>
  <a href="vehicle-register.jsp"> Registra un nuovo Veicolo</a>
  <br/>
  <a href="VehicleControllerServlet?action=showAll"> Guarda la lista dei Veicoli </a>

  </body>
</html>
