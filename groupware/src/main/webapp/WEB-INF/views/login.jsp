<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
    body, html {
      margin: 0;
      padding: 0;
      height: 100%;
      font-family: Arial, sans-serif;
    }
    body{
      height: 100vh;
      width: 100%;
      margin: 0 auto;
    }
    .container {
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
    }
    h2 {
      margin-bottom: 20px;
    }
    form {
      width: 80%;
      max-width: 300px;
      border: 1px solid #ccc;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .input-group {
      margin-bottom: 15px;
    }
    .input-group label {
      display: block;
      margin-bottom: 5px;
    }
    .input-group input {
      width: 95%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }
    button {
      width: 100%;
      padding: 10px;
      border: none;
      background-color: #334D8F;
      color: #fff;
      cursor: pointer;
      border-radius: 3px;
    }
    button:hover {
      background-color: #0056b3;
    }
    .tabs {
        margin-bottom: 0px;
        display: flex;
    }
    .tab {
      width: 170px;
        padding: 10px 20px;
        border: none;
        background-color: transparent;
        cursor: pointer;
        font-size: 14px;
        color: #333;
        outline: none;
        flex-grow: 1;
        text-align: center;
    }
    .tab.active {
        background-color: #007bff;
        color: #fff;
    }
    .tab:not(.active):hover {
        background-color: #f0f0f0;
    }
    .container > form{
      display: none;
    }
    .container > form.active {
      display: block;
    }
    </style>
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        const tabs = document.querySelectorAll('.tab');
        const loginForm = document.getElementById('loginForm');

        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                tabs.forEach(t => t.classList.remove('active'));
                tab.classList.add('active');

            });
        });

      });

    //

    function changeFormEmp(){
        const form = document.querySelector("#loginForm");
        const adminForm = document.querySelector("#adminLoginForm");
        form.classList.add("active");
        adminForm.classList.remove("active");
    }


    function changeFormAdmin(){
        const form = document.querySelector("#loginForm");
        const adminForm = document.querySelector("#adminLoginForm");
        form.classList.remove("active");
        adminForm.classList.add("active");
    }
    </script>

</head>
<body>
    <div class="container">

        <div class="tabs">
            <button onclick="changeFormEmp()" id="generalTab" class="tab active">사원</button>
            <button onclick="changeFormAdmin()" id="adminTab" class="tab">관리자</button>
        </div>
        <form action="${pageContext.request.contextPath}/emp/login" method="post" id="loginForm" class="active" >
            <div class="input-group">
                <label for="empId">Employee ID</label>
                <input type="text" id="empId" name="id" placeholder="아이디를 입력하세요">
            </div>
            <div class="input-group">
                <label for="empPwd">Password</label>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요">
            </div>
            <button type="submit">Login</button>
        </form>

        <form action="${pageContext.request.contextPath}/admin/login" method="post" id="adminLoginForm">
            <div class="input-group">
                <label for="username">관리자 아이디</label>
                <input type="text" id="userId" name="adminId" placeholder="아이디를 입력하세요">
            </div>
            <div class="input-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="adminPwd" placeholder="비밀번호를 입력하세요">
            </div>
            <button type="submit">Login</button>
            <c:if test="${not empty errMsg}">
                <div class="error-message">${errMsg}</div>
            </c:if>
        </form>
    </div>


</body>
</html>
