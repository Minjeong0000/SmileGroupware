<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>문의사항 목록</title>
<link rel="stylesheet" type="text/css" href="/css/admin/adminQuestion.css">
<script defer src="/js/admin/adminQuestion.js"></script>
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
        <div class="form-container">
            <div class="question-box">
                <div class="board-header">
                    <h2>문의사항</h2>
                    <div class="pagination">
                        <button id="prev-inquiry" onclick="prevPageInquiry()">이전</button>
                        <button id="next-inquiry" onclick="nextPageInquiry()">다음</button>
                    </div>
                </div>
                <div class="inquiry-items">
                    <!-- 문의사항 내용이 여기에 추가됩니다 -->
                </div>
            </div>
        </div>
    </div>
</body>
</html>
