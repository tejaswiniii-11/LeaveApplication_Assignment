<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f6fa;
        }
        .status-card {
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .status-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
        }
        .table th {
            background-color: #343a40;
            color: #fff;
        }
        .table td, .table th {
            vertical-align: middle;
        }
        .welcome-text {
            font-weight: bold;
            color: #fff;
        }
    </style>
</head>
<body>


<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Leave Management - Admin</a>
        <div class="d-flex align-items-center">
            <span class="navbar-text me-3 welcome-text">
                Welcome, ${sessionScope.username}
            </span>
           <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">Logout</a>


        </div>
    </div>
</nav>

<div class="container mt-4">

    <h2 class="mb-4 text-center text-primary">Admin Dashboard</h2>

    <!-- Leave Status Cards -->
    <div class="row g-3 mb-4">
        <div class="col-md-3">
            <a href="adminLeave?action=all" class="text-decoration-none">
                <div class="card status-card text-center text-white bg-primary p-3">
                    <h5>All Leaves</h5>
                    <p><i class="bi bi-journal-text" style="font-size: 24px;"></i></p>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="adminLeave?action=pending" class="text-decoration-none">
                <div class="card status-card text-center text-dark bg-warning p-3">
                    <h5>Pending Leaves</h5>
                    <p><i class="bi bi-hourglass-split" style="font-size: 24px;"></i></p>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="adminLeave?action=approved" class="text-decoration-none">
                <div class="card status-card text-center text-white bg-success p-3">
                    <h5>Approved Leaves</h5>
                    <p><i class="bi bi-check2-circle" style="font-size: 24px;"></i></p>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="adminLeave?action=rejected" class="text-decoration-none">
                <div class="card status-card text-center text-white bg-danger p-3">
                    <h5>Rejected Leaves</h5>
                    <p><i class="bi bi-x-circle" style="font-size: 24px;"></i></p>
                </div>
            </a>
        </div>
    </div>

</div>

<!-- Bootstrap JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap Icons CDN -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

</body>
</html>
