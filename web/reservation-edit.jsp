<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 13/07/2020
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Prenotazione </title>
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
        <h1> Registra Prenotazione</h1>
        <% if (request.getParameter("action").equalsIgnoreCase("edit")){%>
        <form action="<%=request.getContextPath()%>/ReservationControllerServlet?action=edit&reservationId=<%=request.getParameter("reservationId")%>" method="post">
                <%}else if(request.getParameter("action").equalsIgnoreCase("create")){%>
            <form action="<%=request.getContextPath()%>/ReservationControllerServlet?action=insert&vehicleId=<%=request.getParameter("vehicleId")%>" method="post">
                <%}%>
                <div class="table-responsive">
                    <table cellpadding="3pt" class="table">
                        <tr>
                            <td>Data di inizio</td>
                            <td>
                                <input type="date" name="dataInizio" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Data di fine :</td>
                            <td>
                                <input type="date" name="dataFine" size="30" />
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Register" class="btn btn-primary"/>
                </div>
            </form>

    </div>
</div>
<div class="row">
    <div class="col">
        <a href="/index.jsp" class="btn btn-primary">Dashboard</a>
    </div>
</div>
</body>
</html>