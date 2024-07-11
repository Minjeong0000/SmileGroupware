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
            <main>
                <div>
                  <form action="/approval/vac" method="post">
                    <table id="user-search" class="userList">
                      <tr>
                        <th>
                          결재재목
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="appTitle">
                        </td>
                      </tr>
                      <tr>
                        <th>
                          결재내용
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="appContent">
                        </td>
                      </tr>
                      <tr>
                        <th>
                          휴가 양식
                        </th>
                        <td style="text-align: left">
                          <select name="vacNo">
                                <c:forEach items="${writeVo.vacCateVo}" var="vacCate">
                                    <option value="${vacCate.vacCateNo}">${vacCate.vacCateName}</option>
                                </c:forEach>
                            </select>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          우선순위
                        </th>
                        <td style="text-align: left">
                          <select name="prioritieNo">
                                <c:forEach items="${writeVo.prioritieVo}" var="prioritieVo">
                                    <option value="${prioritieVo.priorityNo}">${prioritieVo.priorityName}</option>
                                </c:forEach>
                            </select>
                        </td>
                      </tr>
                      <tr>
                          <th>
                            결재선1
                          </th>
                          <td class="search-field" style="text-align: left">
                                <select name="appLine1">
                                    <c:forEach items="${writeVo.employeeVo1}" var="employeeVo1">
                                        <option value="${employeeVo1.empId}">${employeeVo1.empName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                      <tr>
                        <th>
                          결재선2
                        </th>
                        <td class="search-field" style="text-align: left">
                              <select name="appLine2">
                                  <c:forEach items="${writeVo.employeeVo2}" var="employeeVo2">
                                      <option value="${employeeVo2.empId}">${employeeVo2.empName}</option>
                                  </c:forEach>
                              </select>
                          </td>
                      </tr>
                      <tr>
                        <th>
                          결재선3
                        </th>
                        <td class="search-field" style="text-align: left">
                              <select name="appLine3">
                                    <c:forEach items="${writeVo.employeeVo3}" var="employeeVo3">
                                        <option value="${employeeVo3.empId}">${employeeVo3.empName}</option>
                                    </c:forEach>
                                </select>
                          </td>
                      </tr>
                      <tr>
                        <th>
                          휴가일
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="date" name="start">
                          ~
                          <input type="date" name="end">
                          &nbsp;&nbsp;
                          <input type="text" name="use">
                        </td>
                      </tr>
                      <tr>
                        <th>
                          휴가사유
                        </th>
                        <td class="search-field" style="text-align: left">
                          <textarea name="vacContent"></textarea>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          비고
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="note">
                        </td>
                      </tr>
                      <tr>
                          <th>
                            범위
                          </th>
                          <td class="search-field" style="text-align: left">
                                <select name="range">
                                    <option value="전체">전체</option>
                                    <option value="부서">부서</option>
                                    <option value="개인">개인</option>
                                </select>
                            </td>
                        </tr>




                    </table>
                    <div id="search-div">
                        <button type="submit" name="action" value="submit">결재 올리기</button>
                        <button type="submit" name="action" value="save">임시저장</button>
                    </div>

                  </form>
                </div>







            </main>
        </div>
    </div>
</div>
</body>
</html>