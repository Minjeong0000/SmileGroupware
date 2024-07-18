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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/approval/list/response.css">
<script defer src="${pageContext.request.contextPath}/js/approval/common/common.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/home.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/list/response.js"></script>
</head>
<body>
    <div id="mySidenav" class="sidenav">
          <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
            <a href="home.html"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
            <a href="/event/personal/calendar"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
            <a href="/emp/attendance/cal"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
            <a href="/approval/home"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
            <a href="/message/received"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
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
                                <li><a href="/approval/ing">결재중</a></li>
                                <li><a href="/approval/response">결재처리</a></li>
                                <li><a href="#">임시저장</a></li>
                                <li><a href="#">결재목록</a></li>
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
            <div id="me">
                <div class="seller-div">
                  <div>
                    <span><span id="b-g">&#10073;</span> 검색</span>
                  </div>
                  <div id="list-content">
                    <form action="/app/seller/home/product/list/select" method="post">
                      <table id="user-search" class="userList">
                        <tr>
                          <th>
                            종류
                          </th>
                          <td class="search-field" style="text-align: left">
                            <select name="s-cate">
                              <option value="all">전체</option>
                              <option value="1">휴가</option>
                              <option value="2">프로젝트</option>
                            </select>
                          </td>
                        </tr>
                        <tr>
                          <th>
                            우선순위
                          </th>
                          <td class="search-field" style="text-align: left">
                            <select name="s-cate">
                              <option value="all">전체</option>
                              <option value="1">긴급</option>
                              <option value="2">보통</option>
                              <option value="3">낮음</option>
                            </select>
                          </td>
                        </tr>
                        <tr>
                          <th>
                            <select name="s-cate">
                              <option value="title">제목</option>
                              <option value="content">내용</option>
                            </select>
                          </th>
                          <td class="search-field" style="text-align: left">
                            <input type="text" name="searchListText">
                          </td>
                        </tr>


                      </table>
                      <div id="search-div">
                        <input type="submit" value="검색">
                        <input type="reset" value="초기화">
                      </div>
                    </form>
                  </div>
                </div>
                <br />
                <hr />
                <br />
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
                        <p>상세보기 창입니다.</p><br />
                        <p>결재 번호: <span id="detailApprovalNo"></span></p><br />
                        <p>결재자 ID: <span id="empId"></span></p><br />
                        <p>결재선1: <span id="approver1"></span><img id="approver1Img" src=""  width="100px" height="100px"/></p><br />
                        <p>결재선2: <span id="approver2"></span><img id="approver2Img" src=""  width="100px" height="100px"/></p><br />
                        <p>결재선3: <span id="approver3"></span><img id="approver3Img" src=""  width="100px" height="100px"/></p><br />
                        <p>휴가양식: <span id="leaveForm"></span></p><br />
                        <p>우선순위: <span id="priority"></span></p><br />
                        <p>시작일: <span id="startDate"></span></p><br />
                        <p>종료일: <span id="endDate"></span></p><br />
                        <p>사용수: <span id="usageCount"></span></p><br />
                        <p>휴가사유: <span id="reason"></span></p><br />
                        <p>결재일: <span id="approvalDateDetail"></span></p><br />
                        <p>결재선1: <span id="approvalLine1"></span></p><br />
                        <p>결재선2: <span id="approvalLine2"></span></p><br />
                        <p>결재선3: <span id="approvalLine3"></span></p><br />

                        <button id="approveButton">승인하기</button>
                        <button id="rejectButton">반려하기</button>
                    </div>
                </div>
                <div id="canvasModal" class="modal">
                  <div class="modal-content-2">
                    <span class="close">&times;</span>
                    <canvas id="myCanvas" width="600" height="400" style="border: 1px solid black;"></canvas><br />
                    <input type="text" id="response" placeholder="답변을 입력하세요...">
                    <button id="saveDrawingBtn">저장하기</button>
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>