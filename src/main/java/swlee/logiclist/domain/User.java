package swlee.logiclist.domain;

import lombok.Builder;
import lombok.Data;


/**
 *
 */
@Data
@Builder
public class User {
    private String username;
    private String password;
    public User(){}

    public User(String username, String password) {
        this.username=username;
//        this.password=BCrypt.hashpw(password,BCrypt.gensalt(10));
        this.password=password;
    }
}
