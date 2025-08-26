<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Leaves</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h3 class="text-center mb-4">My Leaves</h3>

    <table class="table table-bordered table-hover shadow-lg">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Start</th>
                <th>End</th>
                <th>Status</th>
                <th>Applied On</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="leave" items="${leaves}">
                <tr>
                    <td>${leave.leaveId}</td>
                    <td>${leave.leaveTypeName}</td>
                    <td>${leave.startDate}</td>
                    <td>${leave.endDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${leave.status == 'APPROVED'}">
                                <span class="badge bg-success">${leave.status}</span>
                            </c:when>
                            <c:when test="${leave.status == 'REJECTED'}">
                                <span class="badge bg-danger">${leave.status}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-warning text-dark">${leave.status}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${leave.appliedOn}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
