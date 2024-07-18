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
    <script src="${pageContext.request.contextPath}/js/board/boardList.js"></script>

</head>
<body>
    <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>
    <div id="main" onclick="closeNav()">
        <div class="column">
            <div class="container">
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

            <div><h1>자유게시판</h1></div>

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
