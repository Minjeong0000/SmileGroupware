<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>슬라이드 네비게이터 바</title>
<link rel="stylesheet" type="text/css" href="/css/emp/attendance/adminAdd.css">
<script defer src="/js/adminAdd.js"></script>
</head>
<body>
    <div id="mySidenav" class="sidenav">
        <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
        <a href="/admin/home"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
        <a href="#"><span class="menu-icon">&#128196;</span><span class="link-text">공지사항 관리</span></a>
        <a href="#"><span class="menu-icon">&#128172;</span><span class="link-text">문의사항</span></a>
        <a href="/admin/userEdit"><span class="menu-icon">&#128295;</span><span class="link-text">사용자 관리</span></a>
        <a href="#"><span class="menu-icon">🏢</span><span class="link-text">사원 조직도</span></a>
        <a href="#"><span class="menu-icon">&#128337;</span><span class="link-text">직원 근태관리</span></a>
    </div>

    <div id="main" onclick="closeNav()">
        <div class="column content">
            <h2>관리자 추가</h2>
            <form id="adminForm">
                <div class="form-group">
                    <label for="adminId">관리자 아이디 (닉네임) :</label>
                    <input type="text" id="adminId" name="adminId" required>
                </div>
                <div class="form-group">
                    <label for="adminPwd">관리자 비밀번호 :</label>
                    <input type="password" id="adminPwd" name="adminPwd" required>
                </div>
                <div class="form-group">
                    <label for="adminNick">관리자 닉네임 :</label>
                    <input type="text" id="adminNick" name="adminNick" required>
                </div>
                <div class="form-group">
                    <label for="adminEmail">관리자 이메일 :</label>
                    <input type="email" id="adminEmail" name="adminEmail" required>
                </div>
                <div class="form-group">
                    <label for="adminLevel">관리자 권한 :</label>
                    <input type="text" id="adminLevel" name="adminLevel" required>
                    <div id="adminLevelDesc" class="admin-level-desc"></div>
                </div>
                <input type="submit" value="추가" class="submit-btn">
            </form>
        </div>
    </div>
</body>
</html>
