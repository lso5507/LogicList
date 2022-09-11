package swlee.logiclist.service;

import swlee.logiclist.domain.Board;

import java.util.List;

public interface BoardService {
    public Board save(Board board);
    public Board update(Board board);
    public Board findById(int id);
    public List<Board> findByName(String keyword);
    public boolean delete(int id);
}

