document.addEventListener("DOMContentLoaded", function() {
  const modal = document.getElementById("modal");
  const openModalBtn = document.getElementById("openModal");
  const closeModalBtn = document.querySelector(".close");
  const messageForm = document.getElementById("messageForm");

  openModalBtn.addEventListener("click", function() {
      modal.style.display = "block";
  });

  closeModalBtn.addEventListener("click", function() {
      modal.style.display = "none";
  });

  window.addEventListener("click", function(event) {
      if (event.target === modal) {
          modal.style.display = "none";
      }
  });

  messageForm.addEventListener("submit", function(event) {
      event.preventDefault();
      // 여기서 실제 쪽지 전송 로직을 추가하세요.
      alert("쪽지가 성공적으로 보내졌습니다.");
      modal.style.display = "none";
  });
});


//쪽지보내기 동적셀렉트
$(document).ready(function() {
  // 부서 목록 가져오기
  $.ajax({
      url: '/emp/departments',
      method: 'GET',
      success: function(departments) {
          var departmentSelect = $('#departments');
          departments.forEach(function(department) {
              departmentSelect.append('<option value="' + department.departmentNo + '">' + department.departmentName + '</option>');
          });
      }
  });

  // 부서 선택 시 사원 목록 가져오기
  $('#departments').change(function() {
      var departmentNo = $(this).val();
      if (departmentNo) {
          $.ajax({
              url: '/emp/departments/' + departmentNo + '/employees',
              method: 'GET',
              success: function(employees) {
                  var employeeSelect = $('#employees');
                  employeeSelect.empty();
                  employeeSelect.append('<option value="">사원을 선택하세요</option>');
                  employees.forEach(function(employee) {
                      employeeSelect.append('<option value="' + employee.empId + '">' + employee.empName + ' ' + employee.roleName + '</option>');
                  });
              }
          });
      }
  });

  // 메시지 보내기
  $('#sendMessageBtn').click(function() {
      var empId = $('#employees').val();
      var content = $('#message').val();
      
      if (empId && content) {
          $.ajax({
              url: '/api/message',
              method: 'POST',
              data: {
                  receiverNo: empId,
                  content: content
              },
              success: function(response) {
                  alert(response);
                  location.href="/message/received"              
                },
              error: function() {
                  alert('쪽지 전송 실패');
              }
          });
      } else {
          alert('사원과 쪽지 내용을 입력하세요');
      }
  });
});






//공통
document.addEventListener('DOMContentLoaded', function() {
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
  
  // 토글 버튼을 클릭할 때 사이드바 열고 닫기
  function toggleNav(event) {
    event.stopPropagation();
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    if (sidenav.classList.contains("open")) {
      sidenav.style.width = "70px";
      main.style.marginLeft = "70px"; // 여기만 수정
      sidenav.classList.remove("open");
    } else {
      sidenav.style.width = "250px";
      main.style.marginLeft = "250px"; // 여기만 수정
      sidenav.classList.add("open");
    }
  }
  
  // 닫기 함수
  function closeNav() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    if (sidenav.classList.contains("open")) {
      sidenav.style.width = "70px";
      main.style.marginLeft = "70px"; // 여기만 수정
      sidenav.classList.remove("open");
    }
  }
   // 전체 선택 체크박스의 상태 변경 이벤트
   document.getElementById('select-all').addEventListener('change', function() {
    var checkboxes = document.querySelectorAll('.select-item');
    for (var checkbox of checkboxes) {
      checkbox.checked = this.checked;
    }
  });
  
  // 개별 선택 체크박스의 상태 변경 이벤트
  var checkboxes = document.querySelectorAll('.select-item');
  for (var checkbox of checkboxes) {
    checkbox.addEventListener('change', function() {
      var selectAllCheckbox = document.getElementById('select-all');
      if (!this.checked) {
        selectAllCheckbox.checked = false;
      } else {
        var allChecked = true;
        for (var checkbox of checkboxes) {
          if (!checkbox.checked) {
            allChecked = false;
            break;
          }
        }
        selectAllCheckbox.checked = allChecked;
      }
    });
  }
  

  // 모든 체크박스 체크 해제
function uncheckAllCheckboxes() {
  const checkboxes = document.querySelectorAll("table tbody input[type='checkbox']");
  checkboxes.forEach((checkbox) => {
    checkbox.checked = false;
  });
}
