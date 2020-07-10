<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Users</title>
</head>
<body>
<table border=1 >
    <thead>
    <tr>
        <th>Vehicle Id</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Plate</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td><c:out value="${vehicle.id}" /></td>
            <td><c:out value="${vehicle.brand}" /></td>
            <td><c:out value="${vehicle.model}" /></td>
            <td><c:out value="${vehicle.plate}" /></td>

            <td><a href="VehicleControllerServlet?action=edit&vehicleId=<c:out value="${vehicle.id}"/>">Update</a></td>
            <td><a href="UserControllerServlet?action=delete&vehicleId=<c:out value="${vehicle.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="/vehicle-register.jsp">Add vehicle</a></p>
</body>
</html>