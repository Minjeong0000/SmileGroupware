    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

    <div class="column">
      <h2>근태관리</h2>
      <div id="current-date"></div>
      <h2 id="currentTime"></h2>

      <table>
        <tr>
          <th>
            업무상태
          </th>
          <td id="status">
            출근전
          </td>
        </tr>
        <tr>
          <th>
            출근시간
          </th>
          <td id="startTime">
            미등록
          </td>
        </tr>
        <tr>
          <th>
            퇴근시간
          </th>
          <td id = "endTime">
            미등록
          </td>
        </tr>
        <tr>
          <th>
            주간 누적 근무시간
          </th>
          <td>
            null
          </td>
        </tr>
      </table>





      <div class="btn_wrapper">
        <button id="checkInBtn">출근</button>
        <button id="checkOutBtn">퇴근</button>

      </div>
      <div>
        <span>홍길동</span>|
        <span>무슨부 </span>|
        <span>부장
      </div>
      <div class="menu">
        <div class="menu-item">근태관리</div>
        <div class="submenu">
            <div class="submenu-item">내 근태 현황</div>
            <div class="submenu-item">내 연차 내역</div>
            <div class="submenu-item">내 인사정보</div>
        </div>

    </div>
    </div>