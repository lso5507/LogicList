package swlee.logiclist.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 * 패스워드는 암호화상태로 저장
 */
@Data

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }
}
