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
    <%@ include file="/WEB-INF/views/nav/sideNav.jsp" %>


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
                <div class="submenu-item"><a href="http://127.0.0.1:8080/emp/attendance/history">내 근태 현황</a></div>
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
                <c:if test="${pvo.currentPage > 1}">
                    <a href="?pno=1">&laquo;</a>
                    <a href="?pno=${pvo.currentPage - 1}">&lt;</a>
                </c:if>
                <c:forEach var="i" begin="${pvo.startPage}" end="${pvo.endPage}">
                    <c:choose>
                        <c:when test="${i == pvo.currentPage}">
                            <strong>${i}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="?pno=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pvo.currentPage < pvo.maxPage}">
                    <a href="?pno=${pvo.currentPage + 1}">Next</a>
                    <a href="?pno=${pvo.maxPage}">Last</a>
                </c:if>
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


//페이지들어가자마자 전체목록불러오기(페이징처리 필요)
//페이지 번호를 인자로 받아 출퇴근 목록 로드
function loadAttendanceList(pno){
    $.ajax({
      url: '/record/history/list',
      type: 'GET',
      data: {
        pno: pno
      },
      success: function(response) {

        const data = response.attendanceList;
        const pvo = response.pvo;
          const tbody = $('#recordList tbody');
          let str = ""; // 초기화 위치 확인
          for(let i = 0; i < data.length; ++i) {
              let startTimePart = data[i].startTime.split(' ')[1];
              let endTimePart = data[i].endTime.split(' ')[1];

              console.log(endTimePart);
                let dateObj = new Date(data[i].wdate);
                let options = { year: 'numeric', month: '2-digit', day: '2-digit' };
                let dateString = dateObj.toLocaleDateString('ko-KR', options); // 한국 시간대를 기준으로 날짜 형식을 설정

                let daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
                let dayOfWeekIndex = dateObj.getDay();
                let dayOfWeek = daysOfWeek[dayOfWeekIndex];

                let formattedDate = dateString + ' (' + dayOfWeek + ')';
                 // 조건문 추가(4시간마다 0.5시간 휴게시간 뺌)
                 let adjustedDayWorkTime = data[i].dayWorkTime;
                    if (data[i].dayWorkTime >= 4.5 && data[i].dayWorkTime < 9) {
                        adjustedDayWorkTime -= 0.5;
                    } else if (data[i].dayWorkTime >= 9) {
                        adjustedDayWorkTime -= 1;
                    }
                // 초과 시간 계산
                let [endHour, endMinute, endSecond] = endTimePart.split(':').map(Number);
                let excessTime = 0;

                if (endHour > 18 || (endHour === 18 && (endMinute > 0 || endSecond > 0))) {
                    excessTime = (endHour - 18) + (endMinute / 60) + (endSecond / 3600);
                }


              str += "<tr>";
              str += '<td>' + formattedDate + '</td>';
              str += '<td>'+startTimePart+'</td>';
              str += '<td>'+endTimePart+'</td>';
              str += '<td>'+adjustedDayWorkTime +'hr</td>';
              str += '<td> 기본 '+adjustedDayWorkTime+ 'hr/' +'연장' +excessTime.toFixed(2) +'hr</td>';
              str += '<td>'+data[i].state+'</td>';
              str += "</tr>";
          }

          tbody.html(str);

            // 페이징바 업데이트
            const pagination = $(".pagination");
            let pageStr = "";

            // 이전 페이지 링크 생성
            if (pvo.currentPage > 1) {
                pageStr += '<a href="#" data-pno="1">First</a>';
                pageStr += '<a href="#" data-pno="' + (pvo.currentPage - 1) + '">Previous</a>';
            }
            // 페이지 번호 링크 생성
            for (let i = pvo.startPage; i <= pvo.endPage; i++) {
                if (i === pvo.currentPage) {
                    pageStr += '<strong>' + i + '</strong>'; // 현재 페이지는 굵게 표시
                } else {
                    pageStr += '<a href="#" data-pno="' + i + '">' + i + '</a>';
                }
            }
            // 다음 페이지 링크 생성
            if (pvo.currentPage < pvo.maxPage) {
                pageStr += '<a href="#" data-pno="' + (pvo.currentPage + 1) + '">Next</a>';
                pageStr += '<a href="#" data-pno="' + pvo.maxPage + '">Last</a>';
            }

            pagination.html(pageStr); // 페이징바 업데이트
                // 페이징바 클릭 이벤트 핸들러 등록
            $(".pagination a").click(function (e) {
                e.preventDefault(); // 기본 동작 방지
                let pno = $(this).data("pno"); // 클릭한 링크의 페이지 번호 추출
                loadAttendanceList(pno); // 해당 페이지 번호로 다시 목록 로드
            });
      },
      error: function(e) {
          console.error('Ajax error: ', e);
      }
  });

}
//페이지 로드시 첫번쨰페이지로 
loadAttendanceList(1);





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
            const pagination = $('.pagination');


          const tbody = $('#recordList tbody');
          let str = ""; // 초기화 위치 확인

          for(let i = 0; i < data.length; ++i) {
            
              let startTimePart = data[i].startTime.split(' ')[1];
              let endTimePart = data[i].endTime.split(' ')[1];
            console.log(endTimePart);
                let dateObj = new Date(data[i].wdate);
                let options = { year: 'numeric', month: '2-digit', day: '2-digit' };
                let dateString = dateObj.toLocaleDateString('ko-KR', options); // 한국 시간대를 기준으로 날짜 형식을 설정

                let daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
                let dayOfWeekIndex = dateObj.getDay();
                let dayOfWeek = daysOfWeek[dayOfWeekIndex];

                let formattedDate = dateString + ' (' + dayOfWeek + ')';
                // 조건문 추가(4시간마다 0.5시간 휴게시간 뺌)
                let adjustedDayWorkTime = data[i].dayWorkTime;
                if (data[i].dayWorkTime >= 4.5 && data[i].dayWorkTime < 9) {
                    adjustedDayWorkTime -= 0.5;
                } else if (data[i].dayWorkTime >= 9) {
                    adjustedDayWorkTime -= 1;
                }
                // 초과 시간 계산
                let [endHour, endMinute, endSecond] = endTimePart.split(':').map(Number);
                let excessTime = 0;

                if (endHour > 18 || (endHour === 18 && (endMinute > 0 || endSecond > 0))) {
                    excessTime = (endHour - 18) + (endMinute / 60) + (endSecond / 3600);
                }

              str += "<tr>";
              str += '<td>' + formattedDate + '</td>';
              str += '<td>'+startTimePart+'</td>';
              str += '<td>'+endTimePart+'</td>';
              str += '<td>'+adjustedDayWorkTime +'hr</td>';
              str += '<td> 기본 '+adjustedDayWorkTime+ 'hr/' +'연장' +excessTime.toFixed(2) +'hr</td>';
              str += '<td>'+data[i].state+'</td>';
              str += "</tr>";
          }
          pagination.empty();
          tbody.html(str); // 최종적으로 업데이트된 문자열 적용
      },
      error: function(e) {
          console.error('Ajax error: ', e);
      }
  });
});



});
</script>