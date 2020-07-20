<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Show All Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/css/dataTables.bootstrap4.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.1/umd/popper.min.js"></script>
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
        <h1>Utenti Registrati</h1>
        <div class="table-responsive" style="width: 100%">
            <table id="dtBasicExample" border=1 class="table table-striped table-bordered table-sm" >
                <thead>
                <tr>
                    <th class="th-sm">User Id</th>
                    <th class="th-sm">UserName</th>
                    <th class="th-sm">Email</th>
                    <th class="th-sm">Citt?</th>
                    <th class="th-sm">Phone</th>
                    <th class="th-sm">Ruolo</th>
                    <th class="th-sm">Action</th>
                    <th class="th-sm">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <c:out value="${user.id}" />
                        </td>
                        <td>
                            <c:out value="${user.userName}" />
                        </td>
                        <td>
                            <c:out value="${user.email}" />
                        </td>
                        <td>
                            <c:out value="${user.city}" />
                        </td>
                        <td>
                            <c:out value="${user.phone}" />
                        </td>
                        <td>
                            <c:out value="${user.role}" />
                        </td>
                        <td>
                            <a href="UserControllerServlet?action=edit&userId=
														<c:out value="${user.id}"/>">Update
                            </a>
                        </td>
                        <td>
                            <a href="UserControllerServlet?action=delete&userId=
														<c:out value="${user.id}"/>">Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <p>
            <a href="/index.jsp" class="btn btn-primary">Dashboard</a>
        </p>
    </div>
</div>





</body>
</html>