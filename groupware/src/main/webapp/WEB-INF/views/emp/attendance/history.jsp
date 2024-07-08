<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내 근태 기록 조회</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/attendance_cal.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.14/index.global.min.js'></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/emp/attendance/history.css">
    <style> 
    body{
        margin: 0;
        padding: 0;
    }
    #calendar {
        width: 1200px; height: 80vh;
        padding-left: 30px;
    }
    .fc .fc-daygrid-day {
            /*width: 100px; /* 날짜 칸의 고정 너비 */
            height: 100px; /* 날짜 칸의 고정 높이 */
            /*max-width: none; /* 기본 최대 너비 제한 해제 */
            max-height: 138px; /* 기본 최대 높이 제한 해제 */
        }
        .fc .fc-daygrid-day-frame {
            height: 100%; /* 날짜 칸의 콘텐츠가 칸 전체를 채우도록 설정 */
            display: flex;
            flex-direction: column;
        }
        .fc .fc-daygrid-day-events {
            flex-grow: 1; /* 이벤트 영역이 남은 공간을 채우도록 설정 */
            overflow-y: hidden; /* 이벤트가 많을 경우 스크롤 바 표시 */
        }

    </style>
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
                //   updateAttendanceStatus(); // 출근 기록 후 업데이트

                  location.href="/emp/attendance/cal";
                //   document.querySelector("#status").innerHTML = '근무중';

                //   document.querySelector("#startTime").innerText = timeStr;

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
                    location.href="/emp/attendance/cal";
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


// 근태 기록 업데이트 함수
function updateAttendanceStatus() {
        $.ajax({
            url: "/record/todayRecord",
            type: 'GET',
            success: function(data) {

                const stratTimePart= data.startTime.split(' ')[1];
                const endTimePart= data.endTime.split(' ')[1];

                if (data.startTime && !data.endTime) {
                $('#status').text('근무중');
                } else if (data.startTime && data.endTime) {
                    $('#status').text('퇴근');
                } else {
                    $('#status').text('퇴근'); // startTime이 없는 경우에도 일단 퇴근으로 처리
                }
                $('#startTime').text(stratTimePart || '미등록');
                $('#endTime').text(endTimePart|| '미등록');
                $('#overtime').text(data.overtime || '미등록');
                $('#dayWorkTime').text(data.dayWorkTime+'시간' || '미등록');


            },
            error: function(err) {
                console.log(err);
            }
        });
    }

// 페이지 로드 시 초기 근태 기록 업데이트
    updateAttendanceStatus();

//달력에 근태띄우는부분
    $.ajax({
        url: "${pageContext.request.contextPath}/record/list",
        type: 'get',
        success: function(data) {
            data.forEach(function(event) {
                console.log(event.startTime);
                const dateTimeString = event.startTime;
                // 문자열을 공백으로 분리하여 날짜와 시간 부분을 나눔
                const timePart = dateTimeString.split(' ')[1];
                console.log(timePart);  // 출력: "09:00:00"
                var startDate = event.wdate;
                // 결과 출력
                var endDate = event.wdate;
                calendar.addEvent({
                    title: '출근 : ' + timePart,
                    start: startDate,
                    end: endDate,
                    allDay: true,
                    color: '#f0f1f5',
                    textColor: '#3d3d3d'
                });
            });

            data.forEach(function(event) {
                if (event.endTime !== null) {
                    const timePart = event.endTime.split(' ')[1];
                    var startDate = event.wdate;
                    var endDate = event.wdate;
                    calendar.addEvent({
                        title: '퇴근 : ' + timePart,
                        start: startDate,
                        end: endDate,
                        allDay: true,
                        color: '#f0f1f5',
                        textColor: '#3d3d3d'
                    });
                }
            });

            data.forEach(function(event) {
                if (event.dayWorkTime !== null) {
                    var eventTitle = event.dayWorkTime;
                    var startDate = event.wdate;
                    var endDate = event.wdate;
                    calendar.addEvent({
                        title: '총 근무시간  : ' + eventTitle,
                        start: startDate,
                        end: endDate,
                        allDay: true,
                        color: 'rgba(141, 156, 245, 0.6)',
                        textColor: '#3d3d3d'
                    });
                }
            });

        },
        error: function(err) {
            console.log(err);
        }
    });


 
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
            console.log(data);
            // 여기에 서버에서 받은 데이터를 처리하는 코드를 추가할 수 있습니다
        },
        error: function(e) {
            console.error(e);
        }
    });
});









});
</script>


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


        </div>


        <div class="column">
            
            
            <h1>기간 조회</h1>
            <div>
                start:<input type="date" name="startDate" id="startDate">
            
                end:<input type="date" name="endDate" id="endDate">
                <button type="button" id="searchBtn">검색</button>
            </div>

            <div id="test"></div>




        </div>
    </div>
</body>
</html>
