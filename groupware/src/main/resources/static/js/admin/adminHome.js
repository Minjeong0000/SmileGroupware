document.addEventListener('DOMContentLoaded', function() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");
    var companyBoard = document.getElementById("company-board");
    var notionBoard = document.getElementById("notion-board");
    var inquiryBoard = document.getElementById("inquiry-board");

    var currentPage = 1;
    var itemsPerPage = 4;

    var currentPageNotion = 1;
    var itemsPerPageNotion = 2;

    var currentPageInquiry = 1;
    var itemsPerPageInquiry = 8;

    function updateCurrentTime() {
        var now = new Date();
        var currentTime = now.toLocaleTimeString();
        document.querySelector('.current-time').textContent = currentTime;
    }

    function updateCurrentDate() {
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var date = now.getDate();
        var day = now.getDay();
        var weekDays = ['일', '월', '화', '수', '목', '금', '토'];
        var currentDate = `${year}년 ${month}월 ${date}일 (${weekDays[day]})`;
        document.querySelector('.date-time').textContent = currentDate;
    }

    function expandSidebar() {
        sidenav.style.width = "250px";
        main.style.left = "280px";
        companyBoard.style.left = "280px";
        notionBoard.style.left = "280px";
        inquiryBoard.style.right = "-145px"; /* 사이드바가 열릴 때 위치 변경 */
        sidenav.classList.add("open");
    }

    function collapseSidebar() {
        sidenav.style.width = "70px";
        main.style.left = "100px";
        companyBoard.style.left = "100px";
        notionBoard.style.left = "100px";
        inquiryBoard.style.right = "38px"; /* 사이드바가 닫힐 때 위치 변경 */
        sidenav.classList.remove("open");
    }

    sidenav.addEventListener('mouseover', expandSidebar);

    sidenav.addEventListener('mouseout', function() {
        if (!sidenav.matches(':hover')) {
            collapseSidebar();
        }
    });

    updateCurrentDate();
    updateCurrentTime();
    setInterval(updateCurrentTime, 1000);

    function displayPage(page) {
        var items = document.querySelectorAll('#company-board .board-item');
        items.forEach(function(item, index) {
            if (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });
        updatePaginationButtons(page, items.length, itemsPerPage, 'prev', 'next');
    }

    function displayPageNotion(page) {
        var items = document.querySelectorAll('#notion-board .notion-item');
        items.forEach(function(item, index) {
            if (index >= (page - 1) * itemsPerPageNotion && index < page * itemsPerPageNotion) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });
        updatePaginationButtons(page, items.length, itemsPerPageNotion, 'prev-notion', 'next-notion');
    }

    function displayPageInquiry(page) {
        var items = document.querySelectorAll('#inquiry-board .inquiry-item');
        items.forEach(function(item, index) {
            if (index >= (page - 1) * itemsPerPageInquiry && index < page * itemsPerPageInquiry) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });
        updatePaginationButtons(page, items.length, itemsPerPageInquiry, 'prev-inquiry', 'next-inquiry');
    }

    function updatePaginationButtons(page, totalItems, itemsPerPage, prevButtonId, nextButtonId) {
        var prevButton = document.getElementById(prevButtonId);
        var nextButton = document.getElementById(nextButtonId);

        if (page <= 1) {
            prevButton.disabled = true;
        } else {
            prevButton.disabled = false;
        }

        if (page * itemsPerPage >= totalItems) {
            nextButton.disabled = true;
        } else {
            nextButton.disabled = false;
        }
    }

    window.prevPage = function() {
        if (currentPage > 1) {
            currentPage--;
            displayPage(currentPage);
        }
    };

    window.nextPage = function() {
        var items = document.querySelectorAll('#company-board .board-item');
        if (currentPage * itemsPerPage < items.length) {
            currentPage++;
            displayPage(currentPage);
        }
    };

    window.prevPageNotion = function() {
        if (currentPageNotion > 1) {
            currentPageNotion--;
            displayPageNotion(currentPageNotion);
        }
    };

    window.nextPageNotion = function() {
        var items = document.querySelectorAll('#notion-board .notion-item');
        if (currentPageNotion * itemsPerPageNotion < items.length) {
            currentPageNotion++;
            displayPageNotion(currentPageNotion);
        }
    };

    window.prevPageInquiry = function() {
        if (currentPageInquiry > 1) {
            currentPageInquiry--;
            displayPageInquiry(currentPageInquiry);
        }
    };

    window.nextPageInquiry = function() {
        var items = document.querySelectorAll('#inquiry-board .inquiry-item');
        if (currentPageInquiry * itemsPerPageInquiry < items.length) {
            currentPageInquiry++;
            displayPageInquiry(currentPageInquiry);
        }
    };

    function fetchQuestions() {
        fetch('/question/inquiry')
            .then(response => response.json())
            .then(data => {
                var inquiryItemsContainer = document.querySelector('.inquiry-items');
                inquiryItemsContainer.innerHTML = '';
                data.forEach(question => {
                    var inquiryItem = document.createElement('div');
                    inquiryItem.classList.add('inquiry-item');
                    inquiryItem.innerHTML = `
                        <a href="#"><h3>제목 : ${question.title}</h3></a>
                        <span>게시일: ${question.writeDate}</span>
                        <span>글쓴이 : ${question.writerNo}</span>
                    `;
                    inquiryItemsContainer.appendChild(inquiryItem);
                });
                displayPageInquiry(currentPageInquiry); // 페이지네이션 초기화
            })
            .catch(error => console.error('Error fetching questions:', error));
    }

    displayPage(currentPage);
    displayPageNotion(currentPageNotion);
    displayPageInquiry(currentPageInquiry);

    collapseSidebar();
    fetchQuestions(); // 페이지 로드 시 질문 데이터 가져오기
});
