<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내 근태 현황</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/attendance_cal.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.14/index.global.min.js'></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/emp/attendance/cal.css">
    <style> #calendar {width: 1000px; height: 500px;}</style>
    <script>
      document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            start: 'prev next today',
            center: 'title',
            end: ''
        },
        buttonText: {
            today: '오늘',

            prev: '이전',
            next: '다음'
        },
        titleFormat: function(date) {
            return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
        },
        selectable: true,
        droppable: true,
        editable: false,
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

    });
    calendar.render();

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
});

      </script>
</head>
<body>`
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
            <%@ include file ="common_left.jsp" %>
        </div>


        <div class="column">
            <div id='calendar'></div>
        </div>
    </div>
</body>
</html>
