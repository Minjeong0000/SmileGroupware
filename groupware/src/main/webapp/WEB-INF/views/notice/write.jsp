
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
</style>

</head>
<body>
  
  <h1>사내공지사항</h1>

  <h2>사내 공지사항 게시판</h2>
  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        
      </tr>
    </thead>
    <tbody>
      
    </tbody>
  </table>

  <hr>

  <div id="result">
    
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