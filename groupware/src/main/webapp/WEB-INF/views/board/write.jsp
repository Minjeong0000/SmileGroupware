<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>without bootstrap</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <script defer src="/js/board/boardWrite.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/board/boardWrite.css">


  </head>
  <body>

    <form action="/board/write" method="post">
        <input type="text" name="title">
        <br />
        <textarea id="summernote" name="content"></textarea>
        <br />
        <input type="submit" value="작성하기">
    </form>

  </body>
</html>

<script>
    $('#summernote').summernote({
    placeholder: 'Hello stand alone ui',
    tabsize: 2,
    height: 300,
    minHeight: 200,
    maxHeight: 400,
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

    function handleImgUpload( fileList ){
        const fd = new FormData();
        for (let i = 0; i < fileList.length; i++) {
            fd.append('fileList[]', fileList[i]);
        }
        $.ajax( {
            url: "/board/upload" ,
            method: "POST" ,
            data: fd ,
            processData: false,
            contentType: false,
            success: function(resp){
                console.log("handleImgUpload 성공 ~~~ !");
                console.log(resp);
                $('#summernote').summernote('insertImage', resp);
            } ,
        } );
    }
</script>