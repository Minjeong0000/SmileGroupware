
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬로월드</title>

<style>
  #result {
    width: 1000px;
    height: 300px;
    border: 1px solid black;
  }

  /* 게시판 CSS 추가 시작 */
#noticeBoard {
    width: 100%; /* 테이블 전체 너비를 부모 요소에 맞춤 */
    margin-top: 20px; /* 상단 여백 추가 */
}

#noticeBoard th {
    background-color: #edeef3; /* 헤더 배경색 설정 */
    color: rgb(32, 31, 31); /* 헤더 텍스트 색상 */
    padding: 10px; /* 헤더 패딩 */
}

#noticeBoard td {
    text-align: center; /* 셀 내용을 중앙 정렬 */
    padding: 8px; /* 셀 패딩 */
}
/* 게시판 CSS 추가 종료 */

#writeButton {
  padding: 10px 20px;
  text-align: center;
  margin-bottom: 2px;
  background-color: #edeef3;
  color: rgb(32, 31, 31);
}
</style>
<link rel="stylesheet" type="text/css" href="/css/common/common.css">
<script defer src="/js/common/common.js"></script>
</head>
<body>
  <div id="mySidenav" class="sidenav">
    <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
    <a href="/home.jsp"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
    <a href="/event/personal/calendar"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
    <a href="/emp/attendance/cal"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
    <a href="/approval/home"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
    <a href="/message/received"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
    <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
  </div>

    <div id="main" onclick="closeNav()">
        <div class="column">
          <h1>사내공지사항</h1>
        </div>
        <div class="column content">
          <button id="writeButton" onclick="">글쓰기</button>
          <table id="noticeBoard" border="1">
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
              </tr>
            </thead>

            <tbody> </tbody>
          </table>
        
          <hr>
        
          <div id="result"></div>

        </div>
    </div>
</div>
</body>
</html>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/js/notice/noticeList.js"></script>
<script src="/js/notice/noticeDetail.js"></script>

<script>
const tbody = document.querySelector("tbody");
tbody.addEventListener( "click" , getNoticeByNo );
</script>





