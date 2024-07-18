// 전역 변수로 loginEmployeeVo 선언
var loginEmployeeVo = {
    empId: '' // 초기값은 필요에 따라 비워둘 수 있음
};

// 모달 요소들
var modal = document.getElementById("myModal");
var tempModal = document.getElementById("tempModal");
var canvasModal = document.getElementById("canvasModal");
var span = document.getElementsByClassName("close");

// 모달 내 요소들
var approvalNoText = document.getElementById("approvalNo");
var approverText = document.getElementById("approver");
var approvalTitleText = document.getElementById("approvalTitle");
var approvalContentText = document.getElementById("approvalContent");
var approvalDateText = document.getElementById("approvalDate");
var detailButton = document.getElementById("detailButton");

// 상세보기 모달 내 요소들
var detailApprovalNoText = document.getElementById("detailApprovalNo");
var empIdText = document.getElementById("empId"); // 상세보기 모달에서 직원 ID를 표시할 요소

// tr 클릭 시 모달을 여는 함수
function appDetail(approvalNo, approver, approvalTitle, approvalContent, approvalDate, empId) {
    approvalNoText.textContent = approvalNo;
    approverText.textContent = approver;
    approvalTitleText.textContent = approvalTitle;
    approvalContentText.textContent = approvalContent;
    approvalDateText.textContent = approvalDate;
    loginEmployeeVo.empId = empId; // loginEmployeeVo 객체의 empId 업데이트
    modal.style.display = "block";
}

// 닫기 버튼 클릭 시 모달 닫기
for (let i = 0; i < span.length; i++) {
    span[i].onclick = function() {
        modal.style.display = "none";
        tempModal.style.display = "none";
        canvasModal.style.display = "none";
    }
}

// 모달 외부 클릭 시 모달 닫기
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    } else if (event.target == tempModal) {
        tempModal.style.display = "none";
    } else if (event.target == canvasModal) {
        canvasModal.style.display = "none";
    }
}

// 상세보기 버튼 클릭 시 상세보기 모달 열기
$('#detailButton').click(function() {
    var approvalNo = $('#approvalNo').text(); // 예시로 approvalNo 가져오는 방법

    $.ajax({
        type: 'POST',
        url: '/approval/api/tempDetail', // 컨트롤러의 엔드포인트 경로
        contentType: "application/json", // 요청 형식 설정
        data: JSON.stringify({ approvalNo: approvalNo }), // 요청 데이터 설정 (approvalNo 변수로 변경)
        success: function(response) {
            // 성공적으로 데이터를 받아왔을 때 실행될 코드
            $('#detailApprovalNo').text(response.approvalNo);
            $('#empId').text(response.empId);
            $('#approver1').text(response.approver1);
            $('#approver2').text(response.approver2);
            $('#approver3').text(response.approver3);

            // 이미지 URL을 가져와서 이미지 태그에 할당
            $('#approver1Img').attr('src', response.approver1ImageUrl);
            $('#approver2Img').attr('src', response.approver2ImageUrl);
            $('#approver3Img').attr('src', response.approver3ImageUrl);

            // 나머지 데이터도 할당
            $('#leaveForm').text(response.leaveForm);
            $('#priority').text(response.priority);
            $('#startDate').text(response.startDate);
            $('#endDate').text(response.endDate);
            $('#usageCount').text(response.usageCount);
            $('#reason').text(response.reason);
            $('#approvalDateDetail').text(response.approvalDateDetail);
            $('#approvalLine1').text(response.approvalLine1);
            $('#approvalLine2').text(response.approvalLine2);
            $('#approvalLine3').text(response.approvalLine3);

            // 모달 열기
            $('#tempModal').css('display', 'block');
        },
        error: function(xhr, status, error) {
            console.error('에이잭스 에러남!:', status, error);
        }
    });
});









// 승인하기 버튼 클릭 시 canvas 모달 열기
var approveBtn = document.getElementById('approveButton');
approveBtn.onclick = function() {
    canvasModal.style.display = "block";
}

// 그림 그리기 관련 스크립트
var canvas = document.getElementById('myCanvas');
var ctx = canvas.getContext('2d');
var painting = false;

// Canvas 배경 색상 설정 (흰색 배경)
ctx.fillStyle = '#ffffff'; // 흰색
ctx.fillRect(0, 0, canvas.width, canvas.height);

canvas.addEventListener('mousedown', startPainting);
canvas.addEventListener('mouseup', stopPainting);
canvas.addEventListener('mousemove', draw);

function startPainting(e) {
    painting = true;
    draw(e);
}

function stopPainting() {
    painting = false;
    ctx.beginPath(); // 그리기를 멈출 때마다 새로운 경로 시작
}

function draw(e) {
    if (!painting) return;

    ctx.lineWidth = 5;
    ctx.lineCap = 'round';
    ctx.strokeStyle = '#000000'; // 검은색 선

    var x = e.clientX - canvas.offsetLeft;
    var y = e.clientY - canvas.offsetTop;

    ctx.lineTo(x, y);
    ctx.stroke();
    ctx.beginPath(); // 새로운 경로 시작
    ctx.moveTo(x, y);
}

// 저장 버튼 클릭 이벤트
var saveBtn = document.getElementById('saveDrawingBtn');
saveBtn.onclick = function() {
    // Canvas 이미지 데이터 가져오기
    var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
    var data = imageData.data;

    // 밝기 조절 (선택적으로 추가하신 경우)
    var brightnessAdjustment = 50; // 밝기 조절 값 (0 이상의 값)

    for (var i = 0; i < data.length; i += 4) {
        // 각 픽셀의 RGB 값에 밝기 조절 값을 더하기
        data[i] = Math.min(data[i] + brightnessAdjustment, 255); // Red
        data[i + 1] = Math.min(data[i + 1] + brightnessAdjustment, 255); // Green
        data[i + 2] = Math.min(data[i + 2] + brightnessAdjustment, 255); // Blue
    }

    // 밝게 조정된 이미지 데이터를 Canvas에 다시 그리기
    ctx.putImageData(imageData, 0, 0);

    //사진의 주소 설정
    

    // Canvas 이미지를 PNG 파일로 저장하기
    var downloadLink = document.createElement('a');
    downloadLink.download = 'yongjin.png';
    downloadLink.href = canvas.toDataURL('image/png');
    downloadLink.click();
}