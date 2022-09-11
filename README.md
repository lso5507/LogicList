# 로직리스트(투드리스트)

# 프로젝트 설명

1. 정리되지 않은(쓰레기 코드) 들을 검색하기 위한 로직리스트 블로그 개발
2. 당일 TodoList 확인을 할 수 있는 서비스 개발
    1. 전날 TodoList DataBase저장
3. 가입불가
    1. 나만 쓸거임==

# 개발환경

1. 언어 : Java (Spring Boot Framework) 
    1. JDK 17
    2. Spring Security
    3. Spring Web
    4. lombok
    5. 패스워드 암호화 - JBcypt
2. 빌드 - Gradle
3. DataBase - H2(개발환경), Postgresql(배포환경)
    1. DB Schema (
    
    [LogicList DB_SChema](https://www.notion.so/LogicList-DB_SChema-3c04897a535f4da39b2332cee625550d)
    
4. 서버 - AWS EC2
    1. 이미지 리소스 저장 - AWS S3
5. ERD - dbdiagram
