<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.aurionpro.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"EMPLOYEE".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #fdffeb; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .navbar { background: linear-gradient(90deg,#d7248a,#0a3384); }
        .navbar-brand, .nav-link, .navbar-text { color: #fff !important; font-weight: bold; }
        .card { border-radius: 15px; box-shadow: 0px 6px 12px rgba(0,0,0,0.15); }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Employee Dashboard</a>
        <div class="d-flex">
            <span class="navbar-text me-3">👤 <%= loggedUser.getUsername() %></span>
             <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="row g-4">

        <!-- Apply Leave -->
        <div class="col-md-6">
            <div class="card p-4">
                <h5 class="mb-3">📌 Apply Leave</h5>
                <form action="applyLeave" method="post">
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
                        <input type="date" name="startDate" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">End Date</label>
                        <input type="date" name="endDate" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Apply</button>
                </form>
            </div>
        </div>

        <!-- View Leaves -->
        <div class="col-md-6">
            <div class="card p-4">
                <h5 class="mb-3">📖 My Leaves</h5>
                <a href="viewLeaves" class="btn btn-outline-primary w-100">View All Leaves</a>
            </div>

            <div class="card p-4 mt-3">
                <h5 class="mb-3">📊 Remaining Leaves</h5>
                <a href="remainingLeaves" class="btn btn-outline-success w-100">View Remaining Leaves</a>
            </div>
        </div>

    </div>
</div>

</body>
</html>
