document.addEventListener('DOMContentLoaded', function() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var announcementBoard = document.getElementById("announcement-board");
    var approvalDocumentsBoard = document.getElementById("approval-documents-board");  // 결재 문서 게시판 추가

    // 시간 업데이트 함수
    function updateCurrentTime() {
        var now = new Date();
        var currentTime = now.toLocaleTimeString(); // 'HH:MM:SS' 형태의 로컬 시간을 반환
        document.querySelector('.current-time').textContent = currentTime;
    }

    // 사이드바 확장
    function expandSidebar() {
        sidenav.style.width = "250px";
        main.style.left = "280px";
        announcementBoard.style.left = "calc(300px + 320px + 40px)"; // 조정된 공지 게시판 위치
        approvalDocumentsBoard.style.left = "calc(300px + 320px + 40px)"; // 결재 문서 게시판 위치 조정
        sidenav.classList.add("open");
    }

    // 사이드바 축소
    function collapseSidebar() {
        sidenav.style.width = "70px";
        main.style.left = "100px";
        announcementBoard.style.left = "calc(120px + 320px + 40px)"; // 조정된 공지 게시판 위치
        approvalDocumentsBoard.style.left = "calc(120px + 320px + 40px)"; // 결재 문서 게시판 위치 조정
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

    // 페이지 로드 시 현재 시간 표시 및 매초마다 업데이트
    updateCurrentTime();
    setInterval(updateCurrentTime, 1000);
});

