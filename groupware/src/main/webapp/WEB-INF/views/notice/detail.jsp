
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사내공지사항목록</title>

<link rel="stylesheet" type="text/css" href="/css/common/common.css">
<link rel="stylesheet" type="text/css" href="/css/notice/notice.css">
<script defer src="/js/common/common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

</head>
<body>
  <div id="mySidenav" class="sidenav">
    <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
    <a href="/home"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
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
            <h2>공지사항 상세조회</h2>
            
            <div id="button">
                <button id="writeButton" onclick="location.href='/notice/list'">목록보기</button>
            </div>
       

          <hr>
          <tbody onclick="getNoticeByNo();"></tbody>
          <div id="result"></div>

          

        </div>
    </div>
</div>
</body>
</html>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script defer src="/js/notice/noticeList.js"></script>
<script defer src="/js/notice/noticeDetail.js"></script>
<link rel="stylesheet" href="/css/notice/notice.css">


<script>


</script>
















