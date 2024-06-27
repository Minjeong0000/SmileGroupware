<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>슬라이드 네비게이터 바</title>

<script src="${pageContext.request.contextPath}/js/attendance_cal.js"></script>

<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.14/index.global.min.js'></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/emp/attendance/cal.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      headerToolbar : {
        start : 'prev next today',
        center : 'title',
        end : 'dayGridMonth,dayGridWeek,dayGridDay'
      },
      buttonText: {
        today: '오늘',
        month: '월',
        week: '주',
        day: '일',
        prev: '이전',
        next: '다음'
      },
      titleFormat : function(date) {
        return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
      },
      selectable : true,
      droppable : true,
      editable : false,
      nowIndicator: true,
      locale: 'ko',
      dayCellContent: function(info) {
        var number = document.createElement("a");
        number.classList.add("fc-daygrid-day-number");
        number.innerHTML = info.dayNumberText.replace("일", "").replace("日", "");
        if (info.view.type === "dayGridMonth") {
          return {
            html: number.outerHTML
          };
        }
        return {
          domNodes: []
        };
      },
      googleCalendarApiKey: 'AIzaSyDxgdE0bei5zArUxeQGFA8nLslWfa2mE1k',
      events: {
        googleCalendarId: '9572805292a0dd4e1adfff1e3f0f134238698e4be003e3124b1ace0c53b68c05@group.calendar.google.com'
      }
    });
    calendar.render();


    // 출근, 퇴근 버튼 핸들러
    document.getElementById('checkInBtn').addEventListener('click', function() {

      handleAttendance('출근');

      
      alert('출근 기록이 성공적으로 등록됐습니다.');
    });

    document.getElementById('checkOutBtn').addEventListener('click', function() {
      if (confirm('퇴근하시겠습니까?')) {
        handleAttendance('퇴근');
        alert('퇴근 기록이 성공적으로 등록됐습니다.');
      }
    });

    var attendance = {}; // 출근/퇴근 기록을 저장할 객체

    function handleAttendance(type) {
      var now = new Date();
      var dateStr = now.toISOString().split('T')[0]; // 날짜 문자열 (YYYY-MM-DD)
      var timeStr = now.toTimeString().split(' ')[0]; // 시간 문자열 (HH:MM:SS)
      var title = type + ' - ' + timeStr;

      // 출근 시간이 이미 기록된 경우
      if (type === '출근' && attendance[dateStr] && attendance[dateStr].checkIn) {
        alert('오늘은 이미 출근 기록이 있습니다.');
        return;
      }

      // 퇴근 시간이 이미 기록된 경우
      if (type === '퇴근' && attendance[dateStr] && attendance[dateStr].checkOut) {
        alert('오늘은 이미 퇴근 기록이 있습니다.');
        return;
      }

      // 출근 시간 저장
      if (type === '출근') {
        
        attendance[dateStr] = { checkIn: timeStr };
        document.querySelector("#status").innerHTML = '근무중';
        document.querySelector("#startTime").innerText = timeStr;
      }

      // 퇴근 시간 저장
      if (type === '퇴근') {
        attendance[dateStr].checkOut = timeStr;
        document.querySelector("#status").innerHTML = "근무종료";
        document.querySelector("#endTime").innerText= timeStr;


      }


if(type==='출근'){
  $.ajax({
        // url:"/emp/attendance/start",
        url:"/record/start",
        type:"POST",

        success: function(data){
          console.log(data);

        //swy
        calendar.addEvent({
          title: '출근 : '+timeStr,
          start: now,
          allDay: true,
          textColor: '#3d3d3d',
          color:'#f0f1f5'
        });

        },
        error: function(err){
          console.log(err);
        }
      });
}


if(type==='퇴근'){
  $.ajax({
        // url:"/emp/attendance/start",
        url:"/record/end",
        type:"get",
        success: function(data){
          console.log(data);

        //swy
        calendar.addEvent({
          title: '퇴근 : '+timeStr,
          start: now,
          allDay: true,
          color:'#f0f1f5',
          textColor: '#3d3d3d'
        });

        },
        error: function(err){
          console.log(err);
        }
      });
}



      

      // 버튼 비활성화 처리
      updateButtonState();
    }

    // 출퇴근 버튼 상태 업데이트 함수
    function updateButtonState() {
      var now = new Date();
      var dateStr = now.toISOString().split('T')[0]; // 오늘 날짜 문자열 (YYYY-MM-DD)

      // 출근 기록이 있으면 출근 버튼 비활성화
      if (attendance[dateStr] && attendance[dateStr].checkIn) {
        document.getElementById('checkInBtn').disabled = true;
        document.getElementById('checkInBtn').classList.add('inactive');
      }

      // 퇴근 기록이 있으면 퇴근 버튼 비활성화
      if (attendance[dateStr] && attendance[dateStr].checkOut) {
        document.getElementById('checkOutBtn').classList.add('inactive');
        document.getElementById('checkOutBtn').disabled = true;


      }
    }

    // 페이지 로드 시 버튼 상태 업데이트
    updateButtonState();

    $.ajax({
      url: '/record/list',
      type: 'get',
      success: function(data) {
          console.log(data[0].startTime);
      
        data.forEach(function(event) {
          var eventTitle = event.startTime;
          var startDate = event.wdate;
          var endDate = event.wdate;
          calendar.addEvent({
            title: '출근 : '+eventTitle,
            start: startDate,
            end: endDate,
            allDay: true,
            color : '#f0f1f5',
            textColor: '#3d3d3d'
          });
        });

        //퇴근시간이 null이 아닐 때만 달력에 보여주기
        data.forEach(function(event) {
          if(event.endTime!==null){
            var eventTitle = event.endTime;
          var startDate = event.wdate;
          var endDate = event.wdate;
          calendar.addEvent({
            title: '퇴근 : '+eventTitle,
            start: startDate,
            end: endDate,
            allDay: true,
            color : '#f0f1f5',
            textColor: '#3d3d3d'
          });
          }

        });
        //퇴근 안찍혀서 총근무시간이 null일때 달력 표시x

        data.forEach(function(event) {

          if(event.dayWorkTime!==null){
            var eventTitle = event.dayWorkTime;
          var startDate = event.wdate;
          var endDate = event.wdate;
          var color = 'rgba(141, 156, 245, 0.6)';
          calendar.addEvent({
            title: '총 근무시간  : '+eventTitle,
            start: startDate,
            end: endDate,
            allDay: true,
            color : color,
            textColor: '#3d3d3d'
            
          });
          }


        });




      },
      error: function(err) {
        console.log(err);
      }
    });
  });


 
</script>
</head>
<body>
  <div id="mySidenav" class="sidenav">
    <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
    <a href="#"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
    <a href="#"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
    <a href="#"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
    <a href="#"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
    <a href="#"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
    <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
  </div>

  <div id="main" onclick="closeNav()">



 <div class="column">
      <h2>근태관리</h2>
      <div>
        <h3 id="current-date"></h3>
      </div>
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
        <span>${sessionScope.loginEmployeeVo.empName}</span>|
        <span>${sessionScope.loginEmployeeVo.departmentNo} </span>|
        <span>${sessionScope.loginEmployeeVo.roleNo}<span>
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

    <div class="column content">
      <div class=""><h1>출퇴근기록부(사원)</h1></div>
      <div id='calendar'></div>
    </div>
  </div>
</body>
</html>
