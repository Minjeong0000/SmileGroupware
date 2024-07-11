$(document).ready(function() {
    loadDepartments();
    loadRoles();
    loadPositions();
});

function loadDepartments() {
    $.ajax({
        url: '/admin/api/departments',
        method: 'GET',
        success: function(data) {
            const departmentSelect = $('#department_no');
            data.forEach(department => {
                const option = $('<option></option>').val(department.departmentNo).text(department.departmentName);
                departmentSelect.append(option);
            });
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}

function loadRoles() {
    $.ajax({
        url: '/admin/api/roles',
        method: 'GET',
        success: function(data) {
            const roleSelect = $('#role_no');
            data.forEach(role => {
                const option = $('<option></option>').val(role.roleNo).text(role.roleName);
                roleSelect.append(option);
            });
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}

function loadPositions() {
    $.ajax({
        url: '/admin/api/positions',
        method: 'GET',
        success: function(data) {
            const positionSelect = $('#position_no');
            data.forEach(position => {
                const option = $('<option></option>').val(position.positionNo).text(position.positionName);
                positionSelect.append(option);
            });
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}

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

function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
        var output = document.getElementById('profileImage');
        output.src = reader.result;
        output.style.display = 'block';
    };
    reader.readAsDataURL(event.target.files[0]);
}

function registerEmployee() {
    var form = document.getElementById("registerForm");
    var formData = new FormData(form);

    $.ajax({
        url: '/admin/userAdd',
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
            if (data.success) {
                alert('회원이 성공적으로 등록되었습니다.');
            } else {
                alert('회원 등록에 실패하였습니다.');
            }
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}
