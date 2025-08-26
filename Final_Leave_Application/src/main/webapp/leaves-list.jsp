<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<div class="container mt-3">
    <div class="card shadow-lg p-3 mb-5 bg-body rounded">
        <h4 class="text-center text-primary mb-4">Leaves List</h4>
        <div class="table-responsive">
            <table class="table table-hover table-bordered align-middle">
                <thead class="table-dark text-center">
                    <tr>
                        <th>Leave ID</th>
                        <th>Employee</th>
                        <th>Leave Type</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Applied On</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="leave" items="${leaves}">
                        <tr class="text-center">
                            <td>${leave.leaveId}</td>
                            <td>${leave.username}</td>
                            <td>
                                <span class="badge bg-info text-dark">${leave.leaveTypeName}</span>
                            </td>
                            <td>${leave.startDate}</td>
                            <td>${leave.endDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${leave.status eq 'PENDING'}">
                                        <span class="badge bg-warning text-dark">${leave.status}</span>
                                    </c:when>
                                    <c:when test="${leave.status eq 'APPROVED'}">
                                        <span class="badge bg-success">${leave.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">${leave.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${leave.appliedOn}</td>
                            <td>
                                <c:if test="${leave.status eq 'PENDING'}">
                                    <a href="adminLeave?action=approve&leaveId=${leave.leaveId}" 
                                       class="btn btn-success btn-sm action-btn mb-1">
                                        <i class="bi bi-check-circle"></i> Approve
                                    </a>
                                    <a href="adminLeave?action=reject&leaveId=${leave.leaveId}" 
                                       class="btn btn-danger btn-sm action-btn mb-1">
                                        <i class="bi bi-x-circle"></i> Reject
                                    </a>
                                </c:if>
                                <c:if test="${leave.status ne 'PENDING'}">
                                    <span class="text-muted">No actions</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<style>
    body {
        background: linear-gradient(135deg, #f8f9fa, #e9ecef);
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .card {
        border-radius: 1rem;
    }

    .table-hover tbody tr:hover {
        background-color: #d1ecf1 !important;
    }

    .badge {
        font-weight: 600;
        font-size: 0.95rem;
        padding: 0.4em 0.6em;
    }

    .action-btn {
        transition: transform 0.2s, box-shadow 0.3s;
    }

    .action-btn:hover {
        transform: translateY(-3px);
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.25);
    }

    .table th, .table td {
        vertical-align: middle !important;
    }

    h4 {
        font-weight: 700;
        letter-spacing: 0.5px;
    }
</style>

<!-- Bootstrap JS & Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
