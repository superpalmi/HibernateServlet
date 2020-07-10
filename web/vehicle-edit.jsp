<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 09/07/2020
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <title> Edit </title>
</head>
<body>
Modifica Veicolo
<br/>
<form action="<%=request.getContextPath()%>/VehicleControllerServlet?action=edit&vehicleId=<%=request.getParameter("vehicleId")%>" method="get">
    <table cellpadding="3pt">
    <tr>
        <td>Marca :</td>
        <td><input type="text" name="brand" size="30" /></td>
    </tr>
    <tr>
        <td>Modello :</td>
        <td><input type="text" name="model" size="30" /></td>
    </tr>

    <tr>
        <td>Targa : </td>
        <td><input type="text" name="plate" size="30" /></td>
    </tr>
    <tr>
        <td>Data di immatricolazione :</td>
        <td><input type="text" name="registrationDate" size="30" /></td>
    </tr>
    <tr>
        <td>Tipologia :</td>
        <td><input type="text" name="type" size="30" /></td>
    </tr>

    </table>
    <p />
    <input type="submit" value="Vehicle Register" />
</form>








</body>
</html>
