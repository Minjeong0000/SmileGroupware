<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사용자 관리</title>
    <link rel="stylesheet" type="text/css" href="/css/admin/adminCheck.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/admin/adminCheck.js"></script>
</head>
<body>
    <div id="mySidenav" class="sidenav">
        <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
        <a href="/admin/home"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
        <a href="/admin/notion"><span class="menu-icon">&#128196;</span><span class="link-text">공지사항 관리</span></a>
        <a href=""><span class="menu-icon">&#128172;</span><span class="link-text">문의사항</span></a>
        <a href="/admin/userEdit"><span class="menu-icon">&#128295;</span><span class="link-text">사용자 관리</span></a>
        <a href="#"><span class="menu-icon">🏢</span><span class="link-text">사원 조직도</span></a>
        <a href="#"><span class="menu-icon">&#128337;</span><span class="link-text">직원 근태관리</span></a>
    </div>

<div id="main" onclick="closeNav()">
    <div class="column">
        <div class="user-actions">
            <ul>
                <li class="dropdown main-item">
                    <h2>사원 관리</h2>
                    <a href="#" class="dropdown-toggle"><span class="dropdown-icon"></span> 신규 사용자 등록</a>
                    <ul class="dropdown-menu">
                        <li><a href="/admin/userAdd">• 일반 사원등록</a></li>
                        <li><a href="/admin/userAdd">• 임원진 등록</a></li>
                        <li><a href="/admin/userAdd">• 인턴 등록</a></li>
                    </ul>
                </li>

                <br><br><br>
                <h2>관리자 관리</h2>
                <li class="dropdown main-item">
                    <a href="#" class="dropdown-toggle"><span class="dropdown-icon"></span> 관리자 등록</a>
                    <ul class="dropdown-menu">
                        <li><a href="/admin/adminAdd">• 마스터 관리자 등록</a></li>
                        <li><a href="/admin/adminAdd">• 인사팀 관리자 등록</a></li>
                    </ul>
                </li>
                <li class="dropdown main-item">
                <li><a href="/admin/adminCheck">&nbsp;• &nbsp;&nbsp;관리자 조회</a></li>
                </li>

            </ul>
        </div>
    </div>
    <div class="column content">
        <div class="content-box">
            <div class="admin-header">
                <span>관리자번호</span>
                <span>아이디</span>
                <span>닉네임</span>
                <span>이메일</span>
                <span>관리자 권한 레벨</span>
                <span>삭제</span>
            </div>
            <div class="admin-info" id="admin-info"></div>
            <div class="pagination">
                <button id="prev" onclick="prevPage()">이전</button>
                <button id="next" onclick="nextPage()">다음</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
