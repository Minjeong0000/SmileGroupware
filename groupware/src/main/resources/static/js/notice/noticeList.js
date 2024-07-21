//게시글 목록조회
function getNoticeList(){

}  

$.ajax({
    url :"/api/notice/list",
    method :"get",
    data : {},

    success : (data) => {
      console.log("공지사항 리스트 통신성공");
      console.log(data);
      const x = document.querySelector('tbody');
      console.log(x);

      let str = "";
      for(let i = 0 ; i < data.length; ++i ){
      str += "<tr>";
      str += "<td>" + data[i].no + "</td>"; 
      str += "<td>" + data[i].title + "</td>"; 
      str += "<td>" + data[i].writerNo + "</td>"; 
      str += "<td>" + data[i].writeDate + "</td>"; 
      str += "</tr>";
    }
    x.innerHTML= str;

      

    } ,
    fail: () => {
      console.log("통신실패");

    }, 

  });





  document.addEventListener("DOMContentLoaded", function() {
      const tbody = document.querySelector("#noticeBoard tbody");

      tbody.addEventListener("click", function(event) {
          const tr = event.target.closest('tr');
          if (tr) {
              const no = tr.querySelector('td').textContent; // 클릭한 행의 첫 번째 셀을 번호로 사용
              const h3Tag = document.querySelector("#result");
              
              $.ajax({
                  url: "/api/notice/detail", // 서버의 URL
                  method: "GET",
                  data: { no: no },
                  success: function(data) {
                      h3Tag.innerHTML = `<h3>${data.title}</h3>`; // 제목만 삽입
                      $('#summernote').summernote('code', data.content); // Summernote에 내용 삽입
                  },
                  error: function(xhr, status, error) {
                      alert("공지사항 조회에 실패했습니다.");
                  }
              });
          }
      });
  });


//   // 공지사항 목록조회 
// tbody.addEventListener( "click" , function(no){
//   const h3Tag = document.querySelector("#result");    
//   $.ajax( {
//       url: "/api/noice/detail" ,
//       method: "get" ,
//       data: {no : no} ,
//       success: function(vo){
        
//           h3Tag.innerHTML = vo.title;
//           h3Tag.innerHTML = vo.content;
          
//           h3Tag.innerHTML = $('#summernote').summernote("pasteHTML" , vo.content);

//       } 
//       }),
//     });








