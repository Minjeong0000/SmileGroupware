<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="/css/board/boardList.css">
    <script defer src="/js/board/boardList.js"></script>

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
            <div class="board">
                <div class="board-header">
                    <div class="board-column number">번호</div>
                    <div class="board-column title">제목</div>
                    <div class="board-column name">이름</div>
                    <div class="board-column date">날짜</div>
                    <div class="board-column views">조회수</div>
                    <div class="board-column likes">추천수</div>

                </div>
                <div class="board-row-wrapper">
                    <div class="board-row">
                        <div class="board-column number">6</div>
                        <div class="board-column title">일반 게시판 입니다.</div>
                        <div class="board-column name">하사원</div>
                        <div class="board-column date">2013-05-08</div>
                        <div class="board-column views">24</div>
                        <div class="board-column likes">2</div>
    
                    </div>
                    <div class="board-row">
                        <div class="board-column number">5</div>
                        <div class="board-column title">일반 게시판 입니다.</div>
                        <div class="board-column name">호사원</div>
                        <div class="board-column date">2012-05-20</div>
                        <div class="board-column views">4</div>
                        <div class="board-column likes">2</div>
    
                    </div>
                    <div class="board-row">
                        <div class="board-column number">4</div>
                        <div class="board-column title">일반 게시판 입니다.</div>
                        <div class="board-column name">호사원</div>
                        <div class="board-column date">2012-05-20</div>
                        <div class="board-column views">4</div>
                        <div class="board-column likes">0</div>
    
                    </div>
                    <div class="board-row">
                        <div class="board-column number">3</div>
                        <div class="board-column title">일반 게시판 입니다.</div>
                        <div class="board-column name">호사원</div>
                        <div class="board-column date">2012-05-20</div>
                        <div class="board-column views">4</div>
                        <div class="board-column likes">8</div>
    
                    </div>
                    <div class="board-row">
                        <div class="board-column number">2</div>
                        <div class="board-column title">일반 게시판 입니다.</div>
                        <div class="board-column name">호사원</div>
                        <div class="board-column date">2012-05-20</div>
                        <div class="board-column views">4</div>
                        <div class="board-column likes">7</div>
    
                    </div>

                </div>
              

                <!-- 추가 행들 -->

                <c:if test="${not empty pvo}">
                    <div class="pagination">
                        <c:if test="${pvo.currentPage > 1}">
                            <a href="?pno=1">&laquo;</a>
                            <a href="?pno=${pvo.currentPage - 1}">&lt;</a>
                        </c:if>
                        <c:forEach var="i" begin="${pvo.startPage}" end="${pvo.endPage}">
                            <c:choose>
                                <c:when test="${i == pvo.currentPage}">
                                    <strong>${i}</strong>
                                </c:when>
                                <c:otherwise>
                                    <a href="?pno=${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pvo.currentPage < pvo.maxPage}">
                            <a href="?pno=${pvo.currentPage + 1}">Next</a>
                            <a href="?pno=${pvo.maxPage}">Last</a>
                        </c:if>
                    </div>
                 </c:if>
        
                <div class="search">
                    <input type="text" placeholder="검색어">
                    <button>검색</button>
                </div>
                <div class="write">
                    <button onclick="location.href='/board/write'">글쓰기</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script>
$(document).ready(function() {

});



</script>