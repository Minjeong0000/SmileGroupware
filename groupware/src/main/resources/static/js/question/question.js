document.addEventListener('DOMContentLoaded', function() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var fixedColumn = document.getElementById("fixedColumn");

    // 사이드바의 초기 상태를 닫힌 상태로 설정
    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";
    fixedColumn.style.left = "70px";
    sidenav.classList.remove("open");

    // 마우스가 사이드바 위에 있을 때 열기
    sidenav.addEventListener('mouseover', function() {
        sidenav.style.width = "250px";
        main.style.marginLeft = "250px";
        fixedColumn.style.left = "250px";
        sidenav.classList.add("open");
    });

    // 마우스가 사이드바에서 벗어날 때 닫기
    sidenav.addEventListener('mouseout', function() {
        if (!sidenav.matches(':hover') && sidenav.classList.contains("open")) {
            sidenav.style.width = "70px";
            main.style.marginLeft = "70px";
            fixedColumn.style.left = "70px";
            sidenav.classList.remove("open");
        }
    });
});

// 토글 버튼을 클릭할 때 사이드바 열고 닫기
function toggleNav(event) {
    event.stopPropagation();
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var fixedColumn = document.getElementById("fixedColumn");
    if (sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        main.style.marginLeft = "70px";
        fixedColumn.style.left = "70px";
        sidenav.classList.remove("open");
    } else {
        sidenav.style.width = "250px";
        main.style.marginLeft = "250px";
        fixedColumn.style.left = "250px";
        sidenav.classList.add("open");
    }
}

// 닫기 함수
function closeNav() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var fixedColumn = document.getElementById("fixedColumn");
    if (sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        main.style.marginLeft = "70px";
        fixedColumn.style.left = "70px";
        sidenav.classList.remove("open");
    }
}
