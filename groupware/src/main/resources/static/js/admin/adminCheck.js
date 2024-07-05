document.addEventListener('DOMContentLoaded', function() {
    var adminContainer = document.getElementById('admin-info');
    var admins = JSON.parse(adminContainer.getAttribute('data-admins') || '[]');

    var currentPage = 1;
    var itemsPerPage = 9;

    function fetchAdmins() {
        $.ajax({
            url: '/admin/inquiry',
            type: 'GET',
            success: function(data) {
                admins = data;
                displayAdmins(currentPage);
            },
            error: function() {
                alert('관리자 정보를 가져오는 데 실패했습니다.');
            }
        });
    }

    function displayAdmins(page) {
        adminContainer.innerHTML = '';
        var start = (page - 1) * itemsPerPage;
        var end = start + itemsPerPage;
        var paginatedAdmins = admins.slice(start, end);

        paginatedAdmins.forEach(function(admin) {
            var adminElement = document.createElement('div');
            adminElement.innerHTML = `
                <span>${admin.adminNo}</span>
                <span>${admin.adminId}</span>
                <span>${admin.adminNick}</span>
                <span>${admin.adminEmail}</span>
                <span>${admin.adminLevel}</span>
                <button onclick="deleteAdmin(${admin.adminNo})">삭제</button>
            `;
            adminContainer.appendChild(adminElement);
        });

        document.getElementById('prev').disabled = page === 1;
        document.getElementById('next').disabled = end >= admins.length;
    }

    window.nextPage = function() {
        currentPage++;
        displayAdmins(currentPage);
    };

    window.prevPage = function() {
        currentPage--;
        displayAdmins(currentPage);
    };

    window.deleteAdmin = function(adminNo) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: `/admin/delete?num=${adminNo}`,
                type: 'DELETE',
                success: function(result) {
                    alert(result);
                    fetchAdmins();
                },
                error: function() {
                    alert('삭제 실패...');
                }
            });
        }
    };

    fetchAdmins();

    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");

    // 초기 상태 설정
    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";

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
    sidenav.style.width = "70px";
    main.style.marginLeft = "70px";
    sidenav.classList.remove("open");
}
