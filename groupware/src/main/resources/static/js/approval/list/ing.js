document.addEventListener("DOMContentLoaded", function() {
    // 모달 열기
    function openModal(row) {
        var modal = document.getElementById("myModal");
        var modalText = document.getElementById("modalText");
        modal.style.display = "block";

        // 클릭한 행의 데이터를 모달에 표시
        // var cells = row.getElementsByTagName("td");
        // var text = "";
        // for (var i = 0; i < cells.length; i++) {
        //     text += cells[i].innerText + "\n";
        // }
        // text += cells[5].innerText
        // modalText.innerText = text;
    }

    // 모달 닫기
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        var modal = document.getElementById("myModal");
        modal.style.display = "none";
    }

    // 모달 2 열기
    function openModal_2() {
        var modal = document.getElementById("modal_2");
        modal.style.display = "block";
    }

    // 모달 2 닫기
    function closeModal_2() {
        var modal = document.getElementById("modal_2");
        modal.style.display = "none";
    }

    // 모달 2 닫기 버튼 처리
    var closeBtnModal2 = document.getElementsByClassName("close_2")[0];
    closeBtnModal2.onclick = function() {
        closeModal_2();
    }

    // openModal 함수를 전역 스코프에 추가
    window.openModal = openModal;
    window.openModal_2 = openModal_2;
    window.closeModal_2 = closeModal_2;
});
