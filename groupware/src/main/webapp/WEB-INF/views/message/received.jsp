<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>받은쪽지함</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script defer src="${pageContext.request.contextPath}/js/message/common.js"> </script>
<script defer src="${pageContext.request.contextPath}/js/message/received.js"></script>

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

                <div class="msg-top"> <h1>받은 쪽지함</h1></div>

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
                            <button  onclick="deleteCheckedMessage();" id= "delete" ><i class="fa-solid fa-trash"></i>휴지통으로 이동</button>
                            <button  onclick="readCheckedMessage();" id= "read" ><i class="fa-solid fa-check"></i>읽음</button>
                        </div>
                    </th>
                    <th>
                        <div><button id="write"><i class="fa-solid fa-pencil"></i>쓰기</button></div>
                    </th>
                </thead>
                <thead>
                  <tr>
                    <th><input type="checkbox" id="select-all"></th>
                    <th>보낸이</th>
                    <th>내용</th>
                    <th>읽음 여부</th>
                    <th>일시</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td colspan="6" style="text-align: center;">조회된 데이터가 없습니다.</td>
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
    <div id="modal" class="modal">
      <div class="modal-content">
          <span class="close">&times;</span>
          <h2>쪽지 보내기</h2>
          <form id="messageForm">
              <label for="recipient">받는 사람:</label>
              <select id="recipient" name="recipient" required>
                <option value="" disabled selected>받는 사람을 선택하세요</option>
                <option value="user1">사용자 1</option>
                <option value="user2">사용자 2</option>
                <option value="user3">사용자 3</option>
            </select>                
              <label for="message">메시지:</label>
              <textarea id="message" name="message" rows="4" required></textarea>
              
              <button type="submit">보내기</button>
          </form>
      </div>
  </div>


</div>
</body>
</html>