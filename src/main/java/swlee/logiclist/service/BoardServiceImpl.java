package swlee.logiclist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swlee.logiclist.domain.Board;
import swlee.logiclist.repository.BoardRepository;

import java.util.List;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);

    }

    @Override
    public Board update(Board board) {



        return  boardRepository.update(board);
    }
    @Override

    public  List<Board> findByOrder(){
        return boardRepository.findByOrder();
    }

    @Override
    public Board findById(int id) {
        return  boardRepository.findById(id);
    }

    @Override
    public List<Board> findByName(String keyword) {
        return  boardRepository.findByName(keyword);
    }

    @Override
    public boolean delete(int id) {
        return  boardRepository.delete(id);
    }
}

