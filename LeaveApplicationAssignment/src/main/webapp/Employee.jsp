<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.model.EmployeeLeave"%>

<%
    // ✅ Session validation
    if (session == null || session.getAttribute("employee") == null) {
        response.sendRedirect("UserLogin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Leave Dashboard</title>
<!-- Bootstrap 5 CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.dashboard-container {
	display: flex;
	gap: 40px;
	align-items: flex-start;
}

.form-section, .table-section {
	width: 40%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h2>Employee Leave Dashboard</h2>
			<!-- ✅ Logout button -->
			<form action="LogoutUser" method="get" style="margin-bottom: 1rem;">
				<button type="submit" class="btn btn-danger">Logout</button>
			</form>
		</div>

		<!-- Display success/error messages -->
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<div class="dashboard-container">
			<!-- Leave Apply Form -->
			<div class="form-section">
				<div class="card shadow-sm">
					<div class="card-body">
						<h4 class="mb-3">Apply for Leave</h4>
						<form action="EmployeeLeaveServlet" method="post"
							id="applyLeaveForm">
							<div class="mb-3">
								<label for="empId" class="form-label">Employee ID</label>
								<input type="text" id="empId" name="empId" class="form-control" required />
							</div>
							<div class="mb-3">
								<label for="empName" class="form-label">Employee Name</label>
								<input type="text" id="empName" name="empName" class="form-control" required />
							</div>
							<div class="mb-3">
								<label for="leaveFrom" class="form-label">Leave From</label>
								<input type="date" id="leaveFrom" name="leaveFrom" class="form-control" required />
							</div>
							<div class="mb-3">
								<label for="tillLeave" class="form-label">Till Leave</label>
								<input type="date" id="tillLeave" name="tillLeave" class="form-control" required />
							</div>
							<div class="mb-3">
								<label for="totalLeaves" class="form-label">Total Leaves</label>
								<input type="number" id="totalLeaves" name="totalLeaves" class="form-control" min="1" required />
							</div>
							<div class="mb-3">
								<label for="reason" class="form-label">Reason for Leave</label>
								<textarea id="reason" name="reason" class="form-control" rows="3" required></textarea>
							</div>
							<button type="submit" class="btn btn-success w-100 mb-2">Apply Leave</button>
						</form>
					</div>
				</div>
			</div>

			<!-- Leave Summary Table -->
			<div class="table-section card">
				<h4 class="text-primary mb-4">View Leave Summary</h4>
				<form action="EmployeeLeaveServlet" method="get" class="mb-4">
					<div class="mb-3">
						<label for="filterEmpId" class="form-label">Enter Employee ID to view summary:</label>
						<input type="text" class="form-control mb-3" name="filterEmpId" id="filterEmpId" required />
					</div>
					<button type="submit" class="btn btn-primary w-100" name="action" value="viewSummary">
						View Leave Summary
					</button>
				</form>

				<c:if test="${showSummary == true}">
					<div class="card shadow-sm">
						<div class="card-body">
							<h4 class="mb-3">Leave Summary</h4>
							<div class="table-responsive">
								<table class="table table-bordered table-hover align-middle">
									<thead class="table-light">
										<tr>
											<th>Employee ID</th>
											<th>Employee Name</th>
											<th>Total Leaves</th>
											<th>Leave From</th>
											<th>Till Leave</th>
											<th>Reason</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<%
											List<EmployeeLeave> leaveList = (List<EmployeeLeave>) request.getAttribute("leaveList");
											if (leaveList != null && !leaveList.isEmpty()) {
												for (EmployeeLeave l : leaveList) {
										%>
										<tr>
											<td><%= l.getEmpId() %></td>
											<td><%= l.getEmpName() %></td>
											<td><%= l.getTotalLeaves() %></td>
											<td><%= l.getLeaveFrom() %></td>
											<td><%= l.getTillLeave() %></td>
											<td><%= l.getReason() %></td>
											<td><%= l.getStatus() %></td>
										</tr>
										<%
												}
											} else {
										%>
										<tr>
											<td colspan="7" class="text-center">No leave records found.</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS Bundle -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
