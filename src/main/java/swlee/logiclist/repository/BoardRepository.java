package swlee.logiclist.repository;

import swlee.logiclist.domain.Board;

import java.util.List;

/**
 * save - 게시글 저장
 * update - 게시글 업데이트(Board 도메인 전체 가져옴)
 * findById - 게시글 번호(PK)로 게시글 찾음(유니크)
 * findByName - 게시글 내용,제목(미정)으로 게시글 찾음(다수)
 * delete - 게시글 번호(PK)로 게시글 삭제(삭제성공여부 리턴)
 */
public interface BoardRepository {
    public Board save(Board board);
    public Board update(Board board);
    public Board findById(int id);
    public List<Board> findByName(String keyword);

    public List<Board> findByOrder();
    public boolean delete(int id);
}
