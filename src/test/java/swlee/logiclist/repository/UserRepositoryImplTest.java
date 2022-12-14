package swlee.logiclist.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import swlee.logiclist.domain.User;

import javax.sql.DataSource;
@Slf4j
@SpringBootTest
class UserRepositoryImplTest {

    private final String USER_A = "USER_A";
    private final String USER_B = "USER_B";

    @Autowired
    private  UserRepository userRepository;

    //테스트 후 삭제
    @AfterEach
    void after()  {
        userRepository.delete(USER_A);
        userRepository.delete(USER_B);

    }
    @Test
    void save() {
        User user = new User(USER_A,"test");
        User save = userRepository.save(user);
        Assertions.assertThat(save).isNotNull();
    }

    @Test
    void findByName() {
        User user = new User(USER_B,"test");
        User save = userRepository.save(user);

        User byName = userRepository.findByName(USER_B);
        log.info("Find User ={}",byName.getUsername());
        Assertions.assertThat(byName).isNotNull();


    }
}