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

  // 토글 버튼을 클릭할 때 사이드바 열고 닫기
  function toggleNav(event) {
      event.stopPropagation();
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
      if (sidenav.classList.contains("open")) {
          sidenav.style.width = "70px";
          main.style.marginLeft = "70px";
          sidenav.classList.remove("open");
      }
  }

  // 현재 시각 표시 함수
  function updateTime() {
      const now = new Date();
      let hours = now.getHours();
      const minutes = String(now.getMinutes()).padStart(2, '0');
      const seconds = String(now.getSeconds()).padStart(2, '0');
      const ampm = hours >= 12 ? '오후' : '오전';
      hours = hours % 12;
      hours = hours ? hours : 12;
      const formattedHours = String(hours).padStart(2, '0');
      const currentTime = `${ampm} ${formattedHours}시 ${minutes}분 ${seconds}초`;
      document.getElementById('currentTime').textContent = currentTime;
  }

  // 날짜 포맷 함수
  function formatDate(date) {
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      const days = ['일', '월', '화', '수', '목', '금', '토'];
      const dayOfWeek = days[date.getDay()];
      return `${year}-${month}-${day}(${dayOfWeek})`;
  }

  // 시각 업데이트
  updateTime();
  setInterval(updateTime, 1000);

  // 오늘 날짜 가져오기
  const currentDate = new Date();
  const formattedDate = formatDate(currentDate);
  document.getElementById('current-date').textContent = formattedDate;
});
