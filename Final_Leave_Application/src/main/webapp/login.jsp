<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #d4145a,#fbb03b );
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .login-card {
            background: #ffffff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0px 8px 20px rgba(0,0,0,0.2);
            width: 100%;
            max-width: 400px;
        }
        .login-card h3 {
            font-weight: bold;
            color: #7326df;
        }
        .form-control, .form-select {
            border-radius: 10px;
        }
        .btn-custom {
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            border: none;
            border-radius: 10px;
            padding: 10px;
            font-weight: bold;
            transition: all 0.3s ease-in-out;
        }
        .btn-custom:hover {
            transform: scale(1.05);
            background: linear-gradient(135deg, #ffdb00, #0e197d);
        }
        .alert {
            border-radius: 10px;
        }
    </style>
</head>
<body>

<div class="login-card">
    <h3 class="text-center mb-4">Leave App Login</h3>
    
    <form action="login" method="post">
        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" required>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" required>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Role</label>
            <select name="role" class="form-select" required>
                <option value="EMPLOYEE">Employee</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        
        <button type="submit" class="btn btn-custom w-100">Login</button>
    </form>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger mt-3 text-center">
            ${errorMessage}
        </div>
    </c:if>
</div>

</body>
</html>
