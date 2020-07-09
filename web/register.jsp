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
    <title> Register </title>
</head>
<body>
Registra utente
<br/>
<form action="<%=request.getContextPath()%>/registerUser" method="post">
    <table cellpadding="3pt">
        <tr>
            <td>User Name :</td>
            <td><input type="text" name="userName" size="30" /></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><input type="password" name="password1" size="30" /></td>
        </tr>

        <tr>
            <td>Confirm Password :</td>
            <td><input type="password" name="password2" size="30" /></td>
        </tr>
        <tr>
            <td>email :</td>
            <td><input type="text" name="email" size="30" /></td>
        </tr>
        <tr>
            <td>Phone :</td>
            <td><input type="text" name="phone" size="30" /></td>
        </tr>
        <tr>
            <td>City :</td>
            <td><input type="text" name="city" size="30" /></td>
        </tr>
    </table>
    <p />
    <input type="submit" value="Register" />
</form>

<jsp:include page="success.jsp" />

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

<jsp:include page="success.jsp" />



</body>
</html>
