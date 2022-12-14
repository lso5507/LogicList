package swlee.logiclist.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        //사용자이름 가져오기
        String password="";
        if(System.getProperty("user.name").equals("swlee"));{
            //OS 체크
            String os = System.getProperty("os.name").toLowerCase();
            //os 가 mac을 포함
            if(os.contains("mac")){
                password="lsw96";
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setUsername("lsw");
                dataSource.setPassword(password);
                //postgresql DriverClassname
                dataSource.setDriverClassName("org.postgresql.Driver");
//                dataSource.setUrl("jdbc:postgresql:tcp://localhost/~/logiclist");
                dataSource.setUrl("jdbc:postgresql://localhost:5432/logiclist");
                return dataSource;
            }
            else if(os.contains("window")){
                password="1111";
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setUsername("sa");
                dataSource.setPassword(password);
                dataSource.setDriverClassName("org.h2.Driver");
                dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
                return dataSource;
            }
            else {
                    password="tjrdns9^";
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setUsername("lee");
                dataSource.setPassword(password);
                //postgresql DriverClassname
                dataSource.setDriverClassName("org.postgresql.Driver");
//                dataSource.setUrl("jdbc:postgresql:tcp://localhost/~/logiclist");
                dataSource.setUrl("jdbc:postgresql://localhost:5432/logiclist");

                //postgresql

                return dataSource;
            }
        }

    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
