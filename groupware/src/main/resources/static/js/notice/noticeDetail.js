// 게시글 상세조회
function getNoticeByNo(x){

  console.log("상세조회 함수 실행됨");
  const noticeNo = x.target.parentNode.children[0].innerText;

  $.ajax({
    url :  "/api/notice/detail",
    method : "get",
    data : {
      no : noticeNo,
    },
    success :(data) => {
      console.log("게시글 상세조회 통신성공");
      console.log(data);
  
      const h3Tag = document.createElement("h3");
      const textNode01 = document.createTextNode("글번호 : " + data.no);
      h3Tag.appendChild( textNode01 );
  
      const h3Tag02 = document.createElement("h3");
      const textNode02 = document.createTextNode("제목 : " + data.title);
      h3Tag02.appendChild( textNode02 );
  
      const preTag = document.createElement("pre");
      const textNode03 = document.createTextNode(data.content);
      preTag.appendChild( textNode03 );
  
      const result = document.querySelector("#result");
      result.innerHTML = "";
      result.appendChild(h3Tag);
      result.appendChild(h3Tag02);
      result.appendChild(preTag);
      
    },
    fail : () => {
      console.log("게시글 상세조회 통신실패");
    },
  
  });  
}