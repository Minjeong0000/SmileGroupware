<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>그림 그리기 예시</title>
    <style>
        /* 모달 스타일 */
        .modal {
            display: none; /* 기본적으로 숨김 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4); /* 배경 어둡게 */
        }
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<!-- 버튼 -->
<button id="openModalBtn">그림 그리기 시작</button>

<!-- 모달 -->
<div id="myModal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <canvas id="myCanvas" width="600" height="400"></canvas>
    <input type="text" id="response" placeholder="답변을 입력하세요...">
    <button id="saveDrawingBtn">저장하기</button>
  </div>
</div>
<img src="data:image/png;base64,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" >

<script>
// 모달 관련 스크립트
var modal = document.getElementById('myModal');
var btn = document.getElementById("openModalBtn");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
  modal.style.display = "block";
}

span.onclick = function() {
  modal.style.display = "none";
}

// 그림 그리기 관련 스크립트
// 그림 그리기 관련 스크립트
var canvas = document.getElementById('myCanvas');
var ctx = canvas.getContext('2d');
var painting = false;

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
  ctx.strokeStyle = '#000';

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
  var drawingDataUrl = canvas.toDataURL("image/png"); // Canvas 이미지를 데이터 URL로 변환
  // 여기서 drawingDataUrl을 서버로 전송하여 저장하는 로직을 추가하면 됩니다.
  console.log("그림 저장됨:", drawingDataUrl);
  modal.style.display = "none"; // 모달 닫기
}
</script>

</body>
</html>