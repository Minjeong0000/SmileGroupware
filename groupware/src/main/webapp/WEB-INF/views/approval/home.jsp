<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>슬라이드 네비게이터 바</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/common/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/home.css">
<script defer src="${pageContext.request.contextPath}/js/approval/common/common.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/home.js"></script>
</head>
<body>
    <div id="mySidenav" class="sidenav">
      <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
      <a href="home.html"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
      <a href="#"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
      <a href="#"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
      <a href="/approval/home"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
      <a href="#"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
      <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
    </div>
    <div id="main" onclick="closeNav()">

          <div class="column" id="side_menu">
              <h1 id="big">결재관리</h1>
              <div class="approval_status">
                <a href="#"><span class="circle"><span>결재중</span><span id="pendingCount">${approvalHomeVo.cntAppVo.cntApprovalIng}</span></span></a>
                <a href="#"><span class="circle"><span>임시저장</span><span id="processingCount">${approvalHomeVo.cntAppVo.cntApprovalSave}</span></span></a>
                <a href="#"><span class="circle"><span>전체결재</span><span id="totalCount">${approvalHomeVo.cntAppVo.cntApprovalAll}</span></span></a>

              </div>
              <div class="approval_nav">
                  <ul>
                      <li>
                        <a href="#" class="collapsed">결재작성    &nbsp;<span class="indicator">&#9654;</span></a>
                        <ul>
                          <li><a href="/approval/vac">휴가 결재</a></li>
                          <li><a href="/approval/doc">프로젝트 결재</a></li>
                          <li><a href="#">기타 작성</a></li>
                        </ul>
                      </li>
                      <li>
                        <a href="#" class="collapsed">결재목록   &nbsp;<span class="indicator">&#9654;</span></a>
                        <ul>
                          <li><a href="#">결재중</a></li>
                          <li><a href="#">결재처리</a></li>
                          <li><a href="#">임시저장</a></li>
                        </ul>
                      </li>
                      <li>
                        <a href="#" class="collapsed">전체결재    &nbsp;<span class="indicator">&#9654;</span></a>
                        <ul>
                          <li><a href="#">전체</a></li>
                          <li><a href="#">부서</a></li>
                          <li><a href="#">개인</a></li>
                        </ul>
                      </li>
                    </ul>
              </div>
          </div>

        <div class="column content">
          <main>
            <div id="addr">결재 홈</div>
            <br>
            <div id="first">
              <div id="f-title">
                <span><span id="b-g">&#10073;</span> 결재 현황</span>
               </div>
              <div id="f-content">
                <span id="f-c-right" class="f-c-item">
                  결재현황
                  <div>
                    <table id="userCnt">
                    <tr>
                      <th>휴가</th>
                      <th>프로젝트</th>
                      <th>긴급</th>
                      <th>보통</th>
                      <th>낮음</th>
                    </tr>
                    <tr>
                      <td>${approvalHomeVo.temCntVo.vacationCnt}</td>
                      <td>${approvalHomeVo.temCntVo.projectCnt}</td>
                      <td>${approvalHomeVo.temCntVo.emergencyCnt}</td>
                      <td>${approvalHomeVo.temCntVo.normalCnt}</td>
                      <td>${approvalHomeVo.temCntVo.lowCnt}</td>
                    </tr>
                  </table>
                  </div>
                </span>
                <span id="f-c-left" class="f-c-item">
                  결재현황
                  <div>
                    <table id="userCnt">
                      <tr>
                        <th>승인</th>
                        <th>반려</th>
                        <th>진행중</th>
                        <th>작성중</th>
                        <th>총결재 수</th>
                      </tr>
                      <tr>
                        <td>${approvalHomeVo.appCntVo.approvalOkCnt}</td>
                        <td>${approvalHomeVo.appCntVo.approvalNoCnt}</td>
                        <td>${approvalHomeVo.appCntVo.approvalAIngCnt}</td>
                        <td>${approvalHomeVo.appCntVo.approvalWIngCnt}</td>
                        <td>${approvalHomeVo.appCntVo.approvalCount}</td>
                      </tr>
                    </table>
                  </div>
                </span>
              </div>
            </div>

            <div id="second">
              <div id="s-title">
                <span><span id="b-g">&#10073;</span> 결재목록</span>
                <a href="#">결재목록 바로가기</a>
              </div>
              <div>
                <table id="productList" class="userList">
                <tr>
                  <th>결재번호</th>
                  <th>우선순위</th>
                  <th>종류</th>
                  <th>제목</th>
                  <th>생성일</th>
                  <th>결재자</th>
                  <th>결재선</th>
                  <th>상태</th>
                </tr>
                <c:forEach items="${approvalHomeVo.approvalListVoList}" var="approval">
                    <tr>
                        <td>${approval.approvalNo}</td>
                        <td>${approval.priority}</td>
                        <td>${approval.category}</td>
                        <td>${approval.title}</td>
                        <td>${approval.createDate}</td>
                        <td>${approval.approver}</td>
                        <td>${approval.approvalLine}</td>
                        <td>${approval.status}</td>
                    </tr>
                </c:forEach>
              </table>
              </div>
            </div>

            <div id="third">
              <div id="t-title">
                <span><span id="b-g">&#10073;</span> 결재처리</span>
                <a href="#">결재처리 바로가기</a>
              </div>
              <div>
                <table id="userDeclaration" class="userList">
                <tr>
                  <th>결재번호</th>
                  <th>결재자</th>
                  <th>결재순서</th>
                  <th>결재선</th>
                  <th>처리상태</th>
                  <th>답변내용</th>
                  <th>답변시간</th>
                </tr>
                <c:forEach items="${approvalHomeVo.approvalProcessVoList}" var="approval">
                    <tr>
                        <td>${approval.approvalNo}</td>
                        <td>${approval.approver}</td>
                        <td>${approval.approvalOrder}</td>
                        <td>${approval.approvalLine}</td>
                        <td>${approval.processingStatus}</td>
                        <td>${approval.responseText}</td>
                        <td>${approval.respondedDate}</td>
                    </tr>
                </c:forEach>
              </table>
              </div>
            </div>
          </main>
        </div>
    </div>
</div>
</body>
</html>