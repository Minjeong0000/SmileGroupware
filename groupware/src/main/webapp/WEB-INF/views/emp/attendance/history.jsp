<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내 근태 기록 조회</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/attendance_cal.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/emp/attendance/history.css">

    <style>
    body{
        margin: 0;
        padding: 0;
    }
    </style>
   

</head>
<body>
    <div id="mySidenav" class="sidenav">
        <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
        <a href="#"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
        <a href="http://127.0.0.1:8080/event/personal/calendar"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
        <a href="#"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
        <a href="#"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
        <a href="http://127.0.0.1:8080/message/received"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
        <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
    </div>

    <div id="main" onclick="closeNav()">
        <div class="column">

            <h2>근태관리</h2>
            <div>
                <h3 id="current-date"></h3>
            </div>
            <h2 id="currentTime"></h2>

            <table id = "attTable">
                <tr>
                    <th>업무상태</th>
                    <td id="status">${attendanceVo.startTime}</td>
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
            <div class="empInfoContainer">
                <span class="emp-name-span">${sessionScope.loginEmployeeVo.empName}</span>
                <span class="emp-deptname-span">${sessionScope.loginEmployeeVo.departmentName}</span>
                <span class="emp-rolename-span">${sessionScope.loginEmployeeVo.roleName}</span>
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


        </div>


        <div class="column">


            <h1>기간 조회</h1>
            <div class="searchWrapper">
              <span>기간</span>
              <input type="date" name="startDate" id="startDate">
              <span>~</span>
              <input type="date" name="endDate" id="endDate">
              <button type="button" id="searchBtn">검색</button>
            </div>
            <hr>
            <table id="recordList" class="record-list">
              <thead>
                  <tr>
                      <th>일자</th>
                      <th>업무 시작 시간</th>
                      <th>업무 종료 시간</th>
                      <th>총 근무 시간</th>
                      <th>근무시간 상세</th>
                      <th>비고</th>
                  </tr>
              </thead>
              <tbody>
                    <!-- 여기에 AJAX로 데이터를 불러와서 채워질 예정 -->
                    <tr>
                        <td>2024-07-01</td>
                        <td>09:00</td>
                        <td>18:00</td>
                        <td>1:00</td>
                        <td>9:00</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td>2024-07-02</td>
                        <td>08:30</td>
                        <td>17:30</td>
                        <td>0:30</td>
                        <td>9:00</td>

                    </tr>
                    <tr>
                        <td>2024-07-03</td>
                        <td>09:15</td>
                        <td>18:15</td>
                        <td>0:45</td>
                        <td>9:00</td>
                        <td>지각</td>

                    </tr>
                    <tr>
                        <td>2024-07-04</td>
                        <td>08:45</td>
                        <td>17:45</td>
                        <td>1:00</td>
                        <td>9:00</td>
                    </tr>
                    <tr>
                        <td>2024-07-05</td>
                        <td>09:00</td>
                        <td>18:00</td>
                        <td>0:00</td>
                        <td>9:00</td>
                    </tr>
              </tbody>

          </table>
          <hr>

          <div class="pagination">
            <button id="prevPageBtn" disabled>이전</button>
            <button id="nextPageBtn">다음</button>
          </div>


        </div>
    </div>
