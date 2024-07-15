<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="/css/admin/adminHome.css">
    <script defer src="/js/admin/adminHome.js"></script>
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

    <div id="content-wrapper">
        <div id="main">
            <div class="user-profile">
                <div class="date-time"></div>
                <div class="current-time"></div>
                <img src="/img/admin.png" alt="Profile Picture" class="profile-pic">
                <div class="user-details">
                    <h3>MasterAdmin</h3>
                    <p>스마일그룹</p>
                    <h2>권한 Level : Master</h2>
                </div>
            </div>

            <div class="grid-container">
                <a href="#" class="grid-item">공지사항</a>
                <a href="userEdit" class="grid-item">사용자 수정</a>
                <a href="attendanceStatistics" class="grid-item">근태관리</a>
                <a href="adminQuestion" class="grid-item">문의사항</a>
            </div>
        </div>

        <!-- 전사 게시판 섹션 -->
        <div id="company-board">
            <div class="board-header">
                <h2>전사게시판</h2>
                <div class="pagination">
                    <button id="prev" onclick="prevPage()">이전</button>
                    <button id="next" onclick="nextPage()">다음</button>
                </div>
            </div>
            <div class="board-items" id="board-items">
                <!-- 게시글 예시 -->
                <div class="board-item">
                    <a href="#"><h3>제목 : 집에 가고 싶다 언제끝나지</h3></a>
                    <span>게시일: 2023-06-21</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 끝나고 뭐하지...</h3></a>
                    <span>게시일: 2023-06-22</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 배고프당 점심 뭐묵을까요?</h3></a>
                    <span>게시일: 2023-06-24</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 집가서 뭐하징...</h3></a>
                    <span>게시일: 2023-06-27</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 대표님 누구징?</h3></a>
                    <span>게시일: 2023-06-29</span>
                </div>
                <div class="board-item">
                    <a href="#"><h3>제목 : 놀고싶당</h3></a>
                    <span>게시일: 2023-06-29</span>
                </div>

                <!-- 추가 게시물 자리 -->
            </div>
        </div>

        <!-- 공지사항 섹션 -->
        <div id="notion-board">
            <div class="board-header">
                <h2>공지사항</h2>
                <div class="pagination">
                    <button id="prev-notion" onclick="prevPageNotion()">이전</button>
                    <button id="next-notion" onclick="nextPageNotion()">다음</button>
                </div>
            </div>
            <div class="notion-items">
                <div class="notion-item">
                    <a href="#"><h3>제목 : 대표 ooo입니다. 앞으로 잘 부탁드립니다.</h3></a>
                    <span>게시일: 2023-06-25</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 공지사항 작성하는 곳입니다.</h3></a>
                    <span>게시일: 2023-06-22</span>
                </div>
                <div class="notion-item">
                    <a href="#"><h3>제목 : 새로운 공지사항 입니다. </h3></a>
                    <span>게시일: 2023-06-24</span>
                </div>
            </div>
        </div>

        <!-- 문의사항 섹션 -->
        <div id="inquiry-board">
            <div class="board-header">
                <h2>문의사항</h2>
                <div class="pagination">
                    <button id="prev-inquiry" onclick="prevPageInquiry()">이전</button>
                    <button id="next-inquiry" onclick="nextPageInquiry()">다음</button>
                </div>
            </div>
            <div class="inquiry-items">
                <!-- Ajax처리 --%>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const logoutButton = document.querySelector('.logout-button');

            logoutButton.addEventListener('click', (event) => {
                event.preventDefault();
                const confirmed = confirm('로그아웃 하시겠습니까?');
                if (confirmed) {
                    window.location.href = logoutButton.href;
                }
            });
        });
    </script>
</body>
</html>
