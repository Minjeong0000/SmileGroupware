
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<link rel="stylesheet" type="text/css" href="/css/common/common.css">
<link rel="stylesheet" type="text/css" href="/css/notice/notice.css">
<script defer src="/js/common/common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<style>
  #titlebox {
    width: 100%;
    height: 40px;
    padding: 10px;
    border: 1px solid #8d9cf5;
    border-radius: 4px;
    font-size: 16px;
    box-sizing: border-box;
  }

  #titlebox:focus {
    outline: none;
    border-color: #6b78d1;
  }

  .content {
    margin-top: 20px;
  }

  input[type="submit"] {
    padding: 10px 20px;
    background-color: #8d9cf5;
    border: none;
    border-radius: 4px;
    color: #fff;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s;
  }

  input[type="submit"]:hover {
    background-color: #6b78d1;
  }
</style>
</head>
</style>
</head>
<body>
  <div id="mySidenav" class="sidenav">
    <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
    <a href="/home"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
    <a href="/event/personal/calendar"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
    <a href="/emp/attendance/cal"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
    <a href="/approval/home"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
    <a href="/message/received"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
    <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
</div>

    <div id="main" onclick="closeNav()">
        <div class="column">
            <h2>사내공지사항</h2>
        </div>
        <div class="column content">
            <center>
              
              <h2>사내 공지 게시판</h2>


            <form id="noticeForm" action="/api/notice" method="post">
              <input type="text" id="titlebox"  name="title" placeholder="제목을 입력하세요">
              <br />
              <textarea id="summernote" name="content" placeholder="내용을 입력하세요"></textarea>
              <br />
              <input type="submit" value="작성하기">
              <br />
            </form>

          </center>
            


        </div>
    </div>
</div>
</body>
</html>


<script>
  $('#summernote').summernote({
  placeholder: '내용을 입력하세요',
  tabsize: 2,
  width: '100%',
  height: 500,
  minHeight: 450,
  maxHeight: 600,
  toolbar: [
      ['style', ['style']],
      ['font', ['bold', 'underline', 'clear']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link', 'picture', 'video']],
      ['view', ['fullscreen', 'codeview', 'help']]
  ],
  callbacks: {
      onImageUpload : handleImgUpload ,
  } ,
  });

  function handleImgUpload( fileList ){
    console.log("handleImgUpload 호출됨");
      const fd = new FormData();

      fd.append( 'fileList' , fileList[0] );

      $.ajax( {
          url: "/api/notice/upload" ,
          method: "POST" ,
          data: fd ,
          processData: false,
          contentType: false,
          success: function(resp){
              console.log("handleImgUpload 성공 ~~~ !");
              console.log(resp);

              //썸머노트에 이미지 삽입
              $('#summernote').summernote('insertImage', resp);

          } ,
      } );
 
    }


  //공지사항 작성하기 
  document.querySelector('#noticeForm').addEventListener('submit', function(data) {
  // // 기본 폼 제출 방지
  event.preventDefault();

  // 입력 데이터 가져오기
  const title = document.querySelector('input[name="title"]').value;
  // const content = $('#summernote').summernote('code');
 
  const rawContent = $('#summernote').summernote('code');
  const content = $("<div>").html(rawContent).text(); // HTML 태그를 제거하고 순수 텍스트만 추출
  
  console.log("content:", content);


  // AJAX 요청
  $.ajax({
    url: "/api/notice", // 서버의 URL
    method: "post", // HTTP 메소드
    data: {
      
      title: title,  // 제목 데이터
      content: content,  // 내용 데이터
     
    
    },
    success: function(response) {
      // 성공 시 로직
      alert('공지가 성공적으로 등록되었습니다.');
      location.href = '/notice/list';
    },
    error: function(xhr, status, error) {
      // 실패 시 로직
      alert('공지 등록에 실패했습니다.');
    }
  });
});


</script>