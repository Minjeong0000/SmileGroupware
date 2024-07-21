<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>결재</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/approval/home.css">
<link rel="stylesheet" type="text/css" href="/css/approval/common/common.css">
<script defer src="${pageContext.request.contextPath}/js/approval/common/common.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/home.js"></script>
</head>
<body>
    <%@ include file="../nav/sideNav.jsp" %>
    <div id="main" onclick="closeNav()">

          <div class="column" id="side_menu">
              <h1 id="big">결재관리</h1>
              <div class="approval_status">
                <a href="/approval/ing"><span class="circle"><span>결재중</span><span id="pendingCount">${approvalHomeVo.cntAppVo.cntApprovalIng}</span></span></a>
                <a href="/approval/response"><span class="circle"><span>결재처리</span><span id="processingCount">${approvalHomeVo.cntAppVo.cntApprovalSave}</span></span></a>
                <a href="/approval/write"><span class="circle"><span>임시저장</span><span id="totalCount">${approvalHomeVo.cntAppVo.cntApprovalAll}</span></span></a>

              </div>
              <div class="approval_nav">
                  <ul>
                      <li>
                        <a href="#" class="collapsed">결재작성    &nbsp;<span class="indicator">&#9654;</span></a>
                        <ul>
                          <li><a href="/approval/vac">휴가 결재</a></li>
                          <li><a href="/approval/doc">업무 결재</a></li>
                        </ul>
                      </li>
                      <li>
                        <a href="#" class="collapsed">결재목록   &nbsp;<span class="indicator">&#9654;</span></a>
                        <ul>
                          <li><a href="/approval/ing">결재중</a></li>
                          <li><a href="/approval/response">결재처리</a></li>
                          <li><a href="/approval/write">임시저장</a></li>
                          <li><a href="/approval/my">결재목록</a></li>
                        </ul>
                      </li>
                      <li>
                        <a href="#" class="collapsed">전체결재    &nbsp;<span class="indicator">&#9654;</span></a>
                        <ul>
                          <li><a href="/approval/all">전체</a></li>
                          <li><a href="#">부서</a></li>
                        </ul>
                      </li>
                    </ul>
              </div>
          </div>

        <div class="column content">
          <div id="me">
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
                <a href="/approval/my">결재목록 바로가기</a>
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
                <c:forEach items="${approvalHomeVo.approvalListVoList}" begin="0" end="2" step="1" var="approval">
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
                <a href="/approval/response">결재처리 바로가기</a>
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
                <c:forEach items="${approvalHomeVo.approvalProcessVoList}" begin="0" end="2" step="1" var="approval">
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
          </div>
        </div>
    </div>
</div>
</body>
</html>