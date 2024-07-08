document.addEventListener('DOMContentLoaded', function() {
    var sidenav = document.getElementById("mySidenav");
    var main = document.getElementById("main");

    var currentPageInquiry = 1;
    var itemsPerPageInquiry = 5;

    function expandSidebar() {
        sidenav.style.width = "250px";
        main.style.marginLeft = "250px";
        sidenav.classList.add("open");
    }

    function collapseSidebar() {
        sidenav.style.width = "70px";
        main.style.marginLeft = "70px";
        sidenav.classList.remove("open");
    }

    sidenav.addEventListener('mouseover', expandSidebar);

    sidenav.addEventListener('mouseout', function() {
        if (!sidenav.matches(':hover') && sidenav.classList.contains("open")) {
            collapseSidebar();
        }
    });

    function displayPageInquiry(page) {
        var items = document.querySelectorAll('.inquiry-item');
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

    window.prevPageInquiry = function() {
        if (currentPageInquiry > 1) {
            currentPageInquiry--;
            displayPageInquiry(currentPageInquiry);
        }
    };

    window.nextPageInquiry = function() {
        var items = document.querySelectorAll('.inquiry-item');
        if (currentPageInquiry * itemsPerPageInquiry < items.length) {
            currentPageInquiry++;
            displayPageInquiry(currentPageInquiry);
        }
    };

    function fetchQuestions() {
        fetch('/admin/getQuestions')
            .then(response => response.json())
            .then(data => {
                var inquiryItemsContainer = document.querySelector('.inquiry-items');
                inquiryItemsContainer.innerHTML = '';
                data.forEach(question => {
                    var inquiryItem = document.createElement('div');
                    inquiryItem.classList.add('inquiry-item');
                    inquiryItem.innerHTML = `
                        <div><strong>작성자:</strong> ${question.writerNo}</div>
                        <div><strong>제목:</strong> ${question.title}</div>
                        <div><strong>내용:</strong> ${question.content}</div>
                        <div><strong>작성일시:</strong> ${question.writeDate}</div>
                    `;
                    inquiryItemsContainer.appendChild(inquiryItem);
                });
                displayPageInquiry(currentPageInquiry); // 페이지네이션 초기화
            })
            .catch(error => console.error('Error fetching questions:', error));
    }

    fetchQuestions(); // 페이지 로드 시 질문 데이터 가져오기
});
