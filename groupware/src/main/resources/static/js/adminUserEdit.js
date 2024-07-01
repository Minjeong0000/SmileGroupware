document.addEventListener('DOMContentLoaded', function() {
    var employeeContainer = document.getElementById('employee-info');
    console.log("Employee container:", employeeContainer); // 2번 확인 로그 추가
    var employees = [];
    var currentPage = 1;
    var itemsPerPage = 10;

    if (employeeContainer) {
        // Fetch employee data using XMLHttpRequest
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/admin/getEmployees', true); // 경로를 수정했습니다.
        xhr.onload = function() {
            if (xhr.status >= 200 && xhr.status < 300) {
                employees = JSON.parse(xhr.responseText);
                console.log("Fetched employees data:", employees); // 3번 및 5번 확인 로그 추가

                // 배열이 아닌 데이터를 처리
                if (!Array.isArray(employees)) {
                    console.error('Fetched data is not an array.');
                    return;
                }

                displayEmployees(currentPage);
            } else {
                console.error('There has been a problem with your fetch operation:', xhr.statusText);
            }
        };
        xhr.onerror = function() {
            console.error('There has been a problem with your fetch operation:', xhr.statusText);
        };
        xhr.send();

        function displayEmployees(page) {
            employeeContainer.innerHTML = '';
            var start = (page - 1) * itemsPerPage;
            var end = start + itemsPerPage;

            if (!Array.isArray(employees)) {
                console.error('employees is not an array'); // 4번 확인 로그 추가
                return;
            }
            var paginatedEmployees = employees.slice(start, end);

            paginatedEmployees.forEach(function(employee) {
                var employeeElement = document.createElement('p');
                employeeElement.textContent = `${employee.empId} | ${employee.empName} | ${employee.email} | ${employee.phone} | ${employee.positionNo} | ${employee.roleNo}`;
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

        var sidenav = document.getElementById("mySidenav");
        var main = document.getElementById("main");

        sidenav.addEventListener('mouseenter', function() {
            sidenav.style.width = "250px";
            sidenav.classList.add("open");
        });

        sidenav.addEventListener('mouseleave', function() {
            sidenav.style.width = "70px";
            sidenav.classList.remove("open");
        });

        var dropdownToggles = document.querySelectorAll('.dropdown-toggle');
        dropdownToggles.forEach(function(toggle) {
            toggle.addEventListener('click', function(event) {
                event.preventDefault();
                var parent = this.parentElement;
                parent.classList.toggle('open');
            });
        });
    } else {
        console.error('Element with id "employee-info" not found.');
    }
});

function toggleNav(event) {
    event.stopPropagation();
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    if (sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        sidenav.classList.remove("open");
        main.style.marginLeft = "70px"; // 사이드바 닫을 때 main 이동
    } else {
        sidenav.style.width = "250px";
        sidenav.classList.add("open");
        main.style.marginLeft = "250px"; // 사이드바 열 때 main 이동
    }
}

function closeNav() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    if (sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        sidenav.classList.remove("open");
        main.style.marginLeft = "70px"; // 사이드바 닫을 때 main 이동
    }
}
