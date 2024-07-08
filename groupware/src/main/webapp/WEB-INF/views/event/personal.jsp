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
            <button class="custom-button">개인일정관리</button>
            <button class="custom-button">부서일정관리</button>
        </div>
        <div class="column content">
            <div id='calendar'></div>
        </div>
    </div>


     <!-- 일정 등록/수정 모달창 구조 -->
     <div id="eventModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>일정 등록/수정</h2>
            </div>
            <div class="modal-body">
                <label for="title">제목</label>
                <input type="text" id="title" name="title">
                
                <label for="description">내용</label>
                <textarea id="description" name="description"></textarea>
                
                <label for="attendees">참석자</label>
                <input type="text" id="attendees" name="attendees">

                <label for="category">카테고리</label>
                <select id="category" name="category">
                    <option value="personal">개인 일정</option>
                    <option value="department">부서별 일정</option>
                </select>
                
                <label for="startDate">시작 일정</label>
                <input type="date" id="startDate" name="startDate">
                
                <label for="endDate">종료 일정</label>
                <input type="date" id="endDate" name="endDate">
                
                <label for="startTime">시작 시간</label>
                <input type="time" id="startTime" name="startTime">
                
                <label for="endTime">종료 시간</label>
                <input type="time" id="endTime" name="endTime">
            </div>
            <div class="modal-footer">
                <button id="saveEvent">저장</button>
                <button id="deleteEvent">삭제</button>
            </div>
        </div>
    </div>

    <!-- 일정 보기 모달창 구조 -->
    <div id="viewEventModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>일정 보기</h2>
            </div>
            <div class="modal-body">
                <p><strong>제목:</strong> <span id="viewTitle"></span></p>
                <p><strong>내용:</strong> <span id="viewDescription"></span></p>
                <p><strong>참석자:</strong> <span id="viewAttendees"></span></p>
                <p><strong>카테고리:</strong> <span id="viewCategory"></span></p>
                <p><strong>시작 일정:</strong> <span id="viewStartDate"></span></p>
                <p><strong>종료 일정:</strong> <span id="viewEndDate"></span></p>
                <p><strong>시작 시간:</strong> <span id="viewStartTime"></span></p>
                <p><strong>종료 시간:</strong> <span id="viewEndTime"></span></p>
            </div>
            <div class="modal-footer">
                <button id="editEvent">수정하기</button>
                <button id="closeViewModal">닫기</button>
            </div>
        </div>
    </div>

 
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                locale: 'ko',
                events: [],
                eventClick: function(info) {
                    openViewModal(info.event);
                },
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
                dateClick: function(info) {
                    openEventModal({ start: info.dateStr });
                }
            });
            calendar.render();

            const eventModal = document.getElementById("eventModal");
            var viewEventModal = document.getElementById("viewEventModal");
            var eventSpan = eventModal.getElementsByClassName("close")[0];
            var viewEventSpan = viewEventModal.getElementsByClassName("close")[0];
            var selectedEvent = null;

            function openEventModal(event) {
                selectedEvent = event;
                if (event.title) {
                    document.getElementById('title').value = event.title;
                    document.getElementById('description').value = event.extendedProps.description || '';
                    document.getElementById('attendees').value = event.extendedProps.attendees || '';
                    document.getElementById('category').value = event.extendedProps.category || 'personal';
                    document.getElementById('startDate').value = event.startStr.split('T')[0];
                    document.getElementById('endDate').value = event.endStr ? event.endStr.split('T')[0] : '';
                    document.getElementById('startTime').value = event.startStr.split('T')[1] || '';
                    document.getElementById('endTime').value = event.endStr ? event.endStr.split('T')[1] : '';
                } else {
                    document.getElementById('title').value = '';
                    document.getElementById('description').value = '';
                    document.getElementById('attendees').value = '';
                    document.getElementById('category').value = 'personal';
                    document.getElementById('startDate').value = event.start;
                    document.getElementById('endDate').value = '';
                    document.getElementById('startTime').value = '';
                    document.getElementById('endTime').value = '';
                }
                eventModal.style.display = "block";
            }

            function closeEventModal() {
                eventModal.style.display = "none";
                // 입력 필드 초기화
                document.getElementById('title').value = '';
                document.getElementById('description').value = '';
                document.getElementById('attendees').value = '';
                document.getElementById('category').value = 'personal';
                document.getElementById('startDate').value = '';
                document.getElementById('endDate').value = '';
                document.getElementById('startTime').value = '';
                document.getElementById('endTime').value = '';
                selectedEvent = null;
            }

            function openViewModal(event) {
                document.getElementById('viewTitle').innerText = event.title;
                document.getElementById('viewDescription').innerText = event.extendedProps.description || '';
                document.getElementById('viewAttendees').innerText = event.extendedProps.attendees || '';
                document.getElementById('viewCategory').innerText = event.extendedProps.category === 'personal' ? '개인 일정' : '부서별 일정';
                document.getElementById('viewStartDate').innerText = event.startStr.split('T')[0];
                document.getElementById('viewEndDate').innerText = event.endStr ? event.endStr.split('T')[0] : '';
                document.getElementById('viewStartTime').innerText = event.startStr.split('T')[1] || '';
                document.getElementById('viewEndTime').innerText = event.endStr ? event.endStr.split('T')[1] : '';
                selectedEvent = event;
                viewEventModal.style.display = "block";
            }

            function closeViewModal() {
                viewEventModal.style.display = "none";
                selectedEvent = null;
            }

            eventSpan.onclick = function() {
                closeEventModal();
            }

            viewEventSpan.onclick = function() {
                closeViewModal();
            }

            window.onclick = function(event) {
                if (event.target == eventModal) {
                    closeEventModal();
                }
                if (event.target == viewEventModal) {
                    closeViewModal();
                }
            }

            document.getElementById('saveEvent').onclick = function() {
                var event = {
                    title: document.getElementById('title').value,
                    description: document.getElementById('description').value,
                    attendees: document.getElementById('attendees').value,
                    category: document.getElementById('category').value,
                    start: document.getElementById('startDate').value + 'T' + document.getElementById('startTime').value,
                    end: document.getElementById('endDate').value + 'T' + document.getElementById('endTime').value,
                };

                if (selectedEvent && selectedEvent.title) {
                    // 수정
                    selectedEvent.setProp('title', event.title);
                    selectedEvent.setExtendedProp('description', event.description);
                    selectedEvent.setExtendedProp('attendees', event.attendees);
                    selectedEvent.setExtendedProp('category', event.category);
                    selectedEvent.setStart(event.start);
                    selectedEvent.setEnd(event.end);
                } else {
                    // 추가
                    calendar.addEvent({
                        title: event.title,
                        start: event.start,
                        end: event.end,
                        extendedProps: {
                            description: event.description,
                            attendees: event.attendees,
                            category: event.category
                        },
                        backgroundColor: event.category === 'personal' ? 'lightpink' : 'lightskyblue',
                        borderColor: event.category === 'personal' ? 'lightpink' : 'lightskyblue'
                    });
                }

                closeEventModal();
            }

            document.getElementById('deleteEvent').onclick = function() {
                if (selectedEvent && selectedEvent.title) {
                    selectedEvent.remove();
                }
                closeEventModal();
            }

            document.getElementById('editEvent').onclick = function() {
                closeViewModal();
                openEventModal(selectedEvent);
            }

            document.getElementById('closeViewModal').onclick = function() {
                closeViewModal();
            }
        });
    </script>
</div>



</body>
</html>