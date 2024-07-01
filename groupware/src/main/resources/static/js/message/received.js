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


$.ajax( {
  url: "http://127.0.0.1:8080/api/message/list" ,
  method: "get" ,
  success: (data) => {
    console.log("통신성공!");
    console.log(data);

    const x = document.querySelector("table > tbody");
    console.log(x);

    let str = "";
    for(let i = 0 ; i < data.length; ++i){
      str += "<tr>";
      str += "<td> <input type='checkbox' class='select-item'></td>";
      str += "<td>" + data[i].senderName + "</td>";
      str += "<td>" + data[i].content + "</td>";
      str += "<td>" + data[i].sentAt + "</td>";
      str += "</tr>";
    }
    x.innerHTML = str;
    
  } ,
  fail: () => {
    console.log("통신실패...");
  } ,

} );

function deleteCheckedBoard(){

  const checkboxArr = document.querySelectorAll("table>tbody input[type=checkbox]")
  console.log("checkboxArr:",checkboxArr);

  let str = "";
  for(let i=0; i<checkboxArr.length; ++i){
    if(checkboxArr[i].checked == true){
      console.log(checkboxArr[i].value);
      str += "no="+checkboxArr[i].value;
      str += "&";
    }

  }

	location.href="http://127.0.0.1:8080/경로 ?" + str;



}