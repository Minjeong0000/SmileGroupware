
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
  <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>
<div id="main" onclick="closeNav()">
  <div class="column">

   
      
</div>
        <div class="column content">
          <center><h2>사내공지사항</h2></center>
           
            <button id="writeButton" onclick="location.href='/notice/write'">글쓰기</button>
         
          <table id="noticeBoard" border="1">
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            

              </tr>
            </thead>

            <tbody> </tbody>
            
          </table>
        

          

        </div>
    </div>
</div>
</body>
</html>



<script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script defer src="/js/notice/noticeList.js"></script>
<script defer src="/js/notice/noticeDetail.js"></script>



    
<script>
  document.addEventListener("DOMContentLoaded", function() {
      const tbody = document.querySelector("#noticeBoard tbody");
  
      tbody.addEventListener("click", function(event) {
          const tr = event.target.closest('tr');
          if (tr) {
              const no = tr.querySelector('td').textContent; // 클릭한 행의 첫 번째 셀을 번호로 사용
              const h3Tag = document.querySelector("h3");
              const noticeContent = document.querySelector("#noticeContent");
              
              $.ajax({
                  url: "/api/notice/detail", // 서버의 URL
                  method: "GET",
                  data: { no: no },
                  success: function(data) {
                      location.href = '/notice/detail?no=' + no;
                      const h3Tag = document.createElement("h3");
                      const textNode01 = document.createTextNode("글번호 : " + data.no);
                      h3Tag.appendChild( textNode01 );
                  
                      const h3Tag02 = document.createElement("h3");
                      const textNode02 = document.createTextNode("제목 : " + data.title);
                      h3Tag02.appendChild( textNode02 );
                  
                      const preTag = document.createElement("pre");
                      const textNode03 = document.createTextNode(data.content);
                      preTag.appendChild( textNode03 );
                
                      
                
                      const result = document.querySelector("#result");
                      // result.innerHTML = "";
                      result.appendChild(h3Tag);
                      result.appendChild(h3Tag02);
                      result.appendChild(preTag);
        
                      





                  },
                  error: function(xhr, status, error) {
                      alert("공지사항 조회에 실패했습니다.");
                  }
              });
          }
      });
  });
  </script>

















