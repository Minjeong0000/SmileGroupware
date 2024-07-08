// add.js
document.addEventListener('DOMContentLoaded', function() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var fixedColumn = document.getElementById("fixedColumn");

    // 초기 네비게이션 바 상태 설정
    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";
    fixedColumn.style.left = "70px";
    sidenav.classList.remove("open");

    // 마우스 오버 시 네비게이션 바 확장
    sidenav.addEventListener('mouseover', function() {
        sidenav.style.width = "250px";
        main.style.marginLeft = "250px";
        fixedColumn.style.left = "250px";
        sidenav.classList.add("open");
    });

    // 마우스 아웃 시 네비게이션 바 축소
    sidenav.addEventListener('mouseout', function() {
        if (!sidenav.matches(':hover') && sidenav.classList.contains("open")) {
            sidenav.style.width = "70px";
            main.style.marginLeft = "70px";
            fixedColumn.style.left = "70px";
            sidenav.classList.remove("open");
        }
    });

    // 폼 제출 이벤트 처리
    document.getElementById('inquiryForm').addEventListener('submit', function(e) {
        e.preventDefault();

        var formData = {
            writerNo: document.getElementById('author').dataset.id, // 작성자 ID
            title: document.getElementById('title').value,
            content: document.getElementById('content').value,
            boardPwd: 'password' // 실제 패스워드로 대체 필요
        };

        fetch('/question/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.text())
            .then(data => {
                if (data === 'success') {
                    alert('문의가 성공적으로 접수되었습니다.');
                    document.getElementById('inquiryForm').reset();
                } else {
                    alert('문의 접수에 실패하였습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('서버와의 통신 중 오류가 발생했습니다.');
            });
    });
});

// 네비게이션 바 닫기 함수 추가
function closeNav() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var fixedColumn = document.getElementById("fixedColumn");

    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";
    fixedColumn.style.left = "70px";
    sidenav.classList.remove("open");
}
