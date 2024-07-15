<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<link rel="stylesheet" type="text/css" href="/css/admin/userAdd.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script defer src="/js/admin/userAdd.js"></script>
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
            <h2>회원 등록</h2><br>
            <form id="registerForm" action="userAdd" method="post" enctype="multipart/form-data">
                <div class="left-column">
                    <div class="form-group">
                        <label for="company_no">회사 번호:</label>
                        <input type="text" id="company_no" name="companyNo" required>
                    </div>
                    <div class="form-group">
                        <label for="emp_name">이름:</label>
                        <input type="text" id="emp_name" name="empName" required>
                    </div>
                    <div class="form-group">
                        <label for="id">아이디:</label>
                        <input type="text" id="id" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="password">비밀번호:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">전화번호:</label>
                        <input type="text" id="phone" name="phone">
                    </div>
                    <div class="form-group">
                        <label for="emp_no">주민등록번호:</label>
                        <input type="text" id="emp_no" name="empNo">
                    </div>
                    <div class="form-group">
                        <label for="email">이메일:</label>
                        <input type="email" id="email" name="email">
                    </div>
                </div>
                <div class="right-column">
                    <div class="form-group">
                        <label for="hire_date">입사일:</label>
                        <input type="date" id="hire_date" name="hireDate">
                    </div>
                    <div class="form-group">
                        <label for="department_no">부서 번호 (DEPARTMENT) :</label>
                        <select id="department_no" name="departmentNo">
                            <!-- Options will be populated by JavaScript -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="role_no">역할 번호 (ROLE) :</label>
                        <select id="role_no" name="roleNo">
                            <!-- Options will be populated by JavaScript -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="position_no">직위 번호 (POSITION) :</label>
                        <select id="position_no" name="positionNo">
                            <!-- Options will be populated by JavaScript -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="profile">프로필 사진:</label>
                        <input type="file" id="profile" name="profileFile" accept="image/*" onchange="previewImage(event)">
                    </div>
                    <div class="form-group profile-image">
                        <div class="image-placeholder">
                            <img id="profileImage" src="#" alt="프로필 사진" style="display:none;"/>
                        </div>
                    </div>
                </div>
                <div class="form-group full-width">
                    <button type="submit">등록</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
