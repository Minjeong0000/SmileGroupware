

//휴지통 수신/발신 구분해서 list 받아오기
$.ajax( {
  url: "http://127.0.0.1:8080/api/message/trashList" ,
  method: "get" ,
  success: (mapdata) => {
    console.log("통신성공!");
    console.log(mapdata);//map 에 담긴 값
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
        location.href="/emp/login";
    } ,
    
    } );








function deleteForever(){

  const checkboxArr = document.querySelectorAll("table>tbody input[type=checkbox]");//전체 체크박스 가져오기
  let checkedValues = [];//체크된 값만 받을 새로운 배열 만들기
  for (let i = 0; i < checkboxArr.length; ++i) {
      if (checkboxArr[i].checked) { //체크==true
          checkedValues.push(checkboxArr[i].value);//새로운배열에 담기
      }
  }
  console.log("Checked values:", checkedValues);//새로운배열 담아졌는지 확인

  $.ajax({
      url: '/api/message/delete', // 요청을 보낼 URL
      type: 'DELETE', // HTTP 요청 메소드
      contentType: 'application/json', // 보낼 데이터 형식,핸들러 매개변수앞에 @requestbody추가해야
      data: JSON.stringify(checkedValues), // 데이터를 JSON 문자열로 변환
      success: function(x) {
          console.log('Success:', x);
          alert(x+'개의 쪽지를 영구 삭제 했습니다.');
          location.href="/message/trash";
      },
      error: function(e) {
        
          console.log('Error:', e);
      }
  });

}

function restoreMessage(){

  const checkboxArr = document.querySelectorAll("table>tbody input[type=checkbox]");//전체 체크박스 가져오기
  let checkedValues = [];//체크된 값만 받을 새로운 배열 만들기
  for (let i = 0; i < checkboxArr.length; ++i) {
      if (checkboxArr[i].checked) { //체크==true
          checkedValues.push(checkboxArr[i].value);//새로운배열에 담기
      }
  }
  console.log("Checked values:", checkedValues);//새로운배열 담아졌는지 확인

  $.ajax({
      url: '/api/message/restore', // 요청을 보낼 URL
      type: 'PUT', // HTTP 요청 메소드
      contentType: 'application/json', // 보낼 데이터 형식,핸들러 매개변수앞에 @requestbody추가해야
      data: JSON.stringify(checkedValues), // 데이터를 JSON 문자열로 변환
      success: function(x) {
          console.log('Success:', x);
          alert(x+'개의 쪽지를 복구했습니다.');
          location.href="/message/trash";
      },
      error: function(e) {
        
          console.log('Error:', e);
      }
  });

}