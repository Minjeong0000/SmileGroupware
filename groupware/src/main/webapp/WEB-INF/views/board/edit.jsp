<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>without bootstrap</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <script defer src="/js/board/write.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/board/write.css">
    <!-- 부트스트랩 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
    integrity="sha384-E4W1a11fF4Rp8FexMlti9R4yIhWm2KwPkvbEriKo6Pv1Pw2Q+m5scsmvMQ/t0xIz"
    crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
    integrity="sha384-+0wb6s6gGP2rI0oBDxYOVJvqbT2yl0Y3BQkmQx8KkO8Vh1jNnT/4kew2e2tzS9zE"
    crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
    crossorigin="anonymous">


  </head>
    <body>
        <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>
        <div id="main" onclick="closeNav()">

    
    
            <div class="column">
                
                    <div class="write-area">
                        <div><h2>게시글 작성</h2></div>
                        <form action="/board/write" method="post">
                            <p class="card-subtitle text-muted">제목</p>
                            <input type="text" name="title" placeholder="제목을 입력하세요.">
                            <br />
                            <p class="card-subtitle text-muted">내용</p>
                            <textarea id="summernote" name="content"></textarea>
                            <br />
                            <div class="submit-btn-wrap">
                                <button type ="button" class="btn btn-secondary" onclick="location.href='/board/list'">취소</button>
                                <button type="submit" class="btn btn-primary">게시글 작성</button>
                            </div>

                        </form>


                    </div>
                    

                

            </div>
        </div>
  </body>
</html>

<script>

    $('#summernote').summernote({
    placeholder: '내용을 입력하세요.',
    tabsize: 2,
    width: '100%',
    height: 500,
    minHeight: 450,
    maxHeight: 600,
    toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'underline', 'clear']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'video']],
        ['view', ['fullscreen', 'codeview', 'help']]
    ],
    callbacks: {
        onImageUpload : handleImgUpload ,
    } ,
    });

    function handleImgUpload(fileList) {
    const fd = new FormData();

    fd.append('fileList' , fileList[0]);


    $.ajax({
        url: "/board/upload",
        method: "POST",
        data: fd,
        processData: false,
        contentType: false,
        success: function(resp) {
            console.log("handleImgUpload 성공 ~~~ !");
            console.log(resp);
            $('#summernote').summernote('insertImage', resp);
        },
        error: function(err) {
            console.error("handleImgUpload 실패 ㅠㅠ");
            console.error(err);
        }
    });
}
</script>