package swlee.logiclist.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import swlee.logiclist.domain.Todo;

import javax.sql.DataSource;
import java.util.ArrayList;

@Repository
@Slf4j
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final DataSource dataSource;

    public TodoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Todo todo){
        try{
            final String sql = "INSERT INTO todo (content) VALUES (?)";
            jdbcTemplate.update(sql, todo.getContent());
            log.info("Todo Save Success");
        }catch (Exception e){
            log.error("TodoRepository.save() error",e);
        }
        //JDBC Template Save Query
    }
    //SaveAll batchupdate
    public void saveAll(ArrayList<Todo> todoArrayList){
        try{
            final String sql = "INSERT INTO todo (content) VALUES (?)";
            jdbcTemplate.batchUpdate(sql, todoArrayList, todoArrayList.size(), (ps, todo) -> {
                ps.setString(1, todo.getContent());
            });
            log.info("Todo Save Success");
        }catch (Exception e){
            log.error("TodoRepository.save() error",e);
        }
        //JDBC Template Save Query
    }




}
