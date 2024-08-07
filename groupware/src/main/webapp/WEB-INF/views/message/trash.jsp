<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>휴지통</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script defer src="${pageContext.request.contextPath}/js/message/common.js"> </script>

<script defer src="${pageContext.request.contextPath}/js/message/trash.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/message/received.css">

</head>
<body>
  <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>


    <div id="main" onclick="closeNav()">
        <div class="column">

          <%@ include file="message_common_left.jsp" %>

        </div>
        <div class="column content">
            <div class="receive-msg-container">

                <div class="msg-top"> <h1>휴지통</h1></div>

            </div>

          <table>

                <thead>
                        <div class="top-btn-wrap">
                            <button onclick="deleteForever();" id="delete"><i class="fa-solid fa-trash"></i>영구삭제</button>
                            <button onclick="restoreMessage();"  id="read"><i class="fa-solid fa-trash-arrow-up"></i>복구하기</button>
                        </div>
                </thead>
                <thead>
                  <tr>
                    <th id="checkbox"><input type="checkbox" id="select-all"></th>
                    <th id="importantYn"></th>
                    <th id="readYn"></th>
                    <th id="sender">보낸이</th>
                    <th id="messageContent">내용</th>
                    <th id="sendAt">일시</th>
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

            <label for="departments">부서:</label>
            <select id="departments">
                <option value="">부서를 선택하세요</option>
            </select>
        
            <label for="employees">사원:</label>
            <select id="employees">
                <option value="">사원을 선택하세요</option>
            </select>              
              <label for="message">메시지:</label>
              <textarea id="message" name="message" rows="4" required></textarea>
              
              <button id="sendMessageBtn">보내기</button>

      </div>
    </div>

  <div id="msg-detail-modal" class="msg-detail-modal">
    <div class="msg-detail-modal-content">
      <span class="close">&times;</span>
      <h2>쪽지 상세 정보</h2>
      <div class="messageDetail">
          <p><strong>발신인:</strong> <span id="senderName"></span></p>
          <p><strong>수신인:</strong> <span id="receiverName"></span></p>
          <p><strong>일시:</strong> <span id="sendTime"></span></p>
          <p><strong>내용:</strong></p>
          <div class="detail-content-wrap">
            
            <div id="msgContent">

            </div>
          </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>