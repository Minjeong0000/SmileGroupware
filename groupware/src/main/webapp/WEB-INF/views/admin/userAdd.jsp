<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<link rel="stylesheet" type="text/css" href="/css/admin/userAdd.css">
<script defer src="/js/admin/userAdd.js"></script>
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
            <h2>회원 등록</h2><br>
            <form id="registerForm">
                <div class="left-column">
                    <div class="form-group">
                        <label for="emp_name">이름:</label>
                        <input type="text" id="emp_name" name="emp_name" required>
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
                        <input type="text" id="emp_no" name="emp_no">
                    </div>
                    <div class="form-group">
                        <label for="email">이메일:</label>
                        <input type="email" id="email" name="email">
                    </div>
                </div>
                <div class="right-column">
                    <div class="form-group">
                        <label for="hire_date">입사일:</label>
                        <input type="date" id="hire_date" name="hire_date">
                    </div>
                    <div class="form-group">
                        <label for="department_no">부서 번호:</label>
                        <input type="text" id="department_no" name="department_no">
                    </div>
                    <div class="form-group">
                        <label for="role_no">역할 번호:</label>
                        <input type="text" id="role_no" name="role_no">
                    </div>
                    <div class="form-group">
                        <label for="position_no">직위 번호:</label>
                        <input type="text" id="position_no" name="position_no">
                    </div>
                    <div class="form-group">
                        <label for="profile">프로필 사진:</label>
                        <input type="file" id="profile" name="profile" accept="image/*" onchange="previewImage(event)">
                    </div>
                    <div class="form-group profile-image">
                        <div class="image-placeholder">
                            <img id="profileImage" src="#" alt="프로필 사진" style="display:none;"/>
                        </div>
                    </div>
                </div>
                <div class="form-group full-width">
                    <button type="button" onclick="registerEmployee()">등록</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
