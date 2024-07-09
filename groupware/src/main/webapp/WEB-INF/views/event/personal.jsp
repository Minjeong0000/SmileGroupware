<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>일정관리</title>
<link rel="stylesheet" type="text/css" href="/css/event/personal/calendar.css">
<script defer src="/js/event/personal.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.14/index.global.min.js'></script>
<script>

   //화면 준비되면 동작하는 함수 
   document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    headerToolbar: {
      start: 'prev next today',
      center: 'title',
      end: 'dayGridMonth dayGridWeek dayGridDay addEventButton saveEventButton'
    },
    customButtons: {
      addEventButton: {
        text: '일정 추가',
        click: function() {
          // 일정 추가 버튼 클릭 시, 이벤트 폼을 표시합니다.
          const eventForm = document.getElementById('eventForm');
          eventForm.style.display = 'block';
        }
      },
      saveEventButton: {
        text: '일정 저장',
        click: function() {
          // 일정 저장 버튼 클릭 시, 입력된 데이터를 이용해 새로운 이벤트를 달력에 추가합니다.
          saveEvent();
        }
      }
    },
    buttonText: {
      today: '오늘',
      month: '월',
      week: '주',
      day: '일',
      prev: '이전',
      next: '다음'
    },
    locale: 'ko',
    dayCellContent: function(info) {
      var number = document.createElement("a");
      number.classList.add("fc-daygrid-day-number");
      number.innerHTML = info.dayNumberText.replace("일", "").replace("日", "");
      return { html: number.outerHTML };
    },
    height: '100%'
  });

  // 달력에 데이터 로드
  function loadEvents() {
    // AJAX를 사용하여 서버에서 이벤트 데이터를 로드합니다.
    
    $.ajax({
      url: '/api/event/list', // 서버의 엔드포인트
      method: 'GET',
      dataType: 'json',
      success: function(data) {
        // 데이터 로딩 성공 시, 달력에 이벤트를 추가합니다.
        data.forEach(function(event) {
          calendar.addEvent({
            title: event.title,
            start: event.startDate + 'T' + event.startTime,
            end: event.endDate + 'T' + event.endTime,
            extendedProps: {
              location: event.location,
              attendees: event.attendees
            },
            color: event.color // 이벤트의 색상 설정, 서버에서 받아온 데이터에 따라 조정 가능
          });
        });
      },
      error: function() {
        console.error("이벤트 로드 실패");
      }
    });
  }

  calendar.render();
  loadEvents(); // 달력 초기화 후 이벤트 로드

  function saveEvent() {
    // 폼에서 데이터를 가져와 새 이벤트를 생성합니다.
    var title = document.getElementById('eventTitle').value;
    var startDate = document.getElementById('eventStartDate').value;
    var endDate = document.getElementById('eventEndDate').value;
    var color = document.getElementById('eventColor').value;
    var location = document.getElementById('eventLocation').value;
    var attendees = document.getElementById('eventAttendees').value;

    if (title && startDate && endDate) {
      calendar.addEvent({
        title: title,
        start: startDate,
        end: endDate,
        color: color,
        extendedProps: {
          location: location,
          attendees: attendees
        }
      });
      resetEventForm();
    } else {
      alert('제목, 시작일, 종료일을 입력하세요.');
    }
  }

  function resetEventForm() {
    // 이벤트 폼을 숨기고 초기화합니다.
    document.getElementById('eventForm').style.display = 'none';
    document.getElementById('eventForm').reset();
  }
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
            <!-- <h2>각 페이지의 <br>인적사항 <br> 근태관리 <br>일정 <br>결재 서류 <br>등을 작성하면 됩니다.</h2> -->
        </div>
            <div class="column content">
                 <div id='calendar'></div>
            </div>
    </div>

</div>



</body>
</html>