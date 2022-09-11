package swlee.logiclist.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swlee.logiclist.domain.User;
import swlee.logiclist.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    private final String USER_A = "USER_A";

    //테스트 후 삭제
    @AfterEach
    void after()  {
        userService.delete(USER_A);
    }
    @Test
    void save() {
        User user = new User(USER_A,"test");
        User save = userService.save(user);
        log.info("USER PASSWORD ={}",user.getPassword());
        Assertions.assertThat(save).isNotNull();
    }

    @Test
    void findByName() {
        User user = new User(USER_A,"test");
        userService.save(user);

        User byName = userService.findByName(user);
        log.info("Find User ={}",byName.getUsername());
        Assertions.assertThat(byName).isNotNull();


    }

    @Test
    void delete() {
        User user = new User(USER_A,"test");
        userService.save(user);
        boolean delete = userService.delete(USER_A);
        Assertions.assertThat(delete).isNotEqualTo(false);
    }
}