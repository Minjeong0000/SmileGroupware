<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬로월드</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>
	<h1>게시글 상세조회</h1>
    <h3></h3>
    <div id="summernote" readonly>


    </div>

    <div id="test" readonly>


    </div>



</body>
</html>
<script>
const h3Tag = document.querySelector('h3');
$.ajax({
    url:"/board",
    method:'get',
    data:{
        no: 24
    },

    success: function(vo){
       h3Tag.innerText = '제목 : '+ vo.title;
        $('#test').html(vo.content);

    }

})
</script>
