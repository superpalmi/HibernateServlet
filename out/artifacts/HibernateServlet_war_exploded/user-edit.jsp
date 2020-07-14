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
Modifica Utente
<br/>
<form action="<%=request.getContextPath()%>/UserControllerServlet?action=edit&userId=<%=request.getParameter("userId")%>" method="post">
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




<p><a href="/index.jsp">torna alla home</a></p>



</body>
</html>
