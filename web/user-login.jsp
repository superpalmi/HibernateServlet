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
<div class="container">

    <h1 class="display-1"> Login Utente </h1>
    <div class="row">

        <form action="<%=request.getContextPath()%>/LoginControllerServlet" method="post" class="form-group">
                <div class="table-responsive">
                 <table class="table" cellpadding="3pt">
                     <tr>
                         <td>UserName :</td>
                         <td>
                             <div class="form-group"> <input type="text" name="username" size="30" />  </div>

                         </td>
                     </tr>
                        <tr>
                         <td>Password :</td>

                        <td>
                            <div class="form-group">
                                <input type="password" name="password" size="30" />
                            </div>

                        </td>
                     </tr>


                </table>
                    <div class="form-group">
                        <input type="submit" value="Login"  class="btn btn-primary"/>
                        <a href="/home.jsp" class="btn btn-secondary" role="button">Home</a>
                    </div>

                </div>


        </form>

    </div>


</div>
    </div>
</div>





</body>
</html>
