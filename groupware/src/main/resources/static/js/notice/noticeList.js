//게시글 목록조회
  $.ajax({
    url :"/api/notice/list",
    method :"get",
    success : (data) => {
      console.log("공지사항 리스트 통신성공");
      console.log(data);
      const x = document.querySelector("table > tbody");
      console.log(x);

      let str = "";
      for(let i = 0 ; i < data.length; ++i ){
      str += "<tr>";
      str += "<td>" + data[i].no + "</td>"; 
      str += "<td>" + data[i].title + "</td>"; 
      str += "<td>" + data[i].writerNo + "</td>"; 
      str += "</tr>";
      }

      x.innerHTML= str;

    } ,
    fail: () => {
      console.log("통신실패");

    }, 

  });
