<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: Arial, sans-serif;
        }

        @keyframes gradientAnimation {
        0% {
            background-position: 0% 50%;
        }
        50% {
            background-position: 100% 50%;
        }
        100% {
            background-position: 0% 50%;
        }
    }

body {
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #d149ff, #71b4ff,#05c5fa);
    margin: 0;
    animation: gradientAnimation 7s ease infinite; /* 애니메이션 적용 */
}
        .main-container {
            display: flex;
            width: 100%;
            height: 100vh;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }
        .animation-container {
            width: 65%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            padding: 30px;
        }
        .text-area{
            padding: 20px;
            margin: 10px;
        }
        h1,h2{
            color: #fff;
        }
        .form-container {
            width: 35%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        svg {
            width: 80%;
            max-width: 250px;
            height: auto;
            transform: rotateZ(0deg);
        }
        .smile, .eyes {
            stroke: #F2EFFB;
            stroke-width: 1.3;
            stroke-linecap: round;
            fill: transparent;
        }
        svg:hover {
            animation: rotate 1.2s cubic-bezier(0.65, 0.000, 0.75, 1.000);
        }
        svg:hover .smile {
            animation: smile 1s cubic-bezier(0.2, 0.000, 0.8, 1.000);
        }
        svg:hover .eyes {
            animation: eyes 1s cubic-bezier(.7, 0.000, 0.4, 1.000);
        }
        @keyframes rotate {
            to {
                transform: rotateZ(720deg);
            }
        }
        @keyframes smile {
            50% {
                stroke-dasharray: 20, 5.1327;
            }
        }
        @keyframes eyes {
            70% {
                stroke-dasharray: 1, 0, .5, 23.6327;
            }
        }

        .tabs {
            width: 55%;
            margin-bottom: 10px;
            padding-left: 10px;
            display: flex;
            justify-content: center;
        }
        .tab {
            width: 138px;
            padding: 10px 20px;
            border: none;
            background-color: transparent;
            cursor: pointer;
            font-size: 14px;
            background-color: rgba(110, 180, 255, 0.331);
            color: #ffffff85;
            outline: none;
            flex-grow: 1;
            text-align: center;
            border-radius: 5px;
        }
        .tab.active {
            background-color: #007bff;
            color: #fff;
        }
        .tab:not(.active):hover {
            background-color: #f0f0f086;
        }
        form {
            width: 100%;
            max-width: 300px;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label{
            color: #ffffff;
        }
        input[type="text"],input[type="password"] {
            color: #191919b9; 
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
            background-color: #3f60b4e0;
            color: #ffffff;
            cursor: pointer;
            border-radius: 3px;
        }
        button:hover {
            background-color: #1b7ee9e8;
        } 


        
        .container > form {
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

        function changeFormEmp() {
            const form = document.querySelector("#loginForm");
            const adminForm = document.querySelector("#adminLoginForm");
            form.classList.add("active");
            adminForm.classList.remove("active");
        }

        function changeFormAdmin() {
            const form = document.querySelector("#loginForm");
            const adminForm = document.querySelector("#adminLoginForm");
            form.classList.remove("active");
            adminForm.classList.add("active");
        }
    </script>
</head>
<body>
    <div class="main-container">
        <div class="animation-container">
            <svg viewbox="0 0 10 10">
                <circle class="smile" cx="5" cy="5" r="4"
                    stroke-dashoffset="-.5"
                    stroke-dasharray="11.5,13.6327" />
                <circle class="eyes" cx="5" cy="5" r="4"
                    stroke-dashoffset="-15.5"
                    stroke-dasharray="0,6.6327,0,17.5" />
            </svg>
            <div class="text-area">
                <h1>Smile Office :)
                </h1>
                <h2> Revolutionizing workspace solutions with innovation and efficiency.</h2>
            </div>
        </div>
        <div class="form-container">
            <div class="tabs">
                <button onclick="changeFormEmp()" id="generalTab" class="tab active">사원</button>
                <button onclick="changeFormAdmin()" id="adminTab" class="tab">관리자</button>
            </div>
            <div class="container">
                <form action="${pageContext.request.contextPath}/emp/login" method="post" id="loginForm" class="active">
                    <div class="input-group">
                        <label for="empId">사원 아이디</label>
                        <input type="text" id="empId" name="id" placeholder="아이디를 입력하세요">
                    </div>
                    <div class="input-group">
                        <label for="empPwd">비밀번호</label>
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
        </div>
    </div>
</body>
</html>
