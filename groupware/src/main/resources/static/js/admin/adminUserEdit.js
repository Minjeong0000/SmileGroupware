document.addEventListener('DOMContentLoaded', function() {
    var employeeContainer = document.getElementById('employee-info');
    var employees = [];
    var currentPage = 1;
    var itemsPerPage = 10;

    if (employeeContainer) {
        $.ajax({
            url: '/admin/getEmployees',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                employees = data;
                displayEmployees(currentPage);
            },
            error: function() {
                console.error('데이터를 가져오는 중 문제가 발생했습니다');
            }
        });

        function displayEmployees(page) {
            employeeContainer.innerHTML = '';
            var start = (page - 1) * itemsPerPage;
            var end = start + itemsPerPage;
            var paginatedEmployees = employees.slice(start, end);

            paginatedEmployees.forEach(function(employee) {
                var employeeElement = document.createElement('div');
                employeeElement.className = 'employee-row';
                employeeElement.innerHTML = `
                    <span>${employee.empId}</span>
                    <span>${employee.empName}</span>
                    <span>${employee.email}</span>
                    <span>${employee.phone}</span>
                    <span>${employee.positionName}</span>
                    <span>${employee.roleName}</span>
                `;
                employeeElement.addEventListener('click', function() {
                    showUserDetail(employee.empId);
                });
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

        function showUserDetail(empId) {
            $.ajax({
                url: `/admin/getEmployeeDetail?empId=${empId}`,
                type: 'GET',
                dataType: 'json',
                success: function(employee) {
                    document.getElementById('empId').textContent = employee.empId;
                    document.getElementById('empName').textContent = employee.empName;
                    document.getElementById('email').textContent = employee.email;
                    document.getElementById('phone').textContent = employee.phone;
                    document.getElementById('positionName').textContent = employee.positionName;
                    document.getElementById('roleName').textContent = employee.roleName;
                    if (employee.profile) {
                        document.getElementById('profileImage').src = '/img/userProfile/' + employee.profile;
                    } else {
                        document.getElementById('profileImage').src = '/img/userProfile/default-profile.png';
                    }
                    document.getElementById('userDetailModal').style.display = "block";
                },
                error: function() {
                    console.error('사원 정보를 가져오는 중 문제가 발생했습니다');
                }
            });
        }

        var modal = document.getElementById('userDetailModal');
        var span = document.getElementsByClassName('close')[0];

        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        var deleteButton = document.getElementById('deleteButton');
        deleteButton.addEventListener('click', function() {
            var empId = document.getElementById('empId').textContent;

            if (confirm(empId + "번 사원을 삭제하시겠습니까?")) {
                $.ajax({
                    url: '/admin/employeeDelete',
                    type: 'DELETE',
                    data: JSON.stringify({ employeeNum: empId }),
                    contentType: 'application/json',
                    success: function(response) {
                        alert(response);
                        document.getElementById('userDetailModal').style.display = "none";
                        location.reload();
                    },
                    error: function() {
                        alert('삭제 중 오류가 발생했습니다.');
                    }
                });
            }
        });
    }

    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");

    sidenav.addEventListener('mouseenter', function() {
        sidenav.style.width = "250px";
        sidenav.classList.add("open");
        main.style.marginLeft = "250px";
    });

    sidenav.addEventListener('mouseleave', function() {
        sidenav.style.width = "70px";
        sidenav.classList.remove("open");
        main.style.marginLeft = "70px";
    });

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
        sidenav.classList.remove("open");
        main.style.marginLeft = "70px";
    } else {
        sidenav.style.width = "250px";
        sidenav.classList.add("open");
        main.style.marginLeft = "250px";
    }
}

function closeNav() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    if (sidenav.classList.contains("open")) {
        sidenav.style.width = "70px";
        sidenav.classList.remove("open");
        main.style.marginLeft = "70px";
    }
}
