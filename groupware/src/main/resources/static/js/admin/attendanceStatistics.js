document.addEventListener("DOMContentLoaded", function() {
    const sidenav = document.getElementById("mySidenav");
    const modal = document.getElementById("detailsModal");
    const closeModal = document.getElementsByClassName("close")[0];

    // 사이드 네비게이션을 토글하는 함수
    function toggleNav(event) {
        event.preventDefault();
        sidenav.classList.toggle("open");
        if (sidenav.classList.contains("open")) {
            sidenav.style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        } else {
            sidenav.style.width = "70px";
            document.getElementById("main").style.marginLeft = "70px";
        }
    }

    // 사이드 네비게이션을 닫는 함수
    function closeNav() {
        if (sidenav.classList.contains("open")) {
            sidenav.classList.remove("open");
            sidenav.style.width = "70px";
            document.getElementById("main").style.marginLeft = "70px";
        }
    }

    // 사이드 네비게이션 외부 클릭 시 닫기
    document.addEventListener("click", function(event) {
        if (!sidenav.contains(event.target) && sidenav.classList.contains("open")) {
            sidenav.classList.remove("open");
            sidenav.style.width = "70px";
            document.getElementById("main").style.marginLeft = "70px";
        }
    });

    // 사이드 네비게이션에 마우스를 올리면 이벤트 발생
    sidenav.addEventListener("mouseenter", function() {
        sidenav.classList.add("open");
        sidenav.style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    });

    // 사이드 네비게이션에서 마우스를 내리면 이벤트 발생
    sidenav.addEventListener("mouseleave", function() {
        sidenav.classList.remove("open");
        sidenav.style.width = "70px";
        document.getElementById("main").style.marginLeft = "70px";
    });

    // 근태 통계를 가져와서 표시하는 함수
    $.ajax({
        url: '/admin/getAttendanceStatistics',
        method: 'GET',
        success: function(data) {
            var tableBody = $('#statisticsTable tbody');
            tableBody.empty();

            data.forEach(function(item) {
                var row = '<tr>' +
                          '<td>' + item.empId + '</td>' +
                          '<td>' + item.empName + '</td>' +
                          '<td>' + item.totalWorkTime + '</td>' +
                          '<td><button class="detailsBtn" data-empid="' + item.empId + '">Details</button></td>' +
                          '</tr>';
                tableBody.append(row);
            });

            // 상세보기 버튼 클릭 시 상세 정보를 가져와 표시하는 함수 호출
            $('.detailsBtn').click(function() {
                var empId = $(this).data('empid');
                fetchAttendanceDetails(empId);
            });
        },
        error: function() {
            alert('근태 통계 데이터를 가져오는 데 실패했습니다.');
        }
    });

    // 특정 사원의 근태 상세 정보를 가져와서 모달에 표시하는 함수
    function fetchAttendanceDetails(empId) {
        $.ajax({
            url: '/admin/getAttendanceDetails/' + empId,
            method: 'GET',
            success: function(data) {
                var tableBody = $('#detailsTable tbody');
                tableBody.empty();

                data.forEach(function(item) {
                    var row = '<tr>' +
                              '<td>' + item.date + '</td>' +
                              '<td>' + item.startTime + '</td>' +
                              '<td>' + item.endTime + '</td>' +
                              '<td>' + item.workTime + '</td>' +
                              '<td>' + item.state + '</td>' +
                              '</tr>';
                    tableBody.append(row);
                });

                modal.style.display = "block";
            },
            error: function() {
                alert('근태 상세 정보를 가져오는 데 실패했습니다.');
            }
        });
    }

    // 모달 닫기 버튼 클릭 시 모달을 닫는 함수
    closeModal.onclick = function() {
        modal.style.display = "none";
    }

    // 모달 외부 클릭 시 모달을 닫는 함수
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});
