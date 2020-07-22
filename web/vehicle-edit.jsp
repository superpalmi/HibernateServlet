<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 09/07/2020
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Edit </title>
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
 <h1> Inserimento dati Veicolo</h1>

<br/>

<%if(request.getParameter("action").equalsIgnoreCase("edit")){ %>
<form action="<%=request.getContextPath()%>/VehicleControllerServlet?action=edit&vehicleId=<%=request.getParameter("vehicleId")%>" method="post">
    <%}else if(request.getParameter("action").equalsIgnoreCase("create")) {%>
    <form action="<%=request.getContextPath()%>/VehicleControllerServlet?action=insert" method="post"> <%}%>
    <div class="table-responsive">
        <table cellpadding="3pt" class="table">
            <tr>
                <td>Marca :</td>
                <td>
                    <input type="text" name="brand" size="30" />
                </td>
            </tr>
            <tr>
                <td>Modello :</td>
                <td>
                    <input type="text" name="model" size="30" />
                </td>
            </tr>
            <tr>
                <td>Targa : </td>
                <td>
                    <input type="text" name="plate" size="30" />
                </td>
            </tr>
            <tr>
                <td>Data di immatricolazione :</td>
                <td>
                    <input type="date" name="registrationDate" size="30" />
                </td>
            </tr>
            <tr>
                <td>Tipologia :</td>
                <td>
                    <input type="text" name="type" size="30" />
                </td>
            </tr>
        </table>
    </div>
    <p />
    <input type="submit" value="Vehicle Register" class="btn btn-primary"/>
        <a href="/index.jsp" class="btn btn-dark">Dashboard</a>
</form>
    </div>
</div>
</body>
</html>
