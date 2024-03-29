package swlee.logiclist.repository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import swlee.logiclist.domain.Board;
import swlee.logiclist.domain.PageMaker;
import swlee.logiclist.domain.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class BoardRepositoryImpl implements BoardRepository{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final DataSource dataSource;

    public BoardRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(Board board) {
        final String sql = "INSERT into board(title,content,image,click_cnt,username) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,board.getTitle(),board.getContent(),board.getImage(),
                board.getClickCnt(),board.getUsername());
        return board;
    }

    @Override
    public Board update(Board board) {
        final String sql = "update board set title=?,content=?,image=?,click_cnt=? where id=? ";
        jdbcTemplate.update(sql,board.getTitle(),board.getContent(),board.getImage(),
                board.getClickCnt(),board.getId());
        return board;

    }

    @Override
    public Board findById(int id) {
        final String sql = "SELECT * FROM board WHERE id=?";
        Board findBoard = jdbcTemplate.queryForObject(sql, getRowMapper(), id);
        return findBoard;

    }
    public RowMapper<Board> getRowMapper(){
        return (rs,rowNum)->{
            Board board =new Board(
                    rs.getInt("id"),rs.getString("title"),rs.getString("content"),
                    rs.getInt("click_cnt"),rs.getString("image"),rs.getString("username")
            );
            board.setDate(rs.getDate("created_at"));
            return board;
        };

    }
    @Override
    public List<Board> findByName(String keyword,String pages) {
        int page = Integer.parseInt(pages);
        int start = (page-1)*10;
        log.info("start : "+start);
        //postgres
        final String sql = "SELECT * FROM board WHERE title LIKE ? ORDER BY created_at ASC LIMIT 10 OFFSET ?";

        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper(), "%"+keyword+"%",start);
        log.info("findBoard : {}",findBoard);
        return findBoard;
//
//        final String sql = "SELECT * FROM board WHERE content LIKE ? OR title LIKE ?";
//        keyword='%'+keyword+'%';
//        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper(),keyword,keyword);
//        return findBoard;
    }
    public List<Board> findByName_win(String keyword,String pages) {
        int page = Integer.parseInt(pages);
        int start = (page-1)*10;
        log.info("start : "+start);
        //
        final String sql = "SELECT * FROM board WHERE title LIKE ? ORDER BY created_at ASC LIMIT ?,10";
        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper(), "%"+keyword+"%",start);
        log.info("findBoard : {}",findBoard);
        return findBoard;
//
//        final String sql = "SELECT * FROM board WHERE content LIKE ? OR title LIKE ?";
//        keyword='%'+keyword+'%';
//        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper(),keyword,keyword);
//        return findBoard;
    }
    //최신 게시글 5개 긁어오기
    @Override
    public List<Board> findByOrder() {
        final String sql = "SELECT * FROM BOARD order by created_at desc LIMIT 3";
        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper());
        return findBoard;
    }
    public List<Board> findByOrder_win() {
        final String sql = "SELECT * FROM BOARD order by created_at desc LIMIT 3";
        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper());
        return findBoard;
    }
    @Override
    public boolean delete(int id) {
        final String sql = "delete from board where id=?";
        int update = jdbcTemplate.update(sql, id);
        if(update>0){
            return true;
        }
        return false;

    }
    @Override
    public int count(String keyword){
        final String sql = "SELECT count(*) FROM board WHERE title LIKE ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%");
        log.info("title :{} count : {}",keyword,count);
        return count;
    }
}
