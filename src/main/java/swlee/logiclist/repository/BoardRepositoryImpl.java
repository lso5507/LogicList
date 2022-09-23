package swlee.logiclist.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import swlee.logiclist.domain.Board;
import swlee.logiclist.domain.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
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
    public List<Board> findByName(String keyword) {
        final String sql = "SELECT * FROM board WHERE content=? OR title=?";
        List<Board> findBoard = jdbcTemplate.query(sql, getRowMapper(),keyword,keyword);
        return findBoard;
    }
    //최신 게시글 5개 긁어오기
    @Override
    public List<Board> findByOrder() {
        final String sql = "SELECT * FROM BOARD order by created_at desc LIMIT 5";
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
}
