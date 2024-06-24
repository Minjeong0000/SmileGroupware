<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>슬라이드 네비게이터 바</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/emp/attendance/cal.css">
<script src="${pageContext.request.contextPath}/static/js/cal.js"></script>

<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.14/index.global.min.js'></script>
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
      }

      // 퇴근 시간 저장
      if (type === '퇴근') {
        attendance[dateStr].checkOut = timeStr;
      }

      // 캘린더에 이벤트 추가
      calendar.addEvent({
        title: title,
        start: now,
        allDay: true,
        color:type === '출근'? '#2E64FE':'#F7819F'
      });

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
      <div id="current-date"></div>
      <h2 id="currentTime"></h2>

      <table>
        <tr>
          <th>
            업무상태
          </th>
          <td>
            출근전
          </td>
        </tr>
        <tr>
          <th>
            출근시간
          </th>
          <td>
            미등록
          </td>
        </tr>
        <tr>
          <th>
            퇴근시간
          </th>
          <td>
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
    <div class="column content">
      <div class=""><h1>출퇴근기록부(사원)</h1></div>
      <div id='calendar'></div>
    </div>
  </div>
</body>
</html>
