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
  var drawingDataUrl = canvas.toDataURL(); // Canvas 이미지를 데이터 URL로 변환
  // 여기서 drawingDataUrl을 서버로 전송하여 저장하는 로직을 추가하면 됩니다.
  console.log("그림 저장됨:", drawingDataUrl);
  modal.style.display = "none"; // 모달 닫기
}
</script>

</body>
</html>