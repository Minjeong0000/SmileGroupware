<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사용자 관리</title>
    <link rel="stylesheet" type="text/css" href="/css/emp/attendance/adminUserEdit.css">
    <script defer src="/js/adminUserEdit.js"></script>
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
        <div class="column">
            <div class="user-actions">
                <ul>
                    <li class="dropdown main-item">
                        <a href="#" class="dropdown-toggle"><span class="dropdown-icon"></span> 신규 사용자 등록</a>
                        <ul class="dropdown-menu">
                            <li><a href="registerAdmin.html">• 일반 사원등록</a></li>
                            <li><a href="registerHrAdmin.html">• 임원진 등록</a></li>
                            <li><a href="registerHrAdmin.html">• 인턴 등록</a></li>
                        </ul>
                    </li>
                    <li class="dropdown main-item">
                        <a href="#" class="dropdown-toggle"><span class="dropdown-icon"></span> 관리자 아이디 등록</a>
                        <ul class="dropdown-menu">
                            <li><a href="registerAdmin.html">• 마스터 관리자 등록</a></li>
                            <li><a href="registerHrAdmin.html">• 인사팀 관리자 등록</a></li>
                        </ul>
                    </li>
                    <li class="dropdown main-item">
                        <a href="#" class="dropdown-toggle"><span class="dropdown-icon"></span> 사용자 수정</a>
                        <ul class="dropdown-menu">
                            <li><a href="editUserPosition.html">• 통합 수정</a></li>
                            <li><a href="editUserId.html">• 아이디 수정</a></li>
                            <li><a href="editUserName.html">• 이름 수정</a></li>
                            <li><a href="editUserDept.html">• 부서 수정</a></li>
                            <li><a href="editUserPosition.html">• 직급 수정</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="column content">
            <div class="content-box">
                <div class="employee-header">
                    <span>사원번호</span>
                    <span>성명</span>
                    <span>이메일</span>
                    <span>전화번호</span>
                    <span>내선번호</span>
                    <span>직급</span>
                </div>
                <div class="employee-info" id="employee-info"></div>
                <div class="pagination">
                    <button id="prev" onclick="prevPage()">이전</button>
                    <button id="next" onclick="nextPage()">다음</button>
                </div>
                <form id="addAdminForm">
                    <label for="adminId">Admin ID:</label>
                    <input type="text" id="adminId" name="adminId" required>
                    <label for="adminPwd">Password:</label>
                    <input type="password" id="adminPwd" name="adminPwd" required>
                    <label for="adminNick">Nickname:</label>
                    <input type="text" id="adminNick" name="adminNick" required>
                    <label for="adminEmail">Email:</label>
                    <input type="email" id="adminEmail" name="adminEmail" required>
                    <label for="adminLevel">Level:</label>
                    <input type="number" id="adminLevel" name="adminLevel" required>
                    <button type="submit">Add Admin</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
