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
        <h1> Inserimento dati Utente</h1>
        <br/><% if (request.getParameter("action").equalsIgnoreCase("edit")){%>
        <form action="<%=request.getContextPath()%>/UserControllerServlet?action=edit&userId=<%=request.getParameter("userId")%>" method="post"><%}else if(request.getParameter("action").equalsIgnoreCase("create")){%>
            <form action="<%=request.getContextPath()%>/UserControllerServlet?action=create" method="post"><%}%>
                <div class="table-responsive">
                    <table cellpadding="3pt" class="table">
                        <tr>
                            <td>User Name :</td>
                            <td>
                                <input type="text" name="userName" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Password :</td>
                            <td>
                                <input type="password" name="password1" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Confirm Password :</td>
                            <td>
                                <input type="password" name="password2" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>email :</td>
                            <td>
                                <input type="text" name="email" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Phone :</td>
                            <td>
                                <input type="text" name="phone" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>City :</td>
                            <td>
                                <input type="text" name="city" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Role :</td>
                            <td>
                                <input type="text" name="role" size="30" />
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Register" class="btn btn-primary" />
                </div>
            </form>

                <%
                            String login_msg=(String)request.getAttribute("message");
                            if(login_msg!=null)
                            out.println("<font color=red size=4px>"+login_msg+"</font>");
                            %>

    </div>
</div>
<p>
    <a href="/home.jsp">Torna alla Home</a>
</p>
</body>
</html>
