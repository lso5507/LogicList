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
}