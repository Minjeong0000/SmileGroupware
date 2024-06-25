<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AdminLogin</title>
</head>
<body>
    <form action="/groupware/admin/login" method="post">
        <div class="form-group">
            <label for="username">관리자 아이디</label>
            <input type="text" id="username" name="adminId" placeholder="아이디 입력" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="adminPwd" placeholder="비밀번호 입력" required>
        </div>
        <br>
        <button type="submit">로그인</button>
    </form>
</body>
</html>
