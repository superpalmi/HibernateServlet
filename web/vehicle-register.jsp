<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 10/07/2020
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Registra Veicolo
<br/>

<form action="<%=request.getContextPath()%>/registerVehicle" method="post">
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
