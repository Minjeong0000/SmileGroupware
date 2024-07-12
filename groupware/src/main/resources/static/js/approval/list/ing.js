document.addEventListener("DOMContentLoaded", function() {
    // 모달 열기
    function openModal(approvalNo) {
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
        $.ajax({
            type: "POST",
            url: "/approval/api/ing",
            contentType: "application/json",
            data: JSON.stringify({ approvalNo: approvalNo }),
            success: function(data) {
                // 서버에서 받은 데이터를 모달에 표시
                var approvalLines = data.appAlListVoList;
                var tableBody = document.getElementById("approvalLines");
                tableBody.innerHTML = ''; // 기존 내용을 지웁니다.

                for (var i = 0; i < approvalLines.length; i++) {
                    var row = `<tr>
                        <th>결재선${i+1}</th>
                        <td>${approvalLines[i].empName}</td>
                        <td>${approvalLines[i].stName}</td>
                        <td>`;

                    // stNo가 1일 때만 버튼을 추가
                    if (approvalLines[i].stNo === '1') {
                        row += `<button onclick="openModal_2('${approvalLines[i].empNo}', '${approvalLines[i].empName}')">메세지보내기</button>`;
                    }

                    row += `</td>
                    </tr>`;

                    tableBody.innerHTML += row;
                }
            },
            error: function(error) {
                console.error('Error:', error);
                var tableBody = document.getElementById("approvalLines");
                tableBody.innerHTML = '<tr><td colspan="4">Error loading data</td></tr>';
            }
        });
    }

    // 모달 닫기
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        var modal = document.getElementById("myModal");
        modal.style.display = "none";
    }

    // 모달 2 열기
    function openModal_2(empNo, empName) {
        var modal = document.getElementById("modal_2");
        modal.style.display = "block";


        var empNameInput = document.getElementById('empName');
        empNameInput.setAttribute('value', empName);

        var empNameInput = document.getElementById('empNo');
        empNameInput.setAttribute('value', empNo);

        $('#sendMessageBtn').click(function() {
            var empNo = $('#empNo').val();  // empNo 가져오기
            var message = $('#message').val();  // 메시지 가져오기

            // JSON 데이터 준비
            var dataToSend = {
                empNo: empNo,
                message: message
            };

            // AJAX 요청 보내기
            $.ajax({
                type: "POST",
                url: "/approval/api/sendMessage",  // 실제 API 엔드포인트로 변경해야 합니다
                contentType: "application/json",
                data: JSON.stringify(dataToSend),
                success: function(response) {
                    // 성공적으로 메시지를 보냈을 때의 처리
                    alert('메시지 전송 성공!');
                    // 화면 이동 등의 처리 (예: 다른 페이지로 이동)
                    window.location.href = "/approval/ing";  // 성공 페이지로 이동 예시
                },
                error: function(xhr, status, error) {
                    console.error('메시지 전송 실패:', error);
                    // 오류 처리를 원하면 추가하세요
                }
            });
        });



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
