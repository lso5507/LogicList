package swlee.logiclist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import swlee.logiclist.domain.User;

import javax.sql.DataSource;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final DataSource dataSource;
    public UserRepositoryImpl( DataSource dataSource) {
        this.dataSource=dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    public RowMapper<User> getRowMapper(){
        return (rs,rowNum)->{
            User user =new User(rs.getString("USERNAME"),rs.getString("password"));
            return user;
        };

    }
    @Override
    public User save(User user) {
         String sql = "INSERT INTO users(username,password)values(?,?)";
//        String sql = "insert into member(member_id,money)values(?,?)";

        jdbcTemplate.update(sql,user.getUsername(),user.getPassword());

        return user;
    }


    @Override
    public User findByName(String  username) {
        final String sql = "SELECT * FROM users WHERE username=?";
        User findUser = jdbcTemplate.queryForObject(sql, getRowMapper(), username);
        return findUser;

    }

    @Override
    public boolean delete(String username) {
        final String sql = "delete FROM users WHERE username=? ";
        int update = jdbcTemplate.update(sql, username);
        if(update>0){
            return true;
        }
        return false;
    }
}
