document.addEventListener("DOMContentLoaded", function() {
  // 공통

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

  // 페이지 로드 시 초기 목록 조회
  loadBoardList(1); // 처음 페이지는 1로 설정

  // 검색 버튼 클릭 이벤트 처리
  $('#search-btn').click(function() {
    search(1); // 페이지 번호를 1로 초기화하여 검색
  });

  function search(pno) {
    const pagecontainer = $('.pagination');
    pagecontainer.html("");


    console.log('search function called with pno:', pno);
    const searchOption = document.querySelector("#searchOption").value;
    const keyword = document.querySelector("#keyword").value;
    const x = $('.board-row-wrapper');
    x.html("");

    let data = {
        pno: pno,
        keyword: keyword
    };
    if (searchOption === "title") {
        data.title = keyword;
    } else if (searchOption === "content") {
        data.content = keyword;
    } else if (searchOption === "titleContent") {
        data.titleContent = keyword;
    } else if (searchOption === "writerName") {
        data.writerName = keyword;
    }

    $.ajax({
        url: '/board/list/search',
        method: 'GET',
        data: data,
        success: function(response) {
            const boardVoList = response.boardVoList;
            const pvo = response.pvo;
            let str = "";
            for (let i = 0; i < boardVoList.length; ++i) {
                str += '<div class="board-row">';
                str += '<div class="board-column number">' + boardVoList[i].no + '</div>';
                str += `<div class="board-column title"><a href="/board/detail?no=${boardVoList[i].no}">${boardVoList[i].title} (${boardVoList[i].replyCount}) </a></div>`;
                str += '<div class="board-column name">' + boardVoList[i].writerName + '</div>';
                str += '<div class="board-column date">' + boardVoList[i].writeDate + '</div>';
                str += '<div class="board-column views">' + boardVoList[i].hit + '</div>';
                str += '<div class="board-column likes">' + boardVoList[i].likeCount + '</div>';
                str += '</div>';
            }
            x.html(str);

            // 페이징바 업데이트
            const pagination = $(".search-pagination");
            let pageStr = "";

            // 이전 페이지 링크 생성
            if (pvo.currentPage > 1) {
                pageStr += '<a href="#" data-pno="1">First</a>';
                pageStr += '<a href="#" data-pno="' + (pvo.currentPage - 1) + '">Previous</a>';
            }
            // 페이지 번호 링크 생성
            for (let i = pvo.startPage; i <= pvo.endPage; i++) {
                if (i === pvo.currentPage) {
                    pageStr += '<strong>' + i + '</strong>'; // 현재 페이지는 굵게 표시
                } else {
                    pageStr += '<a href="#" data-pno="' + i + '">' + i + '</a>';
                }
            }
            // 다음 페이지 링크 생성
            if (pvo.currentPage < pvo.maxPage) {
                pageStr += '<a href="#" data-pno="' + (pvo.currentPage + 1) + '">Next</a>';
                pageStr += '<a href="#" data-pno="' + pvo.maxPage + '">Last</a>';
            }

            pagination.html(pageStr); // 페이징바 업데이트

            // 페이징바 클릭 이벤트 핸들러 등록
            $(".pagination a").click(function(e) {
                e.preventDefault(); // 기본 동작 방지
                let pno = $(this).data("pno"); // 클릭한 링크의 페이지 번호 추출
                search(pno); // 해당 페이지 번호로 다시 목록 로드
            });
        },
        error: function(error) {
            console.error('Error searching board list:', error);
        }
    });
  }

  // 검색 페이징 링크 클릭 시 페이지 변경
  $(document).on('click', '.search-pagination a', function(event) {
    event.preventDefault();
    var pageNo = $(this).attr('data-pno'); // 클릭한 링크의 페이지 번호 가져오기
    search(pageNo); // 해당 페이지의 목록을 로드
  });

  // 전체게시글 페이징 링크 클릭 시 페이지 변경
  $(document).on('click', '.pagination a', function(event) {
      event.preventDefault();
      var pageNo = $(this).attr('data-pno'); // 클릭한 링크의 페이지 번호 가져오기
      loadBoardList(pageNo); // 해당 페이지의 목록을 로드
  });

  // AJAX를 통해 게시판 목록 조회하는 함수
  function loadBoardList(pno) {
    const pagecontainer2 = $('.search-pagination');
    pagecontainer2.html("");

      $.ajax({
          url: '/board/list/data',
          method: 'GET',
          data:{
            pno:pno
          },
          success: function(response) {
              const boardVoList = response.boardVoList;
              const pvo = response.pvo;
              const x = $('.board-row-wrapper');
              let str ="";
              for(let i = 0; i < boardVoList.length; ++i){
                console.log(boardVoList[i].no);
                str += '<div class="board-row">';
                str += '<div class="board-column number">' + boardVoList[i].no + '</div>';
                str += `<div class="board-column title"><a href="/board/detail?no=${boardVoList[i].no}">${boardVoList[i].title} (${boardVoList[i].replyCount}) </a></div>`;
                str += '<div class="board-column name">' + boardVoList[i].writerName + '</div>';
                str += '<div class="board-column date">' + boardVoList[i].writeDate + '</div>';
                str += '<div class="board-column views">' + boardVoList[i].hit + '</div>';
                str += '<div class="board-column likes">' + boardVoList[i].likeCount + '</div>';
                str += '</div>';
              }
              x.html(str);

          // 페이징바 업데이트
          const pagination = $(".pagination");
          let pageStr = "";

          // 이전 페이지 링크 생성
          if (pvo.currentPage > 1) {
              pageStr += '<a href="#" data-pno="1">First</a>';
              pageStr += '<a href="#" data-pno="' + (pvo.currentPage - 1) + '">Previous</a>';
          }
          // 페이지 번호 링크 생성
          for (let i = pvo.startPage; i <= pvo.endPage; i++) {
              if (i === pvo.currentPage) {
                  pageStr += '<strong>' + i + '</strong>'; // 현재 페이지는 굵게 표시
              } else {
                  pageStr += '<a href="#" data-pno="' + i + '">' + i + '</a>';
              }
          }
          // 다음 페이지 링크 생성
          if (pvo.currentPage < pvo.maxPage) {
              pageStr += '<a href="#" data-pno="' + (pvo.currentPage + 1) + '">Next</a>';
              pageStr += '<a href="#" data-pno="' + pvo.maxPage + '">Last</a>';
          }

          pagination.html(pageStr); // 페이징바 업데이트
          // 페이징바 클릭 이벤트 핸들러 등록
          $(".pagination a").click(function (e) {
              e.preventDefault(); // 기본 동작 방지
              let pno = $(this).data("pno"); // 클릭한 링크의 페이지 번호 추출
              loadBoardList(pno); // 해당 페이지 번호로 다시 목록 로드
          });
          },
          error: function(error) {
              console.error('Error loading board list:', error);
          }
      });
  }
  // 페이지 로드 시 초기 목록 조회
  loadBoardList(1);
});
