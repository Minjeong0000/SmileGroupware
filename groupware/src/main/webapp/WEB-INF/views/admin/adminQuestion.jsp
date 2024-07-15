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
            <a href="/admin/home"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
            <a href="/admin/notion"><span class="menu-icon">&#128196;</span><span class="link-text">공지사항 관리</span></a>
            <a href="/admin/adminQuestion"><span class="menu-icon">&#128172;</span><span class="link-text">문의사항</span></a>
            <a href="/admin/userEdit"><span class="menu-icon">&#128295;</span><span class="link-text">사용자 관리</span></a>
            <a href="#"><span class="menu-icon">🏢</span><span class="link-text">사원 조직도</span></a>
            <a href="attendanceStatistics"><span class="menu-icon">&#128337;</span><span class="link-text">직원 근태관리</span></a>
            <a href="/admin/logout" class="logout-button"><span class="menu-icon">❌</span><span class="link-text">로그아웃</span></a>
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
