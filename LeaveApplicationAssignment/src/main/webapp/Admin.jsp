<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Leave Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.dashboard-container {
	margin-top: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<h2 class="mb-4 text-danger">Admin Leave Management Dashboard</h2>

		<!-- Optional info/success/error messages -->
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<div class="dashboard-container">
			<!-- Button to refresh summary -->
			<form action="AdminLeaveServlet" method="get" class="mb-3">
				 <button type="submit" class="btn btn-primary">Employee Leave Action</button>


				<!-- Leave Summary Table -->
				<div class="card shadow-sm">
					<div class="card-body">
						<h4 class="mb-3">Leaves Applied By The Employees</h4>
						<div class="table-responsive">
							<table class="table table-bordered table-hover align-middle">
								<thead class="table-secondary">
									<tr>
										<th>Employee ID</th>
										<th>Employee Name</th>
										<th>Total Leaves</th>
										<th>Leave From</th>
										<th>Till Leave</th>
										<th>Reason</th>
										<th>Status</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${not empty leaveList}">
											<c:forEach var="leave" items="${leaveList}">
												<tr>
													<td>${leave.empId}</td>
													<td>${leave.empName}</td>
													<td>${leave.totalLeaves}</td>
													<td>${leave.leaveFrom}</td>
													<td>${leave.tillLeave}</td>
													<td>${leave.reason}</td>
													<td><c:choose>
															<c:when test="${leave.status == 'Approved'}">
																<span class="badge bg-success">${leave.status}</span>
															</c:when>
															<c:when test="${leave.status == 'Rejected'}">
																<span class="badge bg-danger">${leave.status}</span>
															</c:when>
															<c:otherwise>
																<span class="badge bg-warning text-dark">${leave.status}</span>
															</c:otherwise>
														</c:choose></td>
													<td><c:choose>
															<c:when test="${leave.status == 'Pending'}">
																<form action="AdminLeaveServlet" method="post" style="display: inline;">
																	<input type="hidden" name="empId"
																		value="${leave.empId}" /> <input type="hidden"
																		name="action" value="approve" />
																	<button type="submit" class="btn btn-success btn-sm">Approve</button>
																</form>
																<form action="AdminLeaveServlet" method="post" style="display: inline;">
																	<input type="hidden" name="empId"
																		value="${leave.empId}" /> <input type="hidden"
																		name="action" value="reject" />
																	<button type="submit" class="btn btn-danger btn-sm">Reject</button>
																</form>
															</c:when>
															<c:otherwise>
																<span class="text-muted">N/A</span>
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="8" class="text-center">No leave records
													found.</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</form>
		</div>
		
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
