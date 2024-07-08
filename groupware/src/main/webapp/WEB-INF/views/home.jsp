<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>홈</title>
<link rel="stylesheet" type="text/css" href="/css/home.css">
<script defer src="/js/home.js"></script>
</head>


<body>
    <div id="mySidenav" class="sidenav">
      <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
      <a href="/home"><span class = "menu-icon">&#8962;</span><span class="link-text">홈</span></a>
      <a href="#"><span class = "menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
      <a href="#"><span class = "menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
      <a href="#"><span class = "menu-icon">&#128203;</span><span class="link-text">결재</span></a>
      <a href="#"><span class = "menu-icon">📫</span><span class="link-text">메일</span></a>
      <a href="/question/question"><span class = "menu-icon">📞</span><span class="link-text">문의사항</span></a>
      <a href="#"><span class = "menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
    </div>

    <div id="content-container">
        <div id="main">
            <div class="user-profile">
                <div class="date-time">2023년 5월 6일 (토)</div>
                <img src="/img/test.jpg" alt="Profile Picture" class="profile-pic">
                <div class="user-details">
                    <h3>문태웅 부장</h3>
                    <p>인사팀 운용</p>
                </div>
                <div class="current-time">16:09:25</div>
                <div class="work-status">
                    <div class="status">업무 상태: <span>근무 중</span></div>
                    <div class="time-entry">
                        <div>출근 시간: <span>09:00</span></div>
                        <div>퇴근 시간: <span>18:00</span></div>
                    </div>
                </div>
                <div class="actions">
                    <button class="action">출근하기</button>
                    <button class="action">퇴근하기</button>
                </div>
            </div>
        </div>
        <div id="announcement-board">
            <div class="board-header">
                <h2>공지 게시판</h2>
            </div>
            <div class="board-content">
                <ul>
                    <li>
                        <span>대회 입상 소식</span>
                        <img src="/img/test.jpg" alt="Profile 1" class="profile-pic">
                        <small>2023-04-11</small>
                    </li>
                    <li>
                        <span>신입 사원 오리엔테이션</span>
                        <img src="/img/test.jpg" alt="Profile 2" class="profile-pic">
                        <small>2023-04-11</small>
                    </li>
                    <li>
                        <span>연말 정산 안내</span>
                        <img src="/img/test.jpg" alt="Profile 3" class="profile-pic">
                        <small>2023-04-11</small>
                    </li>
                </ul>
            </div>
        </div>
        <div id="approval-documents-board">
            <div class="board-header">
                <h2>결재 대기 문서</h2>
            </div>
            <div class="board-content">
                <table class="document-table">
                    <thead>
                        <tr>
                            <th>기안일</th>
                            <th>결재양식</th>
                            <th>긴급</th>
                            <th>결재상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>2023-04-11</td>
                            <td>비용청구서</td>
                            <td>긴급</td>
                            <td><button class="status-button pending">결재</button></td>
                        </tr>
                        <tr>
                            <td>2023-04-12</td>
                            <td>여비 정산</td>
                            <td>일반</td>
                            <td><button class="status-button complete">완료</button></td>
                        </tr>
                        <tr>
                            <td>2023-04-13</td>
                            <td>구매 요청서</td>
                            <td>긴급</td>
                            <td><button class="status-button rejected">반려</button></td>
                        </tr>
                        <!-- 추가된 예시 및 설명 -->
                        <tr>
                            <td>2023-04-14</td>
                            <td>프로젝트 계약서</td>
                            <td>일반</td>
                            <td><button class="status-button pending">결재</button></td>
                        </tr>
                        <tr>
                            <td>2023-04-15</td>
                            <td>연간 예산 계획</td>
                            <td>일반</td>
                            <td><button class="status-button complete">완료</button></td>
                        </tr>
                        <tr>
                            <td>2023-04-16</td>
                            <td>직원 채용 제안</td>
                            <td>긴급</td>
                            <td><button class="status-button rejected">반려</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>