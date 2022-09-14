package swlee.logiclist;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import swlee.logiclist.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class PasswordEncTest {
    @Test
    public void enc(){
         final String plainPassword="1234";
        User user = new User("LSW",plainPassword);
        User user2 = new User("LSW2",plainPassword);

        log.info("Password={}",user.getPassword());
        boolean result = BCrypt.checkpw(plainPassword, user.getPassword());
        Assertions.assertThat(result).isTrue();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    @DisplayName("패스워드 암호화 테스트")
    void passwordEncode() {
        // given
        String rawPassword = "12345678";

        // when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // then
        assertAll(
                () -> assertNotEquals(rawPassword, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
        );
    }
}
