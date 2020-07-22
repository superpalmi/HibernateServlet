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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/js/jquery.dataTables.min.js"></script>
</head>
<body>

<script type="text/javascript">
    $(document).ready(function () {
        $('#dtBasicExample').DataTable();
    });
</script>
<div class="row justify-content-center">
    <div class="col-auto">
        <h1> Veicoli disponibili</h1>
        <div class="table-responsive" style="width: 100%">
            <table  id="dtBasicExample" border=1 class="table table-striped table-bordered table-sm" >
                <thead>
                <tr>
                    <th class="th-sm">Vehicle Id</th>
                    <th class="th-sm">Brand</th>
                    <th class="th-sm">Model</th>
                    <th class="th-sm">Plate</th>
                    <th class="th-sm">Immatricolation Date</th>
                    <th class="th-sm">Action</th>
                    <th class="th-sm">Action</th>
                    <th class="th-sm">Action</th>
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
                            <fmt:formatDate value="${vehicle.immdate}" />
                        </td>
                        <td>
                            <a href="ReservationControllerServlet?action=create&vehicleId=
														<c:out value="${vehicle.id}"/>">Prenota
                            </a>
                        </td>

                        <c:if test="${user.role=='superuser'}" >
                            <td>
                                <a href="VehicleControllerServlet?action=delete&vehicleId=
															<c:out value="${vehicle.id}"/>">Delete
                                </a>
                            </td>
                            <td>
                                <a href="VehicleControllerServlet?action=edit&vehicleId=
														<c:out value="${vehicle.id}"/>">Update
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${user.role=='superuser'}" >
            <p>
                <a href="/VehicleControllerServlet?action=create" class="btn btn-primary">Add vehicle</a>
            </p>
        </c:if>
        <p>
            <a href="/index.jsp" class="btn btn-dark">Dashboard</a>
        </p>
    </div>
</div>
</body>
</html>