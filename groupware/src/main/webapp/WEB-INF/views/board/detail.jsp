<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script defer src="${pageContext.request.contextPath}/js/board/detail.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/board/detail.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <!-- 부트스트랩 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
    integrity="sha384-E4W1a11fF4Rp8FexMlti9R4yIhWm2KwPkvbEriKo6Pv1Pw2Q+m5scsmvMQ/t0xIz"
    crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
    integrity="sha384-+0wb6s6gGP2rI0oBDxYOVJvqbT2yl0Y3BQkmQx8KkO8Vh1jNnT/4kew2e2tzS9zE"
    crossorigin="anonymous"></script>



</head>

<body>
    <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>
    <div id="main" onclick="closeNav()">
        <div class="column">
            <div><h1><i class="fa-regular fa-message"></i> 게시판</h1></div>
            <div class="msg-menu">
                <div>
                    <i class="fa-solid fa-envelope">받은 쪽지함</i>
                    <ul>
                        <li><i class="fa-solid fa-star"></i> 중요 쪽지함</li>
                        <li> <i class="fa-solid fa-trash-can"></i> 휴지통</li>
                    </ul>
                </div>
                <div><i class="fa-regular fa-envelope-open"></i> 읽은 쪽지함</div>
                <div><i class="fa-regular fa-paper-plane"></i> 보낸 쪽지함</div>
            </div>
        </div>
        <div class="column content">

            <div class="container mt-4">
                <h1>게시글 상세조회</h1>
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 class="card-title" id="title">${vo.title}</h3>
                        <p class="card-subtitle text-muted">작성자: ${vo.writerName}</p>
                        <div class="date-hit-wrap">
                            <span class="card-subtitle text-muted">
                             작성일: ${vo.writeDate}
                            </span>
                            <span class="card-subtitle text-muted">
                                조회수: ${vo.hit}
                            </span>
                        </div>
                        <!-- <p class="card-subtitle text-muted">좋아요 수: ${vo.likeCount}</p> -->
                         <div class="content-wrap">

                             <p class="card-text">${vo.content}</p>

                         </div>
                        <div class="like-btn-wrap">
                            <button type="button" class="btn btn-primary like-btn" data-no="${vo.no}">
                                <i class="fa-solid fa-thumbs-up"></i> 추천하기 (${vo.likeCount})
                            </button>
                        </div>
                        <c:if test="${vo.writerNo eq sessionScope.loginEmployeeVo.empId}">
                            <div class="del-edit-wrap">
                                <button class="btn btn-secondary" onclick="location.href='/board/edit?no=${vo.getNo()}'">수정</button>
                                <button class="btn btn-secondary btn-delete" data-no="${vo.no}">삭제</button>
                            </div>
                        </c:if>
                        
                    </div>
                </div>
                </br>
                </hr>
                <div class="container mt-4">
                    <form id="commentForm" class="comment-form">
                        <div class="form-group">
                            <label for="commentContent">댓글 작성</label>
                            <textarea class="form-control" id="commentContent" name="commentContent" rows="3" required></textarea>
                        </div>
                        <div class="reply-submit-wrapper">
                            <button type="submit" class="btn btn-primary">댓글 등록</button>
                        </div>
                    </form>
                </div>
                <div class="container mt-4">
                    <div>
                        <h4>댓글 목록 (${vo.replyCount})</h4>
                        <span> <button class="btn-secondary" onclick="loadReplyList(`${vo.no}`);">댓글 목록 열기</button></span>
                    </div>
                    <div class="comment-list">
                        <div class="comment-item">
                            <p><strong>작성자1</strong></p>
                            <p>이것은 첫 번째 댓글입니다.</p>
                            <p class="text-muted">2024-07-16</p>
                            <button class="btn btn-secondary" onclick="alert('댓글 삭제 기능')">삭제</button>
                        </div>
                        <hr>
                        <div class="comment-item">
                            <div class="comment-head">
                                <p><strong>작성자2</strong></p>
                                <c:if test="${vo.writerNo eq sessionScope.loginEmployeeVo.empId}">
                                    <button class="btn btn-secondary" onclick="alert('댓글 삭제 기능')">삭제</button>
                                </c:if>
                            </div>
                            <p>이것은 두 번째 댓글입니다.</p>
                            <p class="text-muted">2024-07-16</p>
                            <!-- 로그인한 사용자가 아닌 경우 삭제 버튼 없음 -->
                        </div>
                        <hr>
                        <div class="comment-item">
                            <p><strong>작성자3</strong></p>
                            <p>이것은 세 번째 댓글입니다.</p>
                            <p class="text-muted">2024-07-16</p>
                            <button class="btn btn-secondary" onclick="alert('댓글 삭제 기능')">삭제</button>
                        </div>
                        <hr>
                    </div><!--댓글컨테이너끝나는부분-->
                </div>
            </div>
        </div>
    </div>

        <!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<!-- Custom JS (필요한 경우 추가) -->
<script>
   
$(document).ready(function () {

    //댓글목록불러오기
    function loadReplyList(refNo){
        $.ajax({
            url:'/board/replyList',
            method:'GET',
            data:{refNo: refNo},
            success:function(replyVoList){
                let str = "";
                for(let i = 0; i< replyVoList.length; ++i){


                }
            }

        })
    }




    // 추천 버튼 클릭 시 동작
    $('.like-btn').click(function () {
        var no = $(this).data('no');

        $.ajax({
            url:'/board/like',
            method:'POST',
            data:{
                no:no,
            },
            success:function(response){
                if(response==='liked'){
                    alert('추천되었습니다!');
                }else{
                    alert('추천이 취소되었습니다.');
                }
                location.reload();//새로고침으로 추천수업데이트
            },
            error:function(e){
                alert(e.responseText);
            }
        })
    });

    $('.btn-delete').click(function(){
        if(confirm('게시글을 삭제하시겠습니까?')){
            var no = $(this).data('no');
        $.ajax({
            url:'/board/delete',
            method:'PUT',
            data:{
                no:no
            },
            success:function(response){
                alert(response);
                location.href="/board/list";
            },
            error:function(e){
                alert(e.responseText);
            }

        })
        
        }

    })





});


</script>

</body>

</html>

