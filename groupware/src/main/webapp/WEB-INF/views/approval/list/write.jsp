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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/list/response.css">
<script defer src="${pageContext.request.contextPath}/js/approval/common/common.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/home.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/list/response.js"></script>
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
                          <tr onclick="appDetail('${approval.approvalNo}', '${approval.approver}', '${approval.title}', '${approval.content}', '${approval.createDate}','${loginEmployeeVo.empId}')">
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
                <!-- 모달 -->
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <p>결재 번호: <span id="approvalNo"></span></p><br />
                        <p>결재자: <span id="approver"></span></p><br />
                        <p>결재제목: <span id="approvalTitle"></span></p><br />
                        <p>결재내용: <span id="approvalContent"></span></p><br />
                        <p>결재일: <span id="approvalDate"></span></p><br />
                        <button id="detailButton">상세보기</button>
                    </div>
                </div>
                <!-- 상세보기 모달 -->
                <div id="tempModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <table id="productList" class="userList">
                            <tr>
                                <th>결재번호</th>
                                <td colspan="2"><span id="detailApprovalNo"></span></td>
                            </tr>
                            <tr>
                                <th>결재자</th>
                                <td colspan="2"><span id="empId"></span></td>
                            </tr>
                            <tr>
                                <th colspan="3">결재선</th>
                            </tr>
                            <tr>
                                <th><span id="approver1"></span></th>
                                <th><span id="approver2"></span></th>
                                <th><span id="approver3"></span></th>
                            </tr>
                            <tr>
                                <th width="100px" height="100px">
                                    <img id="approver1Img" src="" style="width: 100%; height: 100%; object-fit: cover;"/>
                                </th>
                                <th width="100px" height="100px">
                                    <img id="approver2Img" src="" style="width: 100%; height: 100%; object-fit: cover;"/>
                                </th>
                                <th width="100px" height="100px">
                                    <img id="approver3Img" src="" style="width: 100%; height: 100%; object-fit: cover;"/>
                                </th>
                            </tr>
                            <tr>
                                <td><span id="approvalLine1"></span></td>
                                <td><span id="approvalLine2"></span></td>
                                <td><span id="approvalLine3"></span></td>
                            </tr>
                            <tr>
                                <th>종류</th>
                                <td colspan="2"><span id="reason"></span></td>
                            </tr>
                            <tr>
                                <th>양식</th>
                                <td colspan="2"><span id="leaveForm"></span></td>
                            </tr>
                            <tr>
                                <th>우선순위</th>
                                <td colspan="2"><span id="priority"></span></td>
                            </tr>
                            <tr>
                                <th>시작일</th>
                                <td colspan="2"><span id="startDate"></span></td>
                            </tr>
                            <tr>
                                <th>종료일</th>
                                <td colspan="2"><span id="endDate"></span></td>
                            </tr>
                            <c:if test="${appType == '휴가'}">
                                <tr>
                                    <th>사용수</th>
                                    <td colspan="2"><span id="usageCount"></span></td>
                                </tr>
                            </c:if>
                            <tr>
                                <th>내용</th>
                                <td colspan="2"><span id="tempContent"></span></td>
                            </tr>
                            <tr>
                                <th>결재일</th>
                                <td colspan="2"><span id="approvalDateDetail"></span></td>
                            </tr>
                        </table>



                        <button id="approvalUpdate">수정하기</button>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>


</body>
</html>