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
    <div id="mySidenav" class="sidenav">
        <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
        <a href="#"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
        <a href="#"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
        <a href="#"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
        <a href="#"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
        <a href="#"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
        <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
    </div>

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
                        <p class="card-text">${vo.content}</p>
                        <div class="like-btn-wrap">
                            <button type="button" class="btn btn-primary like-btn">추천</button>
                        </div>
                        <c:if test="${vo.writerNo eq sessionScope.loginEmployeeVo.empId}">
                            <div class="del-edit-wrap">
                                <button class="btn btn-secondary" onclick="location.href='/board/edit?no=${vo.getBNo()}'">수정</button>
                                <button class="btn btn-secondary" onclick="location.href='/board/delete?no=${vo.getBNo()}'">삭제</button>
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
                    <h4>댓글 목록</h4>
                    <div class="comment-list">
                        <div class="comment-item">
                            <p><strong>작성자1</strong></p>
                            <p>이것은 첫 번째 댓글입니다.</p>
                            <p class="text-muted">2024-07-16</p>
                            <button class="btn btn-secondary" onclick="alert('댓글 삭제 기능')">삭제</button>
                        </div>
                        <hr>
                        <div class="comment-item">
                            <p><strong>작성자2</strong></p>
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
                    </div>
                </div>




            </div>
        </div>
    </div>

        <!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<!-- Custom JS (필요한 경우 추가) -->
<script>
   
$(document).ready(function () {
    // 추천 버튼 클릭 시 동작
    $('.like-btn').click(function () {
        // 여기에 추천 버튼 클릭 시 동작할 코드를 추가합니다.
        // 예: 추천 수를 증가시키는 AJAX 요청 등
        alert('추천되었습니다!');
    });







});


</script>

</body>

</html>

