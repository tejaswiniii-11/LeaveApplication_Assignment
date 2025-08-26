<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Apply Leave</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5 col-md-6">
    <h3 class="text-center mb-4">Apply Leave</h3>

    <form action="applyLeave" method="post" class="card p-4 shadow-lg">
        <div class="mb-3">
            <label class="form-label">Leave Type</label>
            <select name="leaveTypeId" class="form-select" required>
                <option value="1">Casual</option>
                <option value="2">Privileged</option>
                <option value="3">Occasional</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Start Date</label>
            <input type="date" name="startDate" class="form-control" 
                   placeholder="yyyy-mm-dd" pattern="\d{4}-\d{2}-\d{2}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">End Date</label>
            <input type="date" name="endDate" class="form-control" 
                   placeholder="yyyy-mm-dd" pattern="\d{4}-\d{2}-\d{2}" required>
        </div>

        <button type="submit" class="btn btn-success w-100">Apply</button>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success mt-3">${message}</div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger mt-3">${errorMessage}</div>
    </c:if>
</div>

</body>
</html>
