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
<script defer src="${pageContext.request.contextPath}/js/approval/common/common.js"></script>
<script defer src="${pageContext.request.contextPath}/js/approval/home.js"></script>
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
                <div>
                  <form action="">
                    <table id="user-search" class="userList">
                      <tr>
                        <th>
                          결재재목
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="searchListText">
                        </td>
                      </tr>
                      <tr>
                        <th>
                          결재내용
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="searchListText">
                        </td>
                      </tr>
                      <tr>
                        <th>
                          보고서 양식
                        </th>
                        <td style="text-align: left">
                          <select name="s-len">
                                <option value="P.NAME">프로젝트</option>
                                <option value="P.NAME">계획보고서</option>
                                <option value="P.NAME">업무보고서</option>
                            </select>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          우선순위
                        </th>
                        <td style="text-align: left">
                          <select name="s-len">
                                <option value="P.NAME">긴급</option>
                                <option value="P.NAME">보통</option>
                                <option value="P.NAME">느림</option>
                            </select>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          결재선1
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="searchListText" value="이용진" readonly>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          결재선2
                        </th>
                        <td class="search-field" style="text-align: left">
                              <select name="s-status">
                                  <option value="1">이부장</option>
                                  <option value="2">김부장</option>
                                  <option value="3">박부장</option>
                              </select>
                          </td>
                      </tr>
                      <tr>
                        <th>
                          결재선3
                        </th>
                        <td class="search-field" style="text-align: left">
                              <select name="s-status">
                                  <option value="1">이이사</option>
                                  <option value="2">김이사</option>
                                  <option value="3">박이사</option>
                              </select>
                          </td>
                      </tr>
                      <tr>
                        <th>
                          시작/종료일
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="date" name="searchListText">
                          ~
                          <input type="date" name="searchListText">
                          &nbsp;&nbsp;
                          <input type="text" name="searchListText" readonly>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          내용
                        </th>
                        <td class="search-field" style="text-align: left">
                          <textarea name="searchListText"></textarea>
                        </td>
                      </tr>
                      <tr>
                        <th>
                          비고
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="text" name="searchListText">
                        </td>
                      </tr>




                    </table>
                    <div id="search-div">
                      <button>결재 올리기</button>
                      <button>임시저장</button>
                    </div>

                  </form>
                </div>







            </div>
        </div>
    </div>
</div>
</body>
</html>