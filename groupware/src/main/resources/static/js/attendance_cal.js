document.addEventListener('DOMContentLoaded', function() {
  var sidenav = document.getElementById("mySidenav");
  var main = document.getElementById("main");

  // 마우스가 사이드바 위에 있을 때 열기
  sidenav.addEventListener('mouseover', function() {
    sidenav.style.width = "250px";
    main.style.marginLeft = "280px";
    sidenav.classList.add("open");
  });

  // 마우스가 사이드바에서 벗어날 때 닫기
  sidenav.addEventListener('mouseout', function() {
    if (!sidenav.matches(':hover') && sidenav.classList.contains("open")) {
      sidenav.style.width = "70px";
      main.style.marginLeft = "70px";
      sidenav.classList.remove("open");
    }
  });
});

// 토글 버튼을 클릭할 때 사이드바 열고 닫기
function toggleNav(event) {
  event.stopPropagation();
  var sidenav = document.getElementById("mySidenav");
  var main = document.getElementById("main");
  if (sidenav.classList.contains("open")) {
    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";
    sidenav.classList.remove("open");
  } else {
    sidenav.style.width = "250px";
    main.style.marginLeft = "280px";
    sidenav.classList.add("open");
  }
}

// 닫기 함수
function closeNav() {
  var sidenav = document.getElementById("mySidenav");
  var main = document.getElementById("main");
  if (sidenav.classList.contains("open")) {
    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";
    sidenav.classList.remove("open");
  }
}
//현재시각표시하기
// script.js 파일에 작성
function updateTime() {
  // 현재 시각을 가져옴
  const now = new Date();

  // 시, 분, 초를 가져옴
  let hours = now.getHours();
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  // 오전/오후 구분
  const ampm = hours >= 12 ? '오후' : '오전';

  // 12시간 형식으로 변환
  hours = hours % 12;
  hours = hours ? hours : 12; // 0이면 12로 변환
  const formattedHours = String(hours).padStart(2, '0');

  // 시각 문자열 생성
  const currentTime = `${ampm} ${formattedHours}시 ${minutes}분 ${seconds}초`;

  // HTML 요소에 시각을 표시
  document.getElementById('currentTime').textContent = currentTime;
}

// 시각 업데이트
updateTime();

// 매 초마다 시각을 업데이트
setInterval(updateTime, 1000);

//오늘 날짜 가져오기
document.addEventListener('DOMContentLoaded', (event) => {
  function formatDate(date) {
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더해줍니다.
      const day = ('0' + date.getDate()).slice(-2);

      // 요일 배열 생성
      const days = ['일', '월', '화', '수', '목', '금', '토'];
      const dayOfWeek = days[date.getDay()]; // getDay()는 요일을 0(일요일)부터 6(토요일)까지 반환합니다.

      return `${year}-${month}-${day}(${dayOfWeek})`;
  }

  const currentDate = new Date();
  const formattedDate = formatDate(currentDate);

  document.getElementById('current-date').textContent = formattedDate;
});

