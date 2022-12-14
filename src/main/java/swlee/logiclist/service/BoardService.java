package swlee.logiclist.service;

import swlee.logiclist.domain.Board;

import java.util.List;

public interface BoardService {
    public Board save(Board board);
    public Board update(Board board);
    public Board findById(int id);

    public int count(String keyword);
    public List<Board> findByName(String keyword,String pages);
    public  List<Board> findByOrder();
    public boolean delete(int id);
}

