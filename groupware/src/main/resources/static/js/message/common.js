document.addEventListener("DOMContentLoaded", function() {
  const modal = document.getElementById("modal");
  const openModalBtn = document.getElementById("openModal");
  const closeModalBtn = document.querySelector(".close");
  const messageForm = document.getElementById("messageForm");

  openModalBtn.addEventListener("click", function() {
      modal.style.display = "block";
  });

  closeModalBtn.addEventListener("click", function() {
      modal.style.display = "none";
  });

  window.addEventListener("click", function(event) {
      if (event.target === modal) {
          modal.style.display = "none";
      }
  });

  messageForm.addEventListener("submit", function(event) {
      event.preventDefault();
      // 여기서 실제 쪽지 전송 로직을 추가하세요.
      alert("쪽지가 성공적으로 보내졌습니다.");
      modal.style.display = "none";
  });
});
