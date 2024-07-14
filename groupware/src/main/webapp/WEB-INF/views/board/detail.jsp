<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>
 <div class="container mt-4">
        <h1>게시글 상세조회</h1>
        <div class="card mt-4">
            <div class="card-body">
                <h3 class="card-title" id="title">${vo.title}</h3>
                <p class="card-subtitle text-muted">작성자: ${vo.writerName}</p>
                <p class="card-subtitle text-muted">작성일: ${vo.writeDate}</p>
                <p class="card-subtitle text-muted">조회수: ${vo.hit}</p>
                <p class="card-subtitle text-muted">좋아요 수: ${vo.likeCount}</p>
                <p class="card-text">${vo.content}</p>
                <button type="button" class="btn btn-primary">추천</button>

            </div>
        </div>
    </div>

    <!-- Bootstrap JS (필요한 경우 추가) -->

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <!-- Custom JS (필요한 경우 추가) -->
    <script>
       
    $(document).ready(function () {
        // 추천 버튼 클릭 시 동작
        $('.btn-primary').click(function () {
            // 여기에 추천 버튼 클릭 시 동작할 코드를 추가합니다.
            // 예: 추천 수를 증가시키는 AJAX 요청 등
            alert('추천되었습니다!');
        });
    });

    </script>

</body>
</html>

