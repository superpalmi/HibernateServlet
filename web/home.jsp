<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 14/07/2020
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Gestione Prenotazioni</title>
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
        <h1> Gestione Prenotazioni</h1>
        <div class="container pt-3">

            <div class="row justify-content-center">
                <div class="col-lg-4 text-center">
                    <a href="user-login.jsp" class="btn btn-primary" > Login </a>
                </div>

            </div>
            <div class="row justify-content-center">
                <div class="col-lg-4 text-center">
                    <br/>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-lg-4 text-center">
                    <a href="UserControllerServlet?action=create"  class="btn btn-primary" >Registrati</a>
                </div>
            </div>

            <%
                String login_msg=(String)request.getAttribute("message");
                if(login_msg!=null)
                    out.println("<font color=green size=4px>"+login_msg+"</font>");
            %>



        </div>
    </div>
</div>

</body>
</html>
