<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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