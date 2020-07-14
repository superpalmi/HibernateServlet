<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Users</title>
</head>
<body>
<table border=1 >
    <thead>
    <tr>
        <th>Reservation Id</th>
        <th>User Id</th>
        <th>Vehicle Id</th>
        <th>Data di Inizio</th>
        <th>Data di Fine</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${reservations}" var="reservation">
        <tr>
            <td><c:out value="${reservation.id}" /></td>
            <td><c:out value="${reservation.userId}" /></td>
            <td><c:out value="${reservation.vehicleId}" /></td>
            <td><c:out value="${reservation.dataInizio}" /></td>
            <td><c:out value="${reservation.dataFine}" /></td>
            <td><c:out value="${reservation.user.username}" /></td>
            <td><c:out value="${reservation.vehicle.brand}" /></td>


            <td><a href="ReservationControllerServlet?action=edit&reservationId=<c:out value="${reservation.id}"/>">Update</a></td>
            <td><a href="ReservationControllerServlet?action=delete&reservationId=<c:out value="${reservation.id}"/>">Delete</a></td>
            <td><a href="ReservationControllerServlet?action=getuser&reservationId=<c:out value="${reservation.id}"/>">Visualizza Utente</a></td>


            <td><a href="ReservationControllerServlet?action=getvehicle&reservationId=<c:out value="${reservation.id}"/>">Visualizza Veicolo</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
<p><a href="/user-register.jsp">Add Reservation</a></p>
<p><a href="/index.jsp">torna alla home</a></p>


</body>
</html>