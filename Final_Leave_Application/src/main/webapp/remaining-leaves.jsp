<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Remaining Leaves</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5 col-md-6">
    <h3 class="text-center mb-4">Remaining Leaves</h3>

    <table class="table table-bordered table-hover shadow-lg">
        <thead class="table-dark">
            <tr>
                <th>Leave Type</th>
                <th>Remaining</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over map entrySet -->
            <c:forEach var="entry" items="${remainingLeaves}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
