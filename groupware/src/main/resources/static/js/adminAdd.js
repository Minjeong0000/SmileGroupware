document.addEventListener('DOMContentLoaded', function() {
  // 사이드바 및 메인 컨텐츠 영역 참조
  var sidenav = document.getElementById("mySidenav");
  var main = document.getElementById("main");
  var adminLevelInput = document.getElementById("adminLevel");
  var adminLevelDesc = document.getElementById("adminLevelDesc");

  // 마우스가 사이드바 위에 있을 때 열기
  sidenav.addEventListener('mouseover', function() {
      sidenav.style.width = "250px";
      main.style.marginLeft = "300px";
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

  // 관리자 권한 필드를 클릭하면 설명 표시
  adminLevelInput.addEventListener('focus', function() {
    adminLevelDesc.textContent =
      "권한 1 : 마스터 (모든 페이지를 열람하실 수 있습니다.)\n" +
      "권한 2 : 인사관리팀 (제한된 페이지를 제외하고 열람 가능)";
    adminLevelDesc.style.display = "block";
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
          main.style.marginLeft = "250px";
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
});
