document.addEventListener("DOMContentLoaded", function() {

  //공통
  
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
  
    // 마우스가 사이드바 위에 있을 때 열기
    sidenav.addEventListener('mouseover', function() {
      sidenav.style.width = "250px";
      main.style.marginLeft = "250px"; // 여기만 수정
      sidenav.classList.add("open");
    });
  
    // 마우스가 사이드바에서 벗어날 때 닫기
    sidenav.addEventListener('mouseout', function() {
      if (!sidenav.matches(':hover') && sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        main.style.marginLeft = "70px"; // 여기만 수정
        sidenav.classList.remove("open");
      }
    });
  
  
 
  
  });
  
  
  
  