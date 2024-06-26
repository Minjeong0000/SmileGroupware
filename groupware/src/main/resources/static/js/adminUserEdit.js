document.addEventListener('DOMContentLoaded', function() {
    var employeeContainer = document.getElementById('employee-info');
    var employees = [];

    var currentPage = 1;
    var itemsPerPage = 10;

    // Ajax를 통해 관리자 데이터를 가져와서 표시
    fetch('/admin/getAdmins')
        .then(response => response.json())
        .then(data => {
            employees = data;
            displayEmployees(currentPage);
        });

    function displayEmployees(page) {
        employeeContainer.innerHTML = '';
        var start = (page - 1) * itemsPerPage;
        var end = start + itemsPerPage;
        var paginatedEmployees = employees.slice(start, end);

        paginatedEmployees.forEach(function(employee) {
            var employeeElement = document.createElement('p');
            employeeElement.textContent = `${employee.adminNo} | ${employee.adminId} | ${employee.adminNick} | ${employee.adminEmail} | ${employee.adminLevel}`;
            employeeContainer.appendChild(employeeElement);
        });

        document.getElementById('prev').disabled = page === 1;
        document.getElementById('next').disabled = end >= employees.length;
    }

    window.nextPage = function() {
        currentPage++;
        displayEmployees(currentPage);
    };

    window.prevPage = function() {
        currentPage--;
        displayEmployees(currentPage);
    };

    // 새로운 관리자를 추가하는 함수
    document.getElementById('addAdminForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var adminData = {
            adminId: document.getElementById('adminId').value,
            adminPwd: document.getElementById('adminPwd').value,
            adminNick: document.getElementById('adminNick').value,
            adminEmail: document.getElementById('adminEmail').value,
            adminLevel: document.getElementById('adminLevel').value
        };

        fetch('/admin/userEdit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(adminData)
        })
        .then(response => {
            if (response.ok) {
                alert('관리자가 성공적으로 추가되었습니다.');
                location.reload(); // 페이지를 새로고침하여 업데이트된 내용을 표시
            } else {
                alert('관리자 추가에 실패했습니다.');
            }
        });
    });

    // 사이드 네비게이션 토글
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");

    sidenav.addEventListener('mouseover', function() {
        sidenav.style.width = "250px";
        main.style.marginLeft = "250px";
        sidenav.classList.add("open");
    });

    sidenav.addEventListener('mouseout', function() {
        if (!sidenav.matches(':hover') && sidenav.classList.contains("open")) {
            sidenav.style.width = "70px";
            main.style.marginLeft = "70px";
            sidenav.classList.remove("open");
        }
    });

    // 드롭다운 토글 기능 추가
    var dropdownToggles = document.querySelectorAll('.dropdown-toggle');
    dropdownToggles.forEach(function(toggle) {
        toggle.addEventListener('click', function(event) {
            event.preventDefault();
            var parent = this.parentElement;
            parent.classList.toggle('open');
        });
    });
});

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

function closeNav() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    if (sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        main.style.marginLeft = "70px";
        sidenav.classList.remove("open");
    }
}
