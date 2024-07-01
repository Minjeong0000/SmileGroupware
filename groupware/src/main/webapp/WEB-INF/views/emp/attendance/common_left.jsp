<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <h2>근태관리</h2>
    <div>
        <h3 id="current-date"></h3>
    </div>
    <h2 id="currentTime"></h2>

    <table>
        <tr>
            <th>업무상태</th>
            <td id="status">출근전</td>
        </tr>
        <tr>
            <th>출근시간</th>
            <td id="startTime">미등록</td>
        </tr>
        <tr>
            <th>퇴근시간</th>
            <td id="endTime">미등록</td>
        </tr>
        <tr>
            <th>연장근무시간</th>
            <td id="overtime">미등록</td>
        </tr>
        <tr>
            <th>일근무시간</th>
            <td id="dayWorkTime">미등록</td>
        </tr>
    </table>

    <br>

    <div class="btn_wrapper">
    <button id="checkInBtn">출근</button>
    <button id="checkOutBtn">퇴근</button>
    </div>
    <div>
      <span>${sessionScope.loginEmployeeVo.empName}</span>|
      <span>${sessionScope.loginEmployeeVo.departmentName}</span>|
      <span>${sessionScope.loginEmployeeVo.roleName}</span>
      <span style="display: none;" id="empId">${sessionScope.loginEmployeeVo.empId}</span>
    </div>
    <div class="menu">
      <div class="menu-item">근태관리</div>
      <div class="submenu">
          <div class="submenu-item">내 근태 현황</div>
          <div class="submenu-item">내 연차 내역</div>
          <div class="submenu-item">내 인사정보</div>
      </div>

  </div>