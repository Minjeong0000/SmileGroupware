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
<style>
 /* 버튼 스타일 추가 */
.custom-button {
    display: block;
    width: 200px;
    height: 50px;
    font-size: 18px;
    background-color: #8d9cf5;
    color: white;
    border: none;
    border-radius: 5px;
    margin: 20px auto;
    text-align: center;
    line-height: 50px;
    cursor: pointer;
}

.custom-button:hover {
    background-color: #6b80f7;
}

/* 모달창 스타일 */
.modal {
    display: none; /* 초기 상태에서 모달창 숨김 */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.4);
}

.modal.show {
    display: block; /* 모달창을 보이도록 설정 */
}

.modal-content {
    background-color: #fefefe;
    margin: 10% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 600px;
    border-radius: 10px;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

.modal-header, .modal-footer {
    padding: 10px;
    background-color: #8d9cf5;
    color: white;
    border-radius: 10px 10px 0 0;
}

.modal-footer {
    border-radius: 0 0 10px 10px;
}

.modal-body {
    padding: 2px 16px;
    max-height: 60vh;
    overflow-y: auto;
}

.modal-body input, .modal-body textarea, .modal-body select {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.modal-body label {
    display: block;
    margin: 10px 0 5px;
}

.modal-footer button {
    background-color: #8d9cf5;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.modal-footer button:hover {
    background-color: #4c64f1;
}

/* 캘린더 스타일 */
.column:last-child {
    flex: 1; /* 두 번째 컬럼이 남은 공간을 모두 채움 */
    display: flex;
    flex-direction: column;
}

#calendar {
    flex: 1; /* 캘린더가 남은 공간을 모두 채움 */
    width: 100%; /* 캘린더의 너비를 100%로 설정 */
    height: 100%; /* 캘린더의 높이를 100%로 설정 */
}

</style>


</head>
<body>
    <div id="mySidenav" class="sidenav">
<a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
            <a href="home.html"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
            <a href="/event/personal/calendar"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
            <a href="/emp/attendance/cal"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
            <a href="/approval/home"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
            <a href="/message/received"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
            <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
    </div>

    <div id="main" onclick="closeNav()">
        <div class="column">
           <button class="custom-button">일정관리</button>
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
                <h2>일정 등록</h2>
            </div>
            <div class="modal-body">
                <label for="title">제목</label>
                <input type="text" id="title" name="title">
                
                <label for="content">내용</label>
                <textarea id="content" name="content"></textarea>
                
                <label for="attendees">참석자</label>
                <input type="text" id="attendees" name="attendees">

                <label for="typeNo">카테고리</label>
                <select id="typeNo" name="typeNo">
                    <option value="1">연차</option>
                    <option value="2">회의</option>
                    <option value="3">개인업무</option>
                    <option value="4">미팅</option>
                    <option value="5">오전반차</option>
                    <option value="6">오후반차</option>
                    <option value="7">외근</option>
                    <option value="8">휴가</option>
                    <option value="9">무역 협상</option>
                    <option value="10">제품 설명회</option>
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
                <p><strong>내용:</strong> <span id="viewContent"></span></p>
                <p><strong>참석자:</strong> <span id="viewAttendees"></span></p>
                <p><strong>카테고리:</strong> <span id="viewCategory"></span></p>
                <p><strong>시작 일정:</strong> <span id="viewStartDate"></span></p>
                <p><strong>종료 일정:</strong> <span id="viewEndDate"></span></p>
                <p><strong>시작 시간:</strong> <span id="viewStartTime"></span></p>
                <p><strong>종료 시간:</strong> <span id="viewEndTime"></span></p>
            </div>
            <div class="modal-footer">
                <button id="editEvent">수정하기</button>
                <button id="deleteEvent">삭제하기</button>
                <button id="closeViewModal">닫기</button>
            </div>
        </div>
    </div>


    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                
                headerToolbar: { // 헤더에 표시할 툴 바
                                start: 'prev next today',
                                center: 'title',
                                end: 'dayGridMonth,dayGridWeek,dayGridDay'
                                },
                locale: 'ko',

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
                height: '100%',                     

                events: [],
                eventClick: function(info) {
                    openViewModal(info.event);
                },

                

                //달력에서 '일' 삭제하기 함수 
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
                },

                
            });
            calendar.render();



        // AJAX를 사용하여 서버에서 이벤트를 달력에 데이터 로드(개인일정목록)
         function loadEvents() {
        
         $.ajax({
            url: '/api/event/list', // 서버의 엔드포인트
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                // 데이터 로딩 성공 시, 달력에 이벤트를 추가
                data.forEach(function(event) {
                console.log(event); // 디버깅을 위해 데이터 출력
                let startDate = event.startDate;
                let endDate = event.endDate;
                
                console.log(startDate);
                console.log(endDate);


                const personalNo=event.personalNo
                console.log("안녕"+personalNo);


                const start = new Date(startDate);
                const end = new Date(endDate);

                calendar.addEvent({
                    personalNo:event.personalNo,
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


    document.getElementById('saveEvent').addEventListener('click', function() {
    
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;
    const attendees = document.getElementById('attendees').value;
    const typeNo = document.getElementById('typeNo').value; // 카테고리 선택 값을 확인
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const startTime= document.getElementById('startTime').value;
    const endTime = document.getElementById('endTime').value;



    $.ajax({
        url: '/api/event',
        method: 'POST',
        data:{
            
            title: title,
            content: content,
            attendees: attendees,
            typeNo: typeNo,
            startDate: startDate,
            endDate: endDate,
            startTime: startTime,
            endTime: endTime,
        },
        success: function(response) {
            console.log('Event saved successfully:', response);
            calendar.refetchEvents(); // 캘린더 업데이트
            closeEventModal(); // 모달 닫기
        },
        error: function(xhr, status, error) {
            console.error('Error saving event:', error);
            alert('Event saving failed. Please try again.');
        }
    });
});



document.getElementById('deleteEvent').addEventListener('click', function() {
    const personalNo = selectedEvent.extendedProps.personalNo;
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;
    const attendees = document.getElementById('attendees').value;
    const typeNo = document.getElementById('typeNo').value; // 카테고리 선택 값을 확인
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const startTime= document.getElementById('startTime').value;
    const endTime = document.getElementById('endTime').value;

    $.ajax({
            url: '/api/event',
            method: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify({ personalNo: personalNo }),
            success: function(response) {
                console.log('삭제 성공:', response);
                calendar.refetchEvents(); // 캘린더 업데이트
                closeEventModal(); // 모달 닫기
            },
            error: function(xhr, status, error) {
                console.error('삭제 실패:', error);
                alert('삭제 실패');
            }
        });
    });






            var eventModal = document.getElementById("eventModal");
            var viewEventModal = document.getElementById("viewEventModal");
            var eventSpan = eventModal.getElementsByClassName("close")[0];
            var viewEventSpan = viewEventModal.getElementsByClassName("close")[0];
            var selectedEvent = null;

            function openEventModal(event) {
                selectedEvent = event;
                if (event.title) {
                    document.getElementById('title').value = event.title;
                    document.getElementById('content').value = event.extendedProps.content || '';
                    document.getElementById('attendees').value = event.extendedProps.attendees || '';
                    document.getElementById('typeNo').value = event.extendedProps.typeNo || 'personal';
                    document.getElementById('startDate').value = event.startStr.split('T')[0];
                    document.getElementById('endDate').value = event.endStr ? event.endStr.split('T')[0] : '';
                    document.getElementById('startTime').value = event.startStr.split('T')[1] || '';
                    document.getElementById('endTime').value = event.endStr ? event.endStr.split('T')[1] : '';
                } else {
                    document.getElementById('title').value = '';
                    document.getElementById('content').value = '';
                    document.getElementById('attendees').value = '';
                    document.getElementById('typeNo').value = 'personal';
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
                document.getElementById('content').value = '';
                document.getElementById('attendees').value = '';
                document.getElementById('typeNo').value = 'personal';
                document.getElementById('startDate').value = '';
                document.getElementById('endDate').value = '';
                document.getElementById('startTime').value = '';
                document.getElementById('endTime').value = '';
                selectedEvent = null;
            }

            function openViewModal(event) {
                document.getElementById('viewTitle').innerText = event.title;
                document.getElementById('viewContent').innerText = event.extendedProps.content || '';
                document.getElementById('viewAttendees').innerText = event.extendedProps.attendees || '';
                document.getElementById('viewCategory').innerText = event.extendedProps.typeNo === 'personal' ? '개인 일정' : '부서별 일정';
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
                    content: document.getElementById('content').value,
                    attendees: document.getElementById('attendees').value,
                    typeNo: document.getElementById('typeNo').value,
                    start: document.getElementById('startDate').value + 'T' + document.getElementById('startTime').value,
                    end: document.getElementById('endDate').value + 'T' + document.getElementById('endTime').value,
               
                
                };

                if (selectedEvent && selectedEvent.title) {
                    // 수정
                    selectedEvent.setProp('title', event.title);
                    selectedEvent.setExtendedProp('content', event.content);
                    selectedEvent.setExtendedProp('attendees', event.attendees);
                    selectedEvent.setExtendedProp('typeNo', event.typeNo);
                    selectedEvent.setStart(event.start);
                    selectedEvent.setEnd(event.end);
                } else {
                    // 추가
                    calendar.addEvent({
                        title: event.title,
                        start: event.start,
                        end: event.end,
                        extendedProps: {
                            content: event.content,
                            attendees: event.attendees,
                            typeNo: event.typeNo
                        },
                        backgroundColor: event.typeNo === 'personal' ? 'lightpink' : 'lightskyblue',
                        borderColor: event.typeNo === 'personal' ? 'lightpink' : 'lightskyblue'
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
</body>
</html>
