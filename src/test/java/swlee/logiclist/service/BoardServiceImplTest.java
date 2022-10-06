package swlee.logiclist.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swlee.logiclist.domain.Board;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Slf4j
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;
    private final int Board_A = 0;
    Board board=null;
    //테스트 후 삭제
    @AfterEach
    void after()  {
        boardService.delete(Board_A);
    }
    @BeforeEach
    void before() {
         board = new Board(Board_A, "title", "content", 0, "image", "root");
    }
    @Test
    void save() {
        boardService.save(board);
    }

    @Test
    void update_test(){
        String content="![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/258dab66-f537-42f3-aec6-f1e79a5c44f0474db10e-10e4-433a-a374-83b03a1d0dd5.bmp)\n" +
                "test입니다\n";
        String newContent ="![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/258dab66-f537-42f3-aec6-f1e79a5c44f0474db22210e-10e4-433a-a374-83b03a1d0dd5.bmp)\n" +
                "tes22t입니다\n";
        //URL만 긁어오기
        String[] split = content.split("https://logiclist.s3.ap-northeast-2.amazonaws.com/image/");
        //split for문
        for (String s : split) {
            log.info("s = {}",s);
        }

    }
    @Test
    void update() {
        boardService.save(board);
        final String title = "UpdateTitle";
        board.setTitle(title);
        Board updateBoard = boardService.update(board);
        assertThat(updateBoard.getTitle()).isEqualTo(title);


    }

    //테스트 코드 수정 필요 board ID(PK)값을 미리 가져올수 없음.
    void findById() {
        boardService.save(board);
        Board findBoard = boardService.findById(Board_A);
        log.info("========findBoard :: {} ==========",findBoard.toString());
        assertThat(findBoard).isNotNull();

    }

    @Test
    void findByName() {
        boardService.save(board);
        List<Board> title = boardService.findByName("제목");
//        assertThat(title.get(0).getTitle()).isEqualTo("title");
        StringBuilder sb = new StringBuilder();
        for (Board ele : title) {
            sb.append(ele.toString()+"\n");
        }
        log.info("FindByName={}",sb.toString());
    }
    @Test
    void findByOrder(){
        boardService.save(board);
        List<Board> title = boardService.findByOrder();
        assertThat(title).isNotNull();
        StringBuilder sb = new StringBuilder();
        for (Board ele : title) {
            sb.append(ele.toString()+"\n");
        }
        log.info("FindByOrder={}",sb.toString());
    }

    @Test
    void delete() {
    }
    @Test
    void imageFilter(){
        /**
         * 전달받은 AWS S3 이미지 ![] 문자를 이용해 배열로 저장 후 피렅링
         **/
        String oldData = "![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/96844d74-08ed-451d-a19f-c4119d650980ae51cecf-5ee5-4c77-a0a3-99638222263e6d22.png)![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/a935cce8-0f4c-4982-8b5e-0e3e49026c4a299a3e03-d58b-4939-ba9f-c3f9819f5c29.png)qwe";
        String data = "![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/96844d74-08ed-451d-a19f-c4119d650980ae51cecf-5ee5-4c77-a0a3-9963863e6d22.png)ㅂㅈㄷㅂㅈㄷㅈ![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/a935cce8-0f4c-4982-8b5e-0e3e49026c4a299a3e03-d58b-4939-ba9f-c3f9819f5c29.png)qwe";
        String[] split = data.split("!\\[]");

        for (String s : split) {
//            System.out.println(s.indexOf("("));
            System.out.println(s);
//            System.out.println("data = " + s.substring(s.indexOf("("),s.indexOf(")")));
        }
    }
}