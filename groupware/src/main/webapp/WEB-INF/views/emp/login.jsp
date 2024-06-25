<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .login-container {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 100px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>사원 로그인</h2>
        <form action="${pageContext.request.contextPath}/emp/login" method="post">
            <div class="form-group">
                <label for="adminId">Employee ID</label>
                <input type="text" id="adminId" name="id" placeholder="Enter Admin ID" required>
            </div>
            <div class="form-group">
                <label for="adminPwd">Password</label>
                <input type="password" id="adminPwd" name="password" placeholder="Enter Password" required>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
            <c:if test="${not empty errMsg}">
                <div class="error-message">${errMsg}</div>
            </c:if>
        </form>
    </div>
</body>
</html>
