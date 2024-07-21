<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>결재</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/common/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/home.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/list/ing.css">
<script defer src="${pageContext.request.contextPath}/js/approval/common/common.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/home.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/list/ing.js"></script>
</head>
<body>
    <%@ include file="../../nav/sideNav.jsp" %>
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

                <div id="second">
                  <div id="s-title">
                    <span><span id="b-g">&#10073;</span> 결재중</span>
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
                      <c:forEach items="${listApprovalVo.approvalListVoList}" var="approval">
                          <tr onclick="openModal('${approval.approvalNo}')">
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
                <div id="myModal" class="modal">
                  <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="second">
                      <div id="s-title">
                        <span><span id="b-g">&#10073;</span> 진행</span>
                      </div>
                      <div>
                        <table id="productList" class="userList">
                          <tr>
                            <th>순서</th>
                            <th>이름</th>
                            <th colspan="2">상태</th>
                          </tr>
                          <tbody id="approvalLines">


                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                <div id="modal_2" class="modal_2">
                    <div class="modal-content_2">
                        <span class="close_2">&times;</span>
                        <h2>쪽지 보내기</h2>
                        <input type="text" id="empNo" hidden><br>
                        사원: <input type="text" id="empName" readonly><br>
                        메시지:<br>
                        <textarea id="message" name="message"></textarea><br>
                        <button id="sendMessageBtn">보내기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>