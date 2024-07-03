


document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    headerToolbar: { // 헤더에 표시할 툴 바
      start: 'prev next today',
      center: 'title',
      end: 'dayGridMonth,dayGridWeek,dayGridDay addEventButton saveEventButton'
    },

    customButtons: {
      addEventButton: {
        text: '일정 추가',
        click: function() {
          var eventForm = document.getElementById('eventForm');
          eventForm.style.display = 'block';
        }
      },
      saveEventButton: {
        text: '일정 저장',
        click: function() {
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
            // 폼 값 초기화
            document.getElementById('eventTitle').value = '';
            document.getElementById('eventStartDate').value = '';
            document.getElementById('eventEndDate').value = '';
            document.getElementById('eventColor').value = '#ff0000';
            document.getElementById('eventLocation').value = '';
            document.getElementById('eventAttendees').value = '';
          } else {
            alert('제목, 시작일, 종료일을 입력하세요.');
          }
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
    titleFormat : function(date) {
       return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
    },
    selectable : true, // 달력 일자 드래그 설정가능
    droppable : true,
    editable : true,
    nowIndicator: true, // 현재 시간 마크
    locale: 'ko', // 한국어 설정


  //달력에서 '일' 삭제하기
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




    googleCalendarApiKey: 'AIzaSyByvDh_cmaTCRJZO1A8_G39dmFCX7B0aqg',
    events: {
    googleCalendarId: 'c1fdbaae44266ca34551e199938d336dadcfe402544c9a7b7ac773d6215e9180@group.calendar.google.com'
},
    height: '100%'

  });
  calendar.render();

//Full Calendar cell 클릭 이벤트 추가
var calendar = new Calendar(calendarEl, {
  dateClick: function() {
    alert('a day has been clicked!');
  }
});


calendar.on('dateClick', function(info) {
  console.log('clicked on ' + info.dateStr);
});
  document.getElementById('saveEventButton').addEventListener('click', function() {
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


      var eventForm = document.getElementById('eventForm');
      eventForm.style.display = 'none';
      eventForm.reset(); // 폼 초기화
    } else {
      alert('제목, 시작일, 종료일을 입력하세요.');
    }
  });
});


 // 별도의 함수를 사용하여 AJAX 요청을 처리합니다.
 loadEvents();

 function loadEvents() {
     $.ajax({
         type: "GET",
         url: "http://127.0.0.1:8080/event/personal/list",
         data:{}
         dataType: 'json',
         success: function(data) {
             console.log("통신성공", data);
             data.forEach(function(event) {
                 calendar.addEvent({
                     title: event.title,
                     start: event.enrollDate, // 시작 시간 추가 (예시)
                     end: event.end // 종료 시간 추가 (예시)
                 });
             });
         },
         error: function() {
             console.error("이벤트 로딩 실패");
         }
     });
 }
