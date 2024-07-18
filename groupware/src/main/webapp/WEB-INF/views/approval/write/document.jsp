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
                <span class="circle"><span>결재중</span><span id="pendingCount">1</span></span>
                <span class="circle"><span>결재처리</span><span id="processingCount">3</span></span>
                <span class="circle"><span>전체결재</span><span id="totalCount">10</span></span>
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
                      <tr>
                        <th>
                          첨부파일
                        </th>
                        <td class="search-field" style="text-align: left">
                          <input type="file" name="searchListText">
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