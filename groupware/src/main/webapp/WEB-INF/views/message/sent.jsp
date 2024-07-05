<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>보낸쪽지함</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script defer src="${pageContext.request.contextPath}/js/message/sent.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/message/received.css">

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
        <div class="column">

          <%@ include file="message_common_left.jsp" %>


        </div>
        <div class="column content">
            <div class="receive-msg-container">

                <div class="msg-top"> <h1>보낸 쪽지함</h1></div>

            </div>

          <table>
              <!--
                              <thead>
                    <tr>
                        <th colspan="3">
                            <form class="search-container" action="/search" method="get">
                                <input type="text" name="search" placeholder="제목 혹은 작성자 검색">
                                <button id="search" type="submit">검색</button>
                              </form>
                        </th>
                        <th>

                        </th>

                    </tr>
                </thead>
              -->
                <thead>
                    <th colspan="3">
                        <div>
                            <button onclick="deleteCheckedMessage();" id="delete"><i class="fa-solid fa-trash"></i>휴지통으로 이동</button>
                            <button id="read"><i class="fa-solid fa-trash-arrow-up"></i>???</button>
                        </div>
                    </th>
                    <th>
                        <div><button id="write"><i class="fa-solid fa-pencil"></i>쓰기</button></div>
                    </th>
                </thead>
                <thead>
                  <tr>
                    <th><input type="checkbox" id="select-all"></th>
                    <th>받는이</th>
                    <th>수신자 읽음여부</th>
                    <th>내용</th>
                    <th>일시</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td colspan="5" style="text-align: center;">조회된 데이터가 없습니다.</td>
                  </tr>
                </tbody>
              </table>
                <div class="paging-bar">
                    <button>&laquo;</button>
                    <button>&lt;</button>
                    <button class="active">1</button>
                    <button>&gt;</button>
                    <button>&raquo;</button>
                  </div>
        </div>
    </div>
</div>
</body>
</html>