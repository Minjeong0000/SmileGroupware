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

//중요쪽지 list불러오기
$.ajax( {
  url: "http://127.0.0.1:8080/api/message/important" ,
  method: "get" ,
  success: (data) => {
    console.log("통신성공!");
    console.log(data);

    const x = document.querySelector("table > tbody");
    console.log(x);

    let str = "";
    for(let i = 0 ; i < data.length; ++i){
      str += "<tr>";
      str += `
      <td>
          <input type='checkbox' class='select-item' value='${data[i].messageNo}'>
      </td>
  `;
      str += "<td>" + data[i].senderName + "</td>";
      str += "<td>" + data[i].forderName + "</td>";
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



function readCheckedMessage(){

  const checkboxArr = document.querySelectorAll("table>tbody input[type=checkbox]");//전체 체크박스 가져오기
  let checkedValues = [];//체크된 값만 받을 새로운 배열 만들기
  for (let i = 0; i < checkboxArr.length; ++i) {
      if (checkboxArr[i].checked) { //체크==true
          checkedValues.push(checkboxArr[i].value);//새로운배열에 담기
      }
  }
  console.log("Checked values:", checkedValues);//새로운배열 담아졌는지 확인

  $.ajax({
      url: '/api/message/changeRead', // 요청을 보낼 URL
      type: 'PUT', // HTTP 요청 메소드
      contentType: 'application/json', // 보낼 데이터 형식,핸들러 매개변수앞에 @requestbody추가해야
      data: JSON.stringify(checkedValues), // 데이터를 JSON 문자열로 변환
      success: function(x) {
          console.log('Success:', x);
          
      },
      error: function(e) {
          console.log('Error:', e);
      }
  });

}

function deleteCheckedMessage(){

  const checkboxArr = document.querySelectorAll("table>tbody input[type=checkbox]");//전체 체크박스 가져오기
  let checkedValues = [];//체크된 값만 받을 새로운 배열 만들기
  for (let i = 0; i < checkboxArr.length; ++i) {
      if (checkboxArr[i].checked) { //체크==true
          checkedValues.push(checkboxArr[i].value);//새로운배열에 담기
      }
  }
  console.log("Checked values:", checkedValues);//새로운배열 담아졌는지 확인

  $.ajax({
      url: '/api/message/sendTrash', // 요청을 보낼 URL
      type: 'PUT', // HTTP 요청 메소드
      contentType: 'application/json', // 보낼 데이터 형식,핸들러 매개변수앞에 @requestbody추가해야
      data: JSON.stringify(checkedValues), // 데이터를 JSON 문자열로 변환
      success: function(x) {
          console.log('Success:', x);
      },
      error: function(e) {
          console.log('Error:', e);
      }
  });

}