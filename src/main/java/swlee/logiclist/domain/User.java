package swlee.logiclist.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 패스워드는 암호화상태로 저장
 */
@Data
public class User {
    private String username;
    private String password;
    public User(){}

    public User(String username, String password) {
        this.username=username;
        this.password=BCrypt.hashpw(password,BCrypt.gensalt(10));
    }
}
