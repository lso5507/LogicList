# 프로젝트 설명

1. 정리되지 않은(쓰레기 코드) 들을 검색하기 위한 로직리스트 블로그 개발
2. 당일 TodoList 확인을 할 수 있는 서비스 개발
   1. 당일 데이터는 싱글톤 메모리 변수에 저장 (memorysave)
   2. 전날 TodoList DataBase저장 (upload)
   3. 완료 및 삭제 버튼 생성
      1. 완료 시 완료 콘텐츠로 이동 (upload)
      2. 삭제 시 DB 저장 안함

   4. 당일 완료 또는 삭제하지 않은 TODOLIST는 DB저장(upload)

   [TODOLIST Schema](https://www.notion.so/TODOLIST-Schema-8be7ef2d08a5436685408d5ad1192599)

3. 가입불가
   1. 나만 쓸거임==
4. 피그마를 통한 레이아웃 구성
   1. htmlGenerator 플러그인으로 변환
      1. 완벽하지 않은 기능으로 추후 리팩토링 필요
         1. 모든 레이아웃이 position : absolute 후 top, left으로 설정

# 개발환경

1. 언어 : Java (Spring Boot Framework)
   1. JDK 17
   2. Spring Security
   3. Spring Web
   4. lombok
   5. 패스워드 암호화 - JBcypt
   6. 게시글 에디터 - Toast UI Editor

      [Toast Ui Editor를 이용한 이미지 이벤트](https://www.notion.so/Toast-Ui-Editor-cc8626157fba474cb8cb92d6b9a20614)

2. 빌드 - Gradle
3. DataBase - H2(개발환경), Postgresql(배포환경)
   1. DB Schema (

   [LogicList DB_SChema](https://www.notion.so/LogicList-DB_SChema-3c04897a535f4da39b2332cee625550d)

4. 서버 - AWS EC2
   1. 이미지 리소스 저장 - AWS S3
5. ERD - dbdiagram

[A Free Database Designer for Developers and Analysts](https://dbdiagram.io/)