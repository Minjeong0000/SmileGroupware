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
            if (info.view.type === "dayGridMonth") {
                return {
                    html: number.outerHTML
                };
            }
            return {
                domNodes: []
            };
        },

    });
    calendar.render();




    // 달력에 데이터 로드
    function loadEvents() {
    // AJAX를 사용하여 서버에서 이벤트 데이터를 로드합니다.
    
    $.ajax({
      url: '/api/event/list', // 서버의 엔드포인트
      method: 'GET',
      dataType: 'json',
      success: function(data) {
        // 데이터 로딩 성공 시, 달력에 이벤트를 추가
        data.forEach(function(event) {
          console.log(event); // 디버깅을 위해 데이터 출력
          const start = event.startDate.split(' ')[0] + 'T' + event.startTime.split(' ')[1];
          const end = event.endDate.split(' ')[0] + 'T' + event.endTime.split(' ')[1];
          calendar.addEvent({
            title: event.title,
            start: start,
            end: end,
            extendedProps: {
              location: event.location,
              // attendees: event.attendees
            },
            // color: event.color // 이벤트의 색상 설정, 서버에서 받아온 데이터에 따라 조정 가능
          });
        });
      },
      error: function() {
        console.error("이벤트 로드 실패");
      }
    });
  }
  loadEvents(); // 달력 초기화 후 이벤트 로드

  function saveEvent() {
    var title = document.getElementById('eventTitle').value;
    var startDate = document.getElementById('eventStartDate').value;
    var startTime = document.getElementById('eventStartTime').value;
    var endDate = document.getElementById('eventEndDate').value;
    var endTime = document.getElementById('eventEndTime').value;
    var location = document.getElementById('eventLocation').value;

    if (title && startDate && startTime && endDate && endTime) {
      calendar.addEvent({
        title: title,
        start: startDate + 'T' + startTime,
        end: endDate + 'T' + endTime,
        extendedProps: {
          location: location
        }
      });
      resetEventForm();
    } else {
      alert('제목, 시작일, 시작 시간, 종료일, 종료 시간을 입력하세요.');
    }
  }

  function resetEventForm() {
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