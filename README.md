# SmileGroupware
## 프로젝트 소개
기존 그룹웨어 솔루션은 주로 중대형 기업을 타겟으로 제작되어 다양한 기능을 제공하지만, 이로 인해 비용이 많이 들기 때문에 중소형 기업에게는 부담이 됩니다. 이러한 단점을 보완하기 위해 절차와 기능을 간소화하여 스타트업과 중소기업도 손쉽게 사용할 수 있는 '스마일 그룹웨어'를 제작했습니다.

## 프로젝트 팀원 및 역할

| 팀원   | 역할       | 구현기능                                    |
|--------|------------|---------------------------------------------|
| 장민정 | 형상관리자 | 사원 로그인, 근태, 쪽지, 게시판                |
| 문태웅 | 조장       | 문의사항, 관리자|
| 김송희 | 일정관리자 | 일정관리, 공지사항 게시판           |
| 이용진 | DB관리자   |       결재                                      |


## 개발 기간
2024.06.11~2024.07.20

## 개발 환경
- IDE
  
  ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![SQLDeveloper](https://img.shields.io/badge/SQL%20Developer-FF6F00.svg?style=for-the-badge&logo=oracle&logoColor=white
)
- DBMS
  
   ![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

- WAS
  
  ![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)

- Front

   ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
  ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
  ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)


- Back
  
  ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

- FrameWork&Library
  
  ![Spring](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
  ![MyBatis](https://img.shields.io/badge/MyBatis-B4172C.svg?style=for-the-badge&logo=mybatis&logoColor=white)
  ![summernote](https://img.shields.io/badge/Summernote-f3f3f3.svg?style=for-the-badge&logo=summernote&logoColor=black
  )![fullcalendar](https://img.shields.io/badge/FullCalendar-007bff.svg?style=for-the-badge&logo=fullcalendar&logoColor=white
  )

- AWS
 
  ![AWS](https://img.shields.io/badge/AWS%20S3-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white
  )

- 협업&ECT

  ![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
  ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
  ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)



## ERD

![화면 캡처 2024-08-05 161008](https://github.com/user-attachments/assets/44f81fad-b541-434f-ac7b-c29b4f9b9a50)


## 발표 PPT
https://www.canva.com/design/DAGH-OSfIFc/-gAZNZdmyM0Z_e1RwR_cfA/view?utm_content=DAGH-OSfIFc&utm_campaign=designshare&utm_medium=link&utm_source=editor

# 담당 기능 목록

## 장민정

### 사원
- **사원 로그인**
- **사원 로그아웃**

### 근태
- **나의 전체 출퇴근기록, 근무시간 한눈에 보기 (달력)**
- **나의 출퇴근 기록 기간 선택 조회**
- **출근시간 기록** (이미 출근 상태면 중복 기록 방지)
- **퇴근시간 기록** (가장 마지막에 찍은 퇴근시간이 최종 퇴근시간)
- **미출근 상태에서 퇴근 기록 방지**
- **오늘의 출퇴근 기록 조회**

### 쪽지
- **받은 쪽지 조회**
- **보낸 쪽지 조회**
- **중요 쪽지 조회**
- **휴지통 쪽지 조회**
- **쪽지 상세 조회**
- **쪽지 발신**
- **수/발신 쪽지 휴지통으로 보내기**
- **휴지통에 속한 쪽지 영구 삭제**
- **휴지통에 속한 쪽지 일반 쪽지로 복원**
- **중요 쪽지 설정/설정 취소**
- **수신자가 읽었는지 확인 기능**

### 게시판
- **전체 게시글 조회**
- **게시글 작성** (AWS-S3 사용 다중 이미지 업로드)
- **게시글 삭제** (내가 쓴 게시글만)
- **게시글 수정**
- **게시글 추천/추천 취소**
- **게시글 조회수 증가** (내가 쓴 게시글이 아닌 경우에만)
- **게시글 검색** (제목, 내용, 제목+내용, 작성자)
- **게시글별 댓글 조회**
- **댓글 작성**
- **댓글 삭제** (내가 쓴 댓글만)


## 화면
- 근태(캘린더)

  ![근태기록](https://github.com/user-attachments/assets/f785cd72-15ed-4f5d-a837-4338a429bf28)
- 근태(리스트)
 ![23](https://github.com/user-attachments/assets/cf100ea8-c9c8-4b3c-b739-a3d9e84ceab5)
  
- 쪽지
 ![24](https://github.com/user-attachments/assets/65872916-18f5-4f23-a940-73c267eb1d0b)
 ![25](https://github.com/user-attachments/assets/4c17b50c-26ad-4bec-8aaa-07c79a274ed8)
 ![26](https://github.com/user-attachments/assets/6ee3ab3a-5e35-47d5-af15-a5dbe63be8a6)

 - 게시판
    ![27](https://github.com/user-attachments/assets/b2b0341f-2609-42ae-a91b-28c7b98db314)
    ![28](https://github.com/user-attachments/assets/b9d47473-1ad0-4bdf-a391-4a823c32c87f)
    ![29](https://github.com/user-attachments/assets/2bb4a1d8-bcf4-4545-b82a-e06527557ea2)

## 프로젝트 소감
지난 프로젝트에서 아쉬웠던 점을 교훈 삼아, 이번 프로젝트에서는 최대한 많은 기능을 구현하기보다는 우선순위를 정해 필수적인 기능을 최우선으로 구현했습니다. 그 후에 다음 기능들을 순차적으로 구현하는 방식으로 작업하니, 시간도 단축되고 체계적으로 작업할 수 있었습니다.</br>

 작업에 들어가기 전 그날의 목표를 설정하고, 이를 노션 팀스페이스에 공유했습니다. 이렇게 주 단위와 일 단위로 계획을 세분화하니, 앞으로 나아가야 할 방향이 명확해졌고 각자의 작업 현황을 한눈에 확인할 수 있었습니다. 덕분에 일정 조정도 용이해져 프로젝트가 보다 체계적으로 진행되었습니다.</br>

 특히 이번 프로젝트에서는 AJAX를 최대한 활용하여 클라이언트와 서버 간의 통신을 깊이 이해하는 데 중점을 두었습니다. AJAX를 사용한 CRUD 작업을 통해 비동기적으로 데이터를 처리하고, 사용자 경험을 향상시킬 수 있었습니다. 이 과정에서 서버와의 실시간 데이터 통신과 상태 관리의 중요성을 깨닫게 되었고, 앞으로의 프로젝트에서 더욱 효과적으로 활용할 수 있을 것이라는 자신감도 얻었습니다. 이러한 경험은 클라이언트-서버 간의 원활한 상호작용을 이해하는 데 큰 도움이 되었고, 개발 작업에서의 기초를 더욱 확고히 할 수 있었습니다.
