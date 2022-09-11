package swlee.logiclist.domain;

import lombok.Data;

import java.util.Date;

@Data

public class Board {
    /**
     * id - 게시글번호
     * title - 제목
     * content - 내용
     * clickCnt - 조회수(조회수 업데이트 기능필요)
     * image - image Url(AWS S3연동 필요)
     * username - 등록자
     */
    private int  id;
    private String title;
    private String content;
    private int clickCnt;
    private String image;
    private String username;
    private Date date;

    public Board(int id, String title, String content, int clickCnt, String image, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.clickCnt = clickCnt;
        this.image = image;
        this.username = username;
    }
}
