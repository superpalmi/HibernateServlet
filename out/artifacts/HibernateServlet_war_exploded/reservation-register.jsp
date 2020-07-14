<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 13/07/2020
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Prenotazione </title>
</head>
<body>

<h1> Registra Prenotazione</h1>
<br/>
<form action="<%=request.getContextPath()%>/ReservationControllerServlet?action=insert&vehicleId=<%=request.getParameter("vehicleId")%>" method="post">
    <table cellpadding="3pt">
        <tr>
            <td>Data di inizio</td>
            <td><input type="text" name="dataInizio" size="30" /></td>
        </tr>
        <tr>
            <td>Data di fine :</td>
            <td><input type="text" name="dataFine" size="30" /></td>
        </tr>

    </table>
    <p />
    <input type="submit" value="Register" />
</form>
<p><a href="/index.jsp">torna alla home</a></p>


</body>
</html>
