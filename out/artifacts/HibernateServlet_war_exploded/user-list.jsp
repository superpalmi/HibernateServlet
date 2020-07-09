<%@ page import="com.palmieri.hibernate.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 09/07/2020
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>Title</title>
</head>
<body>
<table class="table" id="utenti">
    <%
        List <User> listaUtenti = (List<User>) request.getAttribute("USER_LIST"); %>
    <%
        if(listaUtenti!=null){

        for (User u : listaUtenti) {
        int i=0;
    %>
    <tr id="u" value="<%= i %>">
        <td id="nome" class="text-center"><%=u.getUserName()%></td>
        <td id="cognome" class="text-center"><%=u.getCity()%></td>
        <td id="email" class="text-center"><%=u.getEmail()%></td>

        <td>
            <form action="creaModificaUtente" method="post">
                <button type="submit" name="cancella"  class="btn btn-danger" value="<%= u.getId() %>" >Cancella</button>

            </form>

            <form action="creaModificaUtente" method="get">
                <button type="submit" name="update" class="btn btn-success" value="<%= u.getId() %>" >Modifica</button>
            </form>


        </td>
    </tr>
    <%
        }
            }
    %>
</table>




</body>
</html>
