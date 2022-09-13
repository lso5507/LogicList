package swlee.logiclist.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import swlee.logiclist.domain.User;

import javax.sql.DataSource;
import javax.validation.constraints.Null;

@Repository
@Slf4j
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
        User findUser=null;
        try {
            findUser = jdbcTemplate.queryForObject(sql, getRowMapper(), username);
        }catch(DataAccessException e){
            log.error("NotFoundUser :",e);
            return null;
        }catch (NullPointerException e){
            log.error("User_NullPointerException : ",e);
        }
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
