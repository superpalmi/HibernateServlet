<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Show All Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
    <div class="col">
<div class="table-responsive">
<table border=1 class="table">
    <thead>
    <tr>
        <th>User Id</th>
        <th>UserName</th>
        <th>Email</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.userName}" /></td>
            <td><c:out value="${user.email}" /></td>

            <td><a href="UserControllerServlet?action=edit&userId=<c:out value="${user.id}"/>">Update</a></td>
            <td><a href="UserControllerServlet?action=delete&userId=<c:out value="${user.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

<p><a href="/index.jsp" class="btn btn-primary">Dashboard</a></p>
    </div>
</div>
</body>
</html>