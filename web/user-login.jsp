<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 13/07/2020
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Login </title>
</head>
<body>
<h1> Registra Utente </h1>
<br/>
<form action="<%=request.getContextPath()%>/LoginControllerServlet" method="post">
    <table cellpadding="3pt">
        <tr>
            <td>UserName :</td>
            <td><input type="text" name="username" size="30" /></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><input type="password" name="password" size="30" /></td>
        </tr>


    </table>
    <p />
    <input type="submit" value="Register" />
</form>





<p><a href="/index.jsp">torna alla home</a></p>


</body>
</html>
