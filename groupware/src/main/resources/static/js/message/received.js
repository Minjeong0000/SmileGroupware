//메세지 리스트 불러오기
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
      let importantYn = (data[i].forderNo === '1')?'<i class="fa-solid fa-star"></i>':'<i class="fa-regular fa-star"></i>';
      let readStatus = (data[i].readYn === 'Y')?'<i class="fa-regular fa-envelope-open"></i>':'<i class="fa-solid fa-envelope"></i>';
      str += "<tr>";
      str += `
      <td>
          <input type='checkbox' class='select-item' value='${data[i].messageNo}'>
      </td>
  `;
      str += "<td>" +importantYn + "</td>";
      str += "<td>" +readStatus + "</td>";
      str += "<td>" + data[i].senderName + "</td>";
      str += `<td data-message-no="${data[i].messageNo}" class="message-content">${data[i].content}</td>`;
      str += "<td>" + data[i].sentAt + "</td>";
      str += "</tr>";
    }
    x.innerHTML = str;
    
  } ,
  error:function(x){
    console.log(x);
    alert(x.responseText);
    location.href="/emp/login";
} ,

} );

//내용 클릭하면 상세조회 함수 호출
document.addEventListener('click', function(event) {
  if (event.target.classList.contains('message-content')) {
    const messageNo = event.target.getAttribute('data-message-no');
    fetchMessageDetail(messageNo);
  }
});
//상세조회 ajax
function fetchMessageDetail(messageNo) {
  $.ajax({
    url: `http://127.0.0.1:8080/api/message/detail/${messageNo}`,
    method: 'GET',
    data: JSON.stringify(messageNo),
    success: function(data) {
      console.log('상세조회'+data);
      // document.getElementById('messageDetail').innerHTML = `
        

      //   <p>보낸이: ${data.senderName}</p>
      //   <p>내용: ${data.content}</p>
      //   <p>일시: ${data.sentAt}</p>
      // `;
      document.getElementById('senderName').textContent = data.senderName;
      document.getElementById('receiverName').textContent = data.receiverName;
      document.getElementById('sendTime').textContent = data.sentAt;
      document.getElementById('msgContent').textContent = data.content;


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
    location.href="/message/received";

  });
});




 //여러개읽음처리
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
      success: function(result) {
          console.log('Success:' + result)+'개 읽음처리';

          location.href="/message/received";

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
        location.href="/message/received";
      },
      error: function(e) {
          console.log('Error:', e);
      }
  });

}