</body>
</html>
<script>
    document.addEventListener('DOMContentLoaded', function() {


//출근버튼찍기
  document.getElementById('checkInBtn').addEventListener('click', function() {
      if(confirm('출근을 기록하시겠습니까?')){
          $.ajax({
              url: "/record/start",
              type: "POST",
              success: function(data) {
                  console.log(data);
                  if(data ==='false'){
                    alert('오늘은 이미 출근 기록이 있습니다.');
                    return;
                  }
                  if(data ==='error'){
                  alert('출근 기록 저장에 실패했습니다.');
                  return;
                 }
                else{
                console.log('출근버튼 else문');

                alert('출근 시간 저장에 성공했습니다.');
                // updateAttendanceStatus(); // 출근 기록 후 업데이트

                location.href="/emp/attendance/history";


                }

              },
              error: function(err) {
                  console.log(err);
              }
          });
      }

  });
  document.getElementById('checkOutBtn').addEventListener('click', function() {
      if (confirm('퇴근하시겠습니까?')) {
          $.ajax({
              url: "/record/end",
              type: "GET",
              success: function(data) {
                if(data==='success'){

                  alert('퇴근 기록에 성공했습니다.');
                  // updateAttendanceStatus(); // 퇴근 기록 후 업데이트
                  location.href="/emp/attendance/history";
                  return;
                }
                if(data =='false'){
                  alert('이미 퇴근 기록이 있습니다.')
                  return;
                }
                else{
                  alert('퇴근 기록 저장에 실패했습니다.')
                  return;
                }
              },
              error: function(err) {
                  console.log(err);
              }
          });
      }
  });


//근태 기록 업데이트 함수
function updateAttendanceStatus() {
  $.ajax({
      url: "/record/todayRecord",
      type: 'GET',
      success: function(data) {
          const startTimePart = data.startTime ? data.startTime.split(' ')[1] : '미등록';
          let endTimePart = '미등록';
          
          if (data.endTime != null) {
              endTimePart = data.endTime.split(' ')[1];
          }

          if (data.startTime && !data.endTime) {
              $('#status').text('근무중');
          } else if (data.startTime && data.endTime) {
              $('#status').text('퇴근');
          } else {
              $('#status').text('퇴근'); // startTime이 없는 경우에도 일단 퇴근으로 처리
          }
          
          $('#startTime').text(startTimePart);
          $('#endTime').text(endTimePart);
          $('#overtime').text(data.overtime || '미등록');
          $('#dayWorkTime').text(data.dayWorkTime ? data.dayWorkTime + '시간' : '미등록');
      },
      error: function(err) {
          console.log(err);
      }
  });
}

// 페이지 로드 시 초기 근태 기록 업데이트
updateAttendanceStatus();



$('#searchBtn').click(function() {
  var startDate = $('#startDate').val();
  var endDate = $('#endDate').val();

  if (!startDate || !endDate) {
      alert('시작일과 종료일을 모두 입력해주세요.');
      return;
  }

  // 기간 조회 Ajax 요청
  $.ajax({
      url: '/record/history',
      type: 'GET',
      data: {
          'startDate': startDate,
          'endDate': endDate
      },
      success: function(data) {


          const tbody = $('#recordList tbody');
          let str = ""; // 초기화 위치 확인

          for(let i = 0; i < data.length; ++i) {
            
              let startTimePart = data[i].startTime.split(' ')[1];
              let endTimePart = data[i].endTime.split(' ')[1];
              console.log(data[i].wdate);
//////날짜,요일처리

         
        let dateObj = new Date(data[i].wdate);
        // 날짜만 추출 (년-월-일 형식)
        let dateString = dateObj.toISOString().split('T')[0]; // '2024-07-08'
        let daysOfWeek = ['토','일', '월', '화', '수', '목', '금'];

        // 요일 가져오기
        let dayOfWeekIndex = dateObj.getDay();
        let dayOfWeek = daysOfWeek[dayOfWeekIndex];

        // 날짜 뒤에 요일 추가
        let formattedDate = dateString + ' (' + dayOfWeek+')'; // '2024-07-08 금'



///////////
              str += "<tr>";
              str += '<td>' + formattedDate + '</td>';
              str += '<td>'+startTimePart+'</td>';
              str += '<td>'+endTimePart+'</td>';
              str += '<td>'+data[i].dayWorkTime +'hr</td>';
              str += '<td> 기본 '+data[i].dayWorkTime+ 'hr/' +'연장' +0 +'hr</td>';
              str += '<td>'+data[i].state+'</td>';
              str += "</tr>";
          }

          console.log('Generated HTML string: ', str);
          tbody.html(str); // 최종적으로 업데이트된 문자열 적용
          console.log('Table updated successfully');
      },
      error: function(e) {
          console.error('Ajax error: ', e);
      }
  });
});



});
</script>
