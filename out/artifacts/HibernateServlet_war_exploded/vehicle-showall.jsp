<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                        <td>
                            <c:out value="${vehicle.id}" />
                        </td>
                        <td>
                            <c:out value="${vehicle.brand}" />
                        </td>
                        <td>
                            <c:out value="${vehicle.model}" />
                        </td>
                        <td>
                            <c:out value="${vehicle.plate}" />
                        </td>
                        <td>
                            <a href="ReservationControllerServlet?action=create&vehicleId=

														<c:out value="${vehicle.id}"/>">Prenota

                            </a>
                        </td>
                        <td>
                            <a href="VehicleControllerServlet?action=edit&vehicleId=

														<c:out value="${vehicle.id}"/>">Update

                            </a>
                        </td>
                        <td>
                            <a href="VehicleControllerServlet?action=delete&vehicleId=

														<c:out value="${vehicle.id}"/>">Delete

                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <p>
            <a href="/vehicle-register.jsp" class="btn btn-primary">Add vehicle</a>
        </p>
        <p>
            <a href="/index.jsp" class="btn btn-primary">Dashboard</a>
        </p>
    </div>
</div>
</body>
</html>