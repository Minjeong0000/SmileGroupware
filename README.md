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
