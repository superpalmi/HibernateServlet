<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Show All Reservations</title>
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
        <h1> Prenotazioni</h1>
        <div class="table-responsive"  style="width: 100%">
            <table id="dtBasicExample" border=1 class="table table-striped table-bordered table-sm" >
                <thead>
                <tr>
                    <th class="th-sm">Reservation Id</th>
                    <th class="th-sm">User Id</th>
                    <th class="th-sm">Vehicle Id</th>
                    <th class="th-sm">Data di Inizio</th>
                    <th class="th-sm">Data di Fine</th>
                    <th class="th-sm">Username</th>
                    <th class="th-sm">Targa Veicolo</th>
                    <th class="th-sm">Action</th>
                    <th class="th-sm">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${reservations}" var="reservation">
                    <tr>
                        <td>
                            <c:out value="${reservation.id}" />
                        </td>
                        <td>
                            <c:out value="${reservation.user.id}" />
                        </td>
                        <td>
                            <c:out value="${reservation.vehicle.id}" />
                        </td>
                        <td>
                            <fmt:formatDate value="${reservation.dataInizio}" />
                        </td>
                        <td>
                            <fmt:formatDate value="${reservation.dataFine}"  />
                        </td>
                        <td>
                            <c:out value="${reservation.user.userName}" />
                        </td>
                        <td>
                            <c:out value="${reservation.vehicle.plate}" />
                        </td>
                        <td>
                            <a href="ReservationControllerServlet?action=edit&reservationId=
														<c:out value="${reservation.id}"/>">Modifica Prenotazione

                            </a>
                        </td>
                        <c:if test="${user.role=='superuser'}">
                            <td>
                                <a href="ReservationControllerServlet?action=delete&reservationId=
															<c:out value="${reservation.id}"/>">Delete
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<p>
    <a href="/index.jsp" class="btn btn-primary">Dashboard</a>
</p>
</body>
</html>