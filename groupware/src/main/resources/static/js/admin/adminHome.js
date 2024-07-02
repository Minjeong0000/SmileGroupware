document.addEventListener('DOMContentLoaded', function() {
  var sidenav = document.getElementById("mySidenav");
  var main = document.getElementById("main");
  var companyBoard = document.getElementById("company-board");
  var notionBoard = document.getElementById("notion-board");

  var currentPage = 1;
  var itemsPerPage = 4; // 전사 게시판의 아이템 갯수를 4개로 설정

  var currentPageNotion = 1;
  var itemsPerPageNotion = 2; // 공지사항의 아이템 갯수를 2개로 설정

  // 시간 업데이트 함수
  function updateCurrentTime() {
      var now = new Date();
      var currentTime = now.toLocaleTimeString(); // 'HH:MM:SS' 형태의 로컬 시간을 반환
      document.querySelector('.current-time').textContent = currentTime;
  }

  // 날짜 업데이트 함수
  function updateCurrentDate() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1;
      var date = now.getDate();
      var day = now.getDay();
      var weekDays = ['일', '월', '화', '수', '목', '금', '토'];
      var currentDate = `${year}년 ${month}월 ${date}일 (${weekDays[day]})`;
      document.querySelector('.date-time').textContent = currentDate;
  }

  // 사이드바 확장
  function expandSidebar() {
      sidenav.style.width = "250px";
      main.style.left = "280px";
      companyBoard.style.left = "280px";
      notionBoard.style.left = "280px";
      sidenav.classList.add("open");
  }

  // 사이드바 축소
  function collapseSidebar() {
      sidenav.style.width = "70px";
      main.style.left = "100px";
      companyBoard.style.left = "100px";
      notionBoard.style.left = "100px";
      sidenav.classList.remove("open");
  }

  // 마우스가 사이드바 위에 있을 때 열기
  sidenav.addEventListener('mouseover', expandSidebar);

  // 마우스가 사이드바에서 벗어날 때 닫기
  sidenav.addEventListener('mouseout', function() {
      if (!sidenav.matches(':hover')) {
          collapseSidebar();
      }
  });

  // 페이지 로드 시 현재 시간 및 날짜 표시 및 매초마다 업데이트
  updateCurrentDate();
  updateCurrentTime();
  setInterval(updateCurrentTime, 1000);

  // 전사게시판 페이징 기능
  function displayPage(page) {
      var items = document.querySelectorAll('.board-item');
      items.forEach(function(item, index) {
          if (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) {
              item.style.display = 'block';
          } else {
              item.style.display = 'none';
          }
      });
      updatePaginationButtons(page, items.length, itemsPerPage, 'prev-page', 'next-page');
  }

  // 공지사항 페이징 기능
  function displayPageNotion(page) {
      var items = document.querySelectorAll('.notion-item');
      items.forEach(function(item, index) {
          if (index >= (page - 1) * itemsPerPageNotion && index < page * itemsPerPageNotion) {
              item.style.display = 'block';
          } else {
              item.style.display = 'none';
          }
      });
      updatePaginationButtons(page, items.length, itemsPerPageNotion, 'prev-page-notion', 'next-page-notion');
  }

  // 페이지 버튼 상태 업데이트 함수
  function updatePaginationButtons(page, totalItems, itemsPerPage, prevButtonId, nextButtonId) {
      var prevButton = document.getElementById(prevButtonId);
      var nextButton = document.getElementById(nextButtonId);

      if (page <= 1) {
          prevButton.style.display = 'none';
      } else {
          prevButton.style.display = 'inline-block';
      }

      if (page * itemsPerPage >= totalItems) {
          nextButton.style.display = 'none';
      } else {
          nextButton.style.display = 'inline-block';
      }
  }

  // 전사게시판 이전 페이지 버튼 클릭 시
  document.getElementById('prev-page').addEventListener('click', function() {
      if (currentPage > 1) {
          currentPage--;
          displayPage(currentPage);
      }
  });

  // 전사게시판 다음 페이지 버튼 클릭 시
  document.getElementById('next-page').addEventListener('click', function() {
      var items = document.querySelectorAll('.board-item');
      if (currentPage * itemsPerPage < items.length) {
          currentPage++;
          displayPage(currentPage);
      }
  });

  // 공지사항 이전 페이지 버튼 클릭 시
  document.getElementById('prev-page-notion').addEventListener('click', function() {
      if (currentPageNotion > 1) {
          currentPageNotion--;
          displayPageNotion(currentPageNotion);
      }
  });

  // 공지사항 다음 페이지 버튼 클릭 시
  document.getElementById('next-page-notion').addEventListener('click', function() {
      var items = document.querySelectorAll('.notion-item');
      if (currentPageNotion * itemsPerPageNotion < items.length) {
          currentPageNotion++;
          displayPageNotion(currentPageNotion);
      }
  });

  // 초기 페이지 표시
  displayPage(currentPage);
  displayPageNotion(currentPageNotion);
});
