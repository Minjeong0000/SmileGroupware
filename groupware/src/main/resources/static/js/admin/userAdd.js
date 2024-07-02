document.addEventListener('DOMContentLoaded', function() {
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

  fetch('/registerEmployee', {
      method: 'POST',
      body: JSON.stringify(Object.fromEntries(formData)),
      headers: {
          'Content-Type': 'application/json'
      }
  }).then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('회원이 성공적으로 등록되었습니다.');
        } else {
            alert('회원 등록에 실패하였습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
}