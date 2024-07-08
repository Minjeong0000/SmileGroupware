<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의사항 작성</title>
    <link rel="stylesheet" type="text/css" href="/css/question/add.css">
    <script defer src="/js/question/add.js"></script>
</head>
<body>
<div id="mySidenav" class="sidenav">
    <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
    <a href="#"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
    <a href="#"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
    <a href="#"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
    <a href="#"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
    <a href="#"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
    <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
</div>

<div id="main" onclick="closeNav()">
    <div class="column" id="fixedColumn">
        <h2>문의 사항</h2>
        <ul class="inquiry-list">
            <li class="inquiry-item"><a href="/question/question">• 자주 묻는 질문</a></li>
            <li class="inquiry-item"><a href="/question/add">• 💬 1:1 문의</a></li>
        </ul>
    </div>
    <div class="column content">
        <h2>1:1 문의</h2>
        <form id="inquiryForm">
            <label for="author">작성자</label>
            <input type="text" id="author" name="author" value="${loginEmployeeVo.empName}" readonly>

            <label for="title">제목</label>
            <input type="text" id="title" name="title" required>

            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" placeholder="작성할 내용"></textarea>

            <button type="submit">문의하기</button>
        </form>
    </div>
</div>
</body>
</html>
