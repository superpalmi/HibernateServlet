<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 15/07/2020
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Il mio Utente </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-auto">
        <div class="table-responsive">
            <table border=1 class="table">
                <thead>
                <tr>
                    <th>User Id</th>
                    <th>UserName</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>City</th>
                    <th>Ruolo</th>
                    <th colspan=2>Action</th>
                </tr>
                </thead>
                <tbody>
                <div class="table-responsive">
                    <tr>
                        <td>
                            <c:out value="${user.id}" />
                        </td>
                        <td>
                            <c:out value="${user.userName}" />
                        </td>
                        <td>
                            <c:out value="${user.email}" />
                        </td>
                        <td>
                            <c:out value="${user.phone}" />
                        </td>
                        <td>
                            <c:out value="${user.city}" />
                        </td>
                        <td>
                            <c:out value="${user.role}" />
                        </td>

                        <td>
                            <a href="UserControllerServlet?action=edit&userId=
														<c:out value="${user.id}"/>">Aggiorna dati Utente
                            </a>
                        </td>

                        <c:if test="${user.role=='superuser'}">
                            <td>
                                <a href="UserControllerServlet?action=delete&userId=
															<c:out value="${user.id}"/>">Cancella Utente
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </div>
                </tbody>
            </table>
        </div>
        <div class="table-responsive">
            <table border=1 class="table">
                <thead>
                <tr>
                    <th>Prenotazione</th>


                </tr>
                </thead>

                <tbody>
                <c:forEach items="${user.reservations}" var="reservation">

            <tr>
                <td>
                    <c:out value="Id: ${reservation.id} Targa: ${reservation.vehicle.plate}" />
                </td>
                <td>
                    <fmt:formatDate value="${reservation.dataInizio}" pattern="YYYY/MM/DD" />
                </td>
                <td>
                    <fmt:formatDate value="${reservation.dataFine}" pattern="YYYY/MM/DD" />
                </td>
                <td>
                    <a href="ReservationControllerServlet?action=edit&reservationId=
														<c:out value="${reservation.id}"/>">Modifica Prenotazione
                    </a>
                </td>
            </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>

        <p>
            <a href="/index.jsp" class="btn btn-primary">Dashboard</a>
        </p>
    </div>
</div>

</body>
</html>
