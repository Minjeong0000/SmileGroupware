<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>홈</title>
<link rel="stylesheet" type="text/css" href="/css/home.css">
<script defer src="/js/home.js"></script>
<script>
  function confirmLogout(event) {
    event.preventDefault(); // a 태그의 기본 동작을 막음
    var result = confirm("로그아웃 하시겠습니까?");
    if (result) {
      // 확인을 누르면 로그아웃 요청을 보냄
      window.location.href = event.currentTarget.href;
    }
  }

  // FontAwesome CDN이 로드되지 않았으면 로드
  (function() {
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css';
    link.integrity = 'sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==';
    link.crossOrigin = 'anonymous';
    link.referrerPolicy = 'no-referrer';

    var head = document.getElementsByTagName('head')[0];
    var existingLink = document.querySelector('link[href="' + link.href + '"]');
    if (!existingLink) {
      head.appendChild(link);
    }
  })();
  </script>
</head>


<body>
<div id="mySidenav" class="sidenav">
  <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
  <a href="/home"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
  <a href="/event/personal/calendar"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
  <a href="/emp/attendance/cal"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
  <a href="/approval/home"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
  <a href="/message/received"><span class="menu-icon">&#9742;</span><span class="link-text">쪽지</span></a>
  <a href="/board/list"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
  <a href="/emp/logout" onclick="confirmLogout(event)" ><span class="menu-icon"><i class="fa-solid fa-right-from-bracket"></i></span><span class="link-text">로그아웃</span></a>
</div>

    <div id="content-container">
        <div id="main">
            <div class="user-profile">
                <div class="date-time">2024년 7월 23일 (월)</div>
                <img src="/img/userProfile/haEmp.jpg" alt="Profile Picture" class="profile-pic">
                <div class="user-details">
                           <div>
                                <h3><span>${sessionScope.loginEmployeeVo.empName}</span></h3>
                                <span>${sessionScope.loginEmployeeVo.departmentName}</span>|
                                <span>${sessionScope.loginEmployeeVo.roleName}</span>
                                <span style="display: none;" id="empId">${sessionScope.loginEmployeeVo.empId}</span>
                              </div>
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
                <button id="directbtn"  onclick="location.href='/notice/list'">공지 게시판 바로가기</button>
            </div>
            <div class="board-content" >
                <ul>
                    <li>
                        <span>공 고 문</span>
                        <!-- <img src="/img/test.jpg" alt="Profile 1" class="profile-pic"> -->
                        <small>2024-06-11</small>
                    </li>
                    <li>
                        <span>신입 사원 오리엔테이션</span>
                        <!-- <img src="/img/test.jpg" alt="Profile 2" class="profile-pic"> -->
                        <small>2024-04-11</small>
                    </li>
                    <li>
                        <span>사내 공지 긴급 전달</span>
                        <!-- <img src="/img/test.jpg" alt="Profile 3" class="profile-pic"> -->
                        <small>2024-04-11</small>
                    </li>
                </ul>
            </div>
        </div>

        <div id="approval-documents-board">
            <div class="board-header">
                <button  id="directbtn" onclick="location.href='/approval/home'">결재 대기 문서 바로가기</button>
            </div>
            <div class="board-content" >
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