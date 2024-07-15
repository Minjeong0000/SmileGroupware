<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사원 관리</title>
    <link rel="stylesheet" type="text/css" href="/css/admin/adminUserEdit.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/admin/adminUserEdit.js"></script>
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
                <div class="employee-header">
                    <span>사원번호</span>
                    <span>성명</span>
                    <span>이메일</span>
                    <span>전화번호</span>
                    <span>직위</span>
                    <span>직책</span>
                </div>
                <div class="employee-info" id="employee-info"></div>
                <div class="pagination">
                    <button id="prev" onclick="prevPage()">이전</button>
                    <button id="next" onclick="nextPage()">다음</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 모달 구조 추가 -->
    <div id="userDetailModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <div class="modal-header">
                <h2>사원 정보</h2>
            </div>
            <div class="modal-body">
                <img id="profileImage" src="" alt="Profile Image" class="profile-image">
                <div class="user-info">
                    <p><strong>사원번호:</strong> <span id="empId"></span></p>
                    <p><strong>성명:</strong> <span id="empName"></span></p>
                    <p><strong>이메일:</strong> <span id="email"></span></p>
                    <p><strong>전화번호:</strong> <span id="phone"></span></p>
                    <p><strong>직위:</strong> <span id="positionName"></span></p>
                    <p><strong>직책:</strong> <span id="roleName"></span></p>
                    <button id="deleteButton" class="delete-button">삭제</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
