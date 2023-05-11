# 프로젝트 설명
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Flso5507%2FLogicList.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Flso5507%2FLogicList?ref=badge_shield)


1. 정리되지 않은(쓰레기 코드)  검색하기 위한 로직리스트 블로그 개발
2. TODOLIST 기능 개발
    1. 당일 데이터는 싱글톤 메모리 변수에 저장 (memorysave)
    2. 전날 TodoList DataBase저장 (upload)
    3. 완료 및 삭제 버튼 생성 
        1. 완료 시 완료 콘텐츠로 이동 (upload)
        2. 삭제 시 DB 저장 안함
    4. 당일 완료 또는 삭제하지 않은 TODOLIST는 DB저장(upload)
    
    [TODOLIST Schema](https://www.notion.so/TODOLIST-Schema-8be7ef2d08a5436685408d5ad1192599)
    
3. 레이아웃
    1. Figma 사용

# 개발환경

1. 언어 : Java (Spring Boot Framework) 
    1. JDK 17
    2. Spring Security
    3. lombok
    4. 패스워드 암호화 - JBcypt
    5. 게시글 에디터 - Toast UI Editor(Markdown)
2. 빌드 - Gradle
3. DataBase - Postgresql
    1. DB Schema 
    
    [LogicList DB_SChema](https://www.notion.so/LogicList-DB_SChema-3c04897a535f4da39b2332cee625550d)
    
4. 서버 - AWS EC2
    1. 이미지 리소스 저장 - AWS S3
5. [ERD - dbdiagram](https://dbdiagram.io/home)

## 기능 설명(글자를 클릭해 관련 정보를 볼 수 있습니다.)

### Toast UI Editor Image URL 업로드

- [기존 Base64 DB저장이 아닌 URL 주소 저장 ( Base64는 DB저장 부적합)](https://dev-swlee.tistory.com/16)
- [AWS S3 사용](https://dev-swlee.tistory.com/17)
- **문제점**
    - [AWS S3 URL 업로드 후 게시글 수정 또는 이미지 삭제시 S3 버킷 내 Dump Image 해결](https://dev-swlee.tistory.com/18)

### TodoList 기능 구현

- **[프론트 소스 구현](https://dev-swlee.tistory.com/20)**
    - TodoList 생성버튼 클릭 시 Script Event
    - 일부 return 값 별 분기가 필요하여 async 함수 사용

- **[백엔드 소스 구현](https://dev-swlee.tistory.com/21)**
    - 등록 후 24시간이 경과된 컨텐츠는 삭제 후 Database에 저장
        - 추후 Todo 테이블과 분리하여 운용필요
    - 최대 5개의 TodoList 전달
- [**페이징 구현**](https://dev-swlee.tistory.com/22)


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Flso5507%2FLogicList.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Flso5507%2FLogicList?ref=badge_large)