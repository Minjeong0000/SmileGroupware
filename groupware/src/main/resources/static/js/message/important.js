//중요쪽지 리스트 불러오기
$.ajax( {
  url: "http://127.0.0.1:8080/api/message/important" ,
  method: "get" ,
  success: (mapdata) => {
    console.log("통신성공!");
    console.log(mapdata);
//로그인 한 사원의 사번
const empId = mapdata.empId;
console.log(empId);
const data = mapdata.messageVoList;
console.log(data);
const x = document.querySelector("table > tbody");
    
    let str = "";
    for(let i = 0 ; i < data.length; ++i){
      let importantYn = (data[i].forderNo === '1')?'<i class="fa-solid fa-star"></i>':'<i class="fa-regular fa-star"></i>';
      let readStatus = (data[i].readYn === 'Y')?'<i class="fa-regular fa-envelope-open"></i>':'<i class="fa-solid fa-envelope"></i>';
      let status = (empId===data[i].receiverNo)?'[수신]':'[발신]'

      str += "<tr>";
      str += `
      <td>
          <input type='checkbox' class='select-item' value='${data[i].messageNo}'>
      </td>
  `;
      str += `<td class="important-td" data-message-no="${data[i].messageNo}" data-forder-no="${data[i].forderNo}">${importantYn}</td>`;
      str += `<td class="readYn-td" data-message-no="${data[i].messageNo}" data-readYn="${data[i].readYn}">${readStatus}</td>`;
      str += "<td>" + data[i].senderName + "</td>";
      str += `<td data-message-no="${data[i].messageNo}" class="message-content">${status} ${data[i].content}</td>`;
      str += "<td>" + data[i].sentAt + "</td>";
      str += "</tr>";
    }

    //테이블에값 채워주기
    x.innerHTML = str;


    //내용td에 클릭 이벤트 
  document.addEventListener('click', function(event) {
  if (event.target.classList.contains('message-content')) {
    const messageNo = event.target.getAttribute('data-message-no');
    fetchMessageDetail(messageNo);
  }
});

   // important-td 클릭 이벤트 추가
    document.querySelectorAll('.important-td').forEach((element) => {
      element.addEventListener('click', (event) => {
        const messageNo = event.currentTarget.getAttribute('data-message-no');
        const currentForderNo = event.currentTarget.getAttribute('data-forder-no');
        if (currentForderNo === '1') {//이미 중요쪽지면
          unbookmarkMessage(messageNo, event.currentTarget);//중요쪽지 해제 
        } else {
          bookmarkMessage(messageNo, event.currentTarget);
        }
      });
    });
    
  } ,
  error:function(x){
    console.log(x);
    alert(x.responseText);
    location.href="/login";
} ,

} );//리스트 불러오기 끝


//북마크설정
function bookmarkMessage(messageNo, element) {
  console.log('messageNo'+messageNo);
  // 아이콘 상태 즉시 업데이트
  element.setAttribute('data-forder-no', '1');
  element.innerHTML = '<i class="fa-solid fa-star"></i>';

  // 서버로 업데이트 요청
  $.ajax({
    url: `http://127.0.0.1:8080/api/message/bookmark`,
    method: 'PUT',
    contentType: 'application/json',
    data: messageNo,
    success: function(response) {
      console.log('북마크 설정 성공:', response);
    },
    error: function(error) {
      console.error('북마크 설정 실패:', error);
      // 실패 시 아이콘 상태 원래대로 복구
      element.setAttribute('data-forder-no', '3');
      element.innerHTML = '<i class="fa-regular fa-star"></i>';
    }
  });
}

// 북마크 해제 함수
function unbookmarkMessage(messageNo, element) {
  // 아이콘 상태 즉시 업데이트
  element.setAttribute('data-forder-no', '3');
  element.innerHTML = '<i class="fa-regular fa-star"></i>';

  // 서버로 업데이트 요청
  $.ajax({
    url: `http://127.0.0.1:8080/api/message/unbookmark`,
    method: 'PUT',
    contentType: 'application/json',
    data: messageNo,
    success: function(response) {
      console.log('북마크 해제 성공:', response);
    },
    error: function(error) {
      console.error('북마크 해제 실패:', error);
      // 실패 시 아이콘 상태 원래대로 복구
      element.setAttribute('data-forder-no', '1');
      element.innerHTML = '<i class="fa-solid fa-star"></i>';
    }
  });
}

//상세조회 ajax
function fetchMessageDetail(messageNo) {
  $.ajax({
    url: `http://127.0.0.1:8080/api/message/detail/${messageNo}`,
    method: 'GET',
    data: JSON.stringify(messageNo),
    success: function(data) {
      console.log('상세조회 성공 :'+data);
      document.getElementById('senderName').textContent = data.senderName;
      document.getElementById('receiverName').textContent = data.receiverName;
      document.getElementById('sendTime').textContent = data.sentAt;
      document.getElementById('msgContent').textContent = data.content;

      // 읽음 상태 업데이트
      const readYnTd = document.querySelector(`.readYn-td[data-message-no="${messageNo}"]`);
      if (readYnTd) {
        readYnTd.innerHTML = '<i class="fa-regular fa-envelope-open"></i>'; // 읽음 아이콘으로 변경
        readYnTd.setAttribute('data-readYn', 'Y'); // 데이터 속성 업데이트
      }
      //상세 모달 띄우기
      document.getElementById('msg-detail-modal').style.display = 'block';
      
    },
    error: function(error) {
      console.error('Error fetching message detail:', error);
    }
  });
}

document.querySelectorAll('.msg-detail-modal .close').forEach(function(closeBtn) {
  closeBtn.addEventListener('click', function() {
    document.getElementById('msg-detail-modal').style.display = 'none';

  });
});







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

//여러개 삭제
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
      success: function(result) {
        console.log('Success:' + result)+'개 휴지통처리';
        alert(result+'개의 쪽지를 휴지통으로 이동했습니다.');
        location.href="/message/important";
      },
      error: function(e) {
          console.log('Error:', e);
      }
  });

}

