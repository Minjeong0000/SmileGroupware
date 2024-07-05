<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의사항</title>
    <link rel="stylesheet" type="text/css" href="/css/inquiry.css">
    <script defer src="/js/inquiry.js"></script>
</head>
<body>
    <div id="mySidenav" class="sidenav">
        <a href="#" onclick="toggleNav(event)"><span class="menu-icon">&#9776;</span><span class="link-text">메뉴</span></a>
        <a href="#"><span class="menu-icon">&#8962;</span><span class="link-text">홈</span></a>
        <a href="#"><span class="menu-icon">&#128736;</span><span class="link-text">일정관리</span></a>
        <a href="#"><span class="menu-icon">&#128100;</span><span class="link-text">근태관리</span></a>
        <a href="#"><span class="menu-icon">&#128203;</span><span class="link-text">결재</span></a>
        <a href="#"><span class="menu-icon">&#9742;</span><span class="link-text">연락처</span></a>
        <a href="#"><span class="menu-icon">&#128101;</span><span class="link-text">커뮤니티</span></a>
    </div>

    <div id="main" onclick="closeNav()">
        <div class="column" id="fixedColumn">
            <h2>문의 사항</h2>
            <ul class="inquiry-list">
                <li class="inquiry-item"><a href="editUserPosition.html">• 자주 묻는 질문</a></li>
                <li class="inquiry-item"><a href="editUserId.html">• 💬 1:1 문의</a></li>
            </ul>
        </div>
        <div class="column content">
            <h2 class="content-title">Q. 그룹웨어를 초기화하려면 어떻게 해야 하나요?</h2>
            <h3 class="content-subtitle">그룹웨어 초기화</h3>
            <p>A. 그룹웨어 초기화가 필요하실 경우 Help Center 내 1:1문의로 사유를 기재하여 초기화 요청 접수를 해주세요.</p>
            <div class="caution-box">
                <p><strong>Caution</strong></p>
                <p>단, 초기화 처리 이후 다시 무료로 그룹웨어를 신청해 사용하는 것은 불가하기 때문에 그룹웨어를 사용하지 않으실 경우에만 초기화 요청 접수를 해주세요.</p>
            </div>

            <br><br>

            <h2 class="content-title">Q. 서비스 견적서 요청은 어떻게 하나요?</h2>
            <h3 class="content-subtitle">서비스 견적서</h3>
            <p>A. 견적서 요청은 1:1 문의로 견적받고 싶은 인원 수 및 기타 문읫자항을 적어서 보내주시면 담당자가 확인 후, 견적을 보내드립니다.</p>

            <br><br>

            <h2 class="content-title">Q. 스마일 그룹웨어 접속 방법은 무엇인가요?</h2>
            <h3 class="content-subtitle">접속방법</h3>
            <p>A. 스마일 오피스는 별도의 설치 없이, 웹 브라우저 상에서 고객사 별 URL을 통해 접속하여 사용하시면 됩니다.</p>
            <div class="caution-box">
                <p><strong>Caution</strong></p>
                <p>현재 모바일 환경에서의 스마일 오피스는 개발중이니 모바일 앱은 아직 사용하실 수 없습니다.</p>
            </div>

            <br><br>

            <h2 class="content-title">Q. 사용자별 접속 계정 등록방법은 무엇인가요?</h2>
            <h3 class="content-subtitle">계정등록</h3>
            <p>A. 각 사용자는 사내 그룹웨어 관리자가 직접 등록하실 수 있습니다.</p>

            <br><br>

            <h2 class="content-title">Q. 사용자별 접속 계정 등록방법은 무엇인가요?</h2>
            <h3 class="content-subtitle">계정등록</h3>
            <p>A. 각 사용자는 사내 그룹웨어 관리자가 직접 등록하실 수 있습니다.</p>

            <br><br>

            <h2 class="content-title">Q. 로그인 상태를 유지할 수 있나요?</h2>
            <h3 class="content-subtitle">로그인 상태유지</h3>
            <p>A. 기본적으로 로그인 후 로그아웃을 하기 전이나 웹 페이지를 닫기 전까지는 로그인 상태를 유지할 수 있습니다.</p>

            <br><br>

            <h2 class="content-title">Q. 서비스 결제 방법은 어떤것들이 있나요?</h2>
            <h3 class="content-subtitle">결제 방법</h3>
            <p>A. 결제방법은 카드결제, 계좌이체, 무통장 입금 중 선택하실 수 있으며, 1개월/12개월/24개월 단위로 결제하실 수 있습니다.</p>
            <div class="caution-box">
                <p><strong>Caution</strong></p>
                <p>현재 스마일그룹웨어는 체험판으로 사용자 여러분께 무료로 제공해드리고 있습니다. 추후 유료화가 되면 위 결제방법을 이용해주시면 됩니다!</p>
            </div>


            <hr><hr><hr><hr><hr>

        </div>
    </div>
</body>
</html>
