<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/admin.css">
<script defer src="/js/admin.js"></script>
</head>

<body>
    <div id="mySidenav" class="sidenav">
        <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
        <a href="/html/admin.html"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
        <a href="#"><span class="menu-icon">&#128196;</span><span class="link-text">공지사항 관리</span></a>
        <a href="#"><span class="menu-icon">&#128172;</span><span class="link-text">문의사항</span></a>
        <a href="/html/userEdit.html"><span class="menu-icon">&#128295;</span><span class="link-text">사용자 관리</span></a>
        <a href="#"><span class="menu-icon">🏢</span><span class="link-text">사원 조직도</span></a>
        <a href="#"><span class="menu-icon">&#128337;</span><span class="link-text">직원 근태관리</span></a>
    </div>

    <div id="content-wrapper">
        <div id="main">
            <div class="user-profile">
                <div class="date-time">2023년 5월 6일 (토)</div>
                <div class="current-time"></div>
                <img src="/img/test.jpg" alt="Profile Picture" class="profile-pic">
                <div class="user-details">
                    <h3>MasterAdmin</h3>
                    <p>스마일그룹</p>
                    <h2>권한 Level : Master</h2>
                </div>
            </div>

            <div class="grid-container">
                <a href="/notice" class="grid-item">공지사항</a>
                <a href="/inquiries" class="grid-item">문의사항</a>
                <a href="/edit-users" class="grid-item">사용자 수정</a>
                <a href="/attendance" class="grid-item">근태관리</a>
            </div>
        </div>

        <!-- 새로운 전사 게시판 섹션 -->
        <div id="company-board">
            <h2>전사게시판</h2>
            <div class="board-items" id="board-items">
                <!-- 게시글 예시 -->
                <div class="board-item">
                    <a href="#"><h3>제목 : 11111111111111111</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 2222222222222222222</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 33333333333333333</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 4444444444444444444</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 55555555555555555555</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 6666666666666666</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 777777777777777777</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 888888888888888888</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 999999999999999999999</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 10000000000000000000000000</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 11111111111111111111111111</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>


                <!-- 추가 게시물 자리 -->
            </div>
            <div class="pagination-board">
                <button id="prev-page"> < </button>
                <button id="next-page"> > </button>
            </div>
        </div>

        <!-- 새로운 공지사항 섹션 -->
        <div id="notion-board">
            <h2>공지사항</h2>
            <div class="notion-items">
                <div class="notion-item">
                    <a href="#"><h3>제목 : 111111111111111111</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 222222222222222222</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 3333333333333333</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 4444444444444444444</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 55555555555555555555</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 666666666666666666666</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 7777777777777777777</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <!-- 추가 게시물 자리 -->
            </div>
            <div class="pagination-notion">
                <button id="prev-page-notion"> < </button>
                <button id="next-page-notion"> > </button>
            </div>
        </div>
    </div>
</body>
</html>
