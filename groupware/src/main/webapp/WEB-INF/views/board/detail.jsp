<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회</title>
<style>
    *,
    *::before,
    *::after {
        box-sizing: content-box !important;
    }
    #commentContent {
            box-sizing: border-box !important; /* 패딩과 보더 포함한 너비를 설정 */
            max-width: 100% !important; /* 부모 영역을 넘지 않도록 설정 */
        }

</style>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script defer src="${pageContext.request.contextPath}/js/board/detail.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <!-- 부트스트랩 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
    integrity="sha384-E4W1a11fF4Rp8FexMlti9R4yIhWm2KwPkvbEriKo6Pv1Pw2Q+m5scsmvMQ/t0xIz"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
    integrity="sha384-+0wb6s6gGP2rI0oBDxYOVJvqbT2yl0Y3BQkmQx8KkO8Vh1jNnT/4kew2e2tzS9zE"
    crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="/css/board/detail.css">
    

</head>

<body>
    <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>
    <div id="main" onclick="closeNav()">
        <div class="column">
            <div class="container" id="first-container">
                <h2>자유게시판 이용규칙</h2>
                <h3>1. 목적</h3>
                <p>사내 자유게시판은 직원 간의 소통과 정보 공유를 목적으로 합니다.</p>
                
                <h3>2. 게시글 작성 시 유의사항</h3>
                <ul>
                    <li>존중과 예의를 지킵니다.</li>
                    <li>적절한 언어를 사용합니다.</li>
                    <li>허위 정보를 유포하지 않습니다.</li>
                    <li>광고 및 홍보를 금지합니다.</li>
                </ul>
                
                <h3>3. 게시글 주제</h3>
                <ul>
                    <li>업무 관련 정보</li>
                    <li>복지 및 이벤트</li>
                    <li>자유로운 의견 교환</li>
                </ul>
                
                <h3>4. 금지사항</h3>
                <ul>
                    <li>비방 및 욕설</li>
                    <li>정치적/종교적 논의</li>
                    <li>개인 정보 노출</li>
                    <li>저작권 침해</li>
                </ul>
            </div>
        </div>
        <div class="column content">

            <div class="container">
                
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
                <div class="container mt-4 comment-form">
                    <form id="commentForm" class="comment-form">
                        <div class="form-group">
                            <label for="commentContent">댓글 작성</label>
                            <textarea class="form-control" id="commentContent" name="commentContent" rows="3" required></textarea>
                        </div>
                        <div class="reply-submit-wrapper">
                            <button type="submit" class="btn btn-primary" onclick="writeReply(`${vo.no}`);" >댓글 등록</button>
                        </div>
                    </form>
                </div>
                <div class="container mt-4">
                    <div>
                        <h4>댓글 (${vo.replyCount})</h4>
                        <span> <button class="btn-secondary" onclick="loadReplyList(`${vo.no}`);">댓글 목록 열기</button></span>
                    </div>
                    <div class="comment-list">
  
                    </div><!--댓글컨테이너끝나는부분-->
                </div>
            </div>
        </div>
    </div>

        <!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<!-- Custom JS (필요한 경우 추가) -->
<script>
   

    //댓글목록불러오기
    function loadReplyList(refNo){
        var loginEmployeeId = "${sessionScope.loginEmployeeVo.empId}";
        console.log(refNo);
        $.ajax({
            url:'/board/replyList',
            method:'GET',
            data:{refNo: refNo},
            success:function(replyVoList){
                //map 으로 리턴했을때
                // const empId = response.empId;
                // const replyVoList = response.replyVoList;
                let str = "";
                for(let i = 0; i< replyVoList.length; ++i){
                    var reply = replyVoList[i];

                    str+='<div class="comment-item"><div class="comment-head">';
                    str+= '<p><strong>' + reply.writerName + '</strong></p>';
                    if (reply.writerNo == loginEmployeeId) {
                        str += '<button class="btn btn-secondary" onclick="deleteReply(' + reply.no + ','+reply.refNo+');">삭제</button>';
                    }
                    str += '</div>';
                    str += '<p>' + reply.content + '</p>';
                    str +='<p>'+reply.writeDate+'</p>';
                    str +='</div><hr>'
                }
                $('.comment-list').html(str);
            }

        });
    }

$(document).ready(function () {

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

//댓글 삭제
function deleteReply(no,refNo){

    if(confirm('댓글을 삭제하시겠습니까?')){

        console.log('삭제할댓글번호'+no);
        
        $.ajax({
            url:'/board/reply/delete',
            method:'PUT',
            data:{
                no:no
            },
            success:function(response){
                alert(response);
                loadReplyList(refNo);

            },
            error:function(e){
                alert(e.responseText);
            }

        })
    }

}


//댓글 작성
function writeReply(refNo){
    var loginEmployeeId = "${sessionScope.loginEmployeeVo.empId}";
    const replyValue = document.querySelector("textarea[name=commentContent]").value;
    document.querySelector("textarea[name=commentContent]").value="";
    $.ajax({
        url:'/board/reply/write',
        method:'POST',
        data:{
            writerNo:loginEmployeeId,
            refNo:refNo,
            content:replyValue
        },
        success:function(response){
            alert(response);
            loadReplyList(refNo);

        },
        error : function(xhr, status, error){
            alert(xhr.responseText);//예외메세지
        }



    })
}




</script>

</body>

</html>

