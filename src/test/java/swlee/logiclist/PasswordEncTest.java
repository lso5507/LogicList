package swlee.logiclist;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import swlee.logiclist.domain.User;

@Slf4j
public class PasswordEncTest {
    @Test
    public void enc(){
         final String plainPassword="votmdnjemdlqslek!@#";
        User user = new User("LSW",plainPassword);
        log.info("Password={}",user.getPassword());
        boolean result = BCrypt.checkpw(plainPassword, user.getPassword());
        Assertions.assertThat(result).isTrue();
    }
}