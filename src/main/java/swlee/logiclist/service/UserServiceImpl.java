package swlee.logiclist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import swlee.logiclist.domain.User;
import swlee.logiclist.repository.UserRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;

    @Override
    public User save(User user) {
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User findByName(User user) {
        /*
            Repository에서 가져온  User정보와 입력 User정보 패스워드 비교 필요
         */
        User findUser = userRepository.findByName(user.getUsername());
        if(passwordCheck(user, findUser)){
            log.info("Correct Password ");
            return user;
        }else{
            log.info("incorrect Password");
            return  null;
        }

    }

    private static boolean passwordCheck(User user, User findUser) {
       return BCrypt.checkpw(user.getPassword(), findUser.getPassword());
    }

    @Override
    public boolean delete(String username) {
        boolean result = userRepository.delete(username);
        return result;
    }
}
