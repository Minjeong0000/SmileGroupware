
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬로월드</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
	<h1>사내 공지사항</h1>
  <table>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>title</td>
        <td>writer01</td>
      </tr>

      <hr>

      <div id="result">
        
      </div>

    </tbody>
  </table>
</body>
</html>

<script>
  $.ajax({
    url :"http://127.0.0.1:8080/api/notice/list",
    method :"get",
    success : (data) => {
      console.log("통신성공");
      console.log(data);
      const x = document.querySelector("table > tbody");
      console.log(x);

      let str = "";
      for(let i = 0 ; i < data.length; ++i ){
      str += "<tr>";
      str += "<td>" + data[i].no + "</td>"; 
      str += "<td>" + data[i].title + "</td>"; 
      str += "<td>" + data[i].writerNo + "</td>"; 
      str += "</tr>";
      }

      x.innerHTML= str;

    } ,
    fail: () => {
      console.log("통신실패");

    }, 

  });
</script>


<script>
  
</script>