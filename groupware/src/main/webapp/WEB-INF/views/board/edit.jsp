<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
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
                        <div><h2>게시글 수정</h2></div>
                            <input type="hidden" id = "voNo" name="no" value="${vo.no}">
                            <p class="card-subtitle text-muted">제목</p>
                            <input type="text" id="title" name="title" placeholder="제목을 입력하세요.">
                            <br />
                            <p class="card-subtitle text-muted">내용</p>
                            <textarea id="summernote"></textarea>
                            <br />
                            <div class="submit-btn-wrap">
                                <button type ="button" class="btn btn-secondary" onclick="location.href='/board/list'">취소</button>
                                <button type="button" onclick="edit(`${vo.no}`);" class="btn btn-primary">게시글 수정</button>
                            </div>
                    </div>
            </div>
        </div>
  </body>
</html>

<script>

function edit(no){
            console.log('수정버튼클릭됨');
            const title = $('#title').val();
            const content = $('#summernote').val();
            
            $.ajax({
                url:'/board/edit',
                method:'POST',
                data:{
                    no:no,
                    title:title,
                    content:content
                },
                success:function(response){
                    alert(response);
                    location.href="/board/list";
                },
                error : function(xhr, status, error){
                    alert(xhr.responseText);//예외메세지
                }

            


            })

        }




    $(document).ready(function(){
        var voNo = $('#voNo')
        console.log(voNo);
        


        $.ajax({
            url:'/board/edit/getPost',
            method:"GET",
            data:voNo,
            success:function(vo){
                console.log(vo);
                const title = document.querySelector("#title")
                title.value = vo.title;
                console.log(title.val);
                $('#summernote').summernote("pasteHTML" , vo.content);

            },
            error:function(e){
                alert(e.responseText);
                location.href='/board/list'
            }

        })






    })
</script>