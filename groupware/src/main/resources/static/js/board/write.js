document.addEventListener("DOMContentLoaded", function() {

  //공통
  
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
  
  
    $('#summernote').summernote({
      placeholder: '내용을 입력하세요.',
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
  
      function handleImgUpload(fileList) {
      const fd = new FormData();
  
      fd.append('fileList' , fileList[0]);
  
  
      $.ajax({
          url: "/board/upload",
          method: "POST",
          data: fd,
          processData: false,
          contentType: false,
        
          success: function(resp) {
              console.log("handleImgUpload 성공 ~~~ !");
              console.log(resp);
              $('#summernote').summernote('insertImage', resp);
          },
          error: function(err) {
              console.error("handleImgUpload 실패 ㅠㅠ");
              console.error(err);
          }
      });
  }



 
  
  });
  
  
  
  