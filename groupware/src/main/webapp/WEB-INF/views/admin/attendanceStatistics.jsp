<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>근태 통계</title>
<link rel="stylesheet" type="text/css" href="/css/admin/attendanceStatistics.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script defer src="/js/admin/attendanceStatistics.js"></script>
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

    <div id="main">
        <div class="form-container">
            <div class="statistics-box">
                <h2>직원 근태 통계</h2>
                <table id="statisticsTable">
                    <thead>
                        <tr>
                            <th>사원 번호</th>
                            <th>사원 이름</th>
                            <th>근무시간(시간)</th>
                            <th>상세정보</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 데이터는 JS에 의해 추가될 예정 -->
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 상세 정보를 표시하는 모달 -->
        <div id="detailsModal" class="modal" style="display: none;">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>Attendance Details</h2>
                <table id="detailsTable">
                    <thead>
                        <tr>
                            <th>아직 미정</th>
                            <th>출근</th>
                            <th>퇴근</th>
                            <th>근무시간</th>
                            <th>상태</th>
                        </tr>
                    </thead>
                    <tbody>

                        <!-- 데이터는 JS에 의해 추가될 예정 -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
