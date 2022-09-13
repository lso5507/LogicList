package swlee.logiclist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import swlee.logiclist.Exception.IncorrectAccountException;
import swlee.logiclist.domain.User;
import swlee.logiclist.repository.UserRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private  final UserRepository userRepository;

    @Override
    public User save(User user) {
        User save = userRepository.save(encPassword(user));
        return save;
    }

    @Override
    public User findByName(User user) {
        /*
            Repository에서 가져온  User정보와 입력 User정보 패스워드 비교 필요
         */
        User findUser = userRepository.findByName(user.getUsername());
        //username Check
        if(findUser==null){
            throw new IncorrectAccountException("계정이 올바르지 않습니다.");
        }
        if(passwordCheck(user, findUser)){
            log.info("Correct Password ");
            return  findUser;
        }else{
            log.info("incorrect Password");
            throw new IncorrectAccountException("패스워드가 알맞지 않습니다.");

        }

    }
    private static User encPassword(User user){
        return new User(user.getUsername(),BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
    }

    private static boolean passwordCheck(User user, User findUser) {
       return BCrypt.checkpw(user.getPassword(), findUser.getPassword());
    }

    @Override
    public boolean delete(String username) {
        boolean result = userRepository.delete(username);
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByName(username);
        if(findUser==null){
            throw new UsernameNotFoundException(username);
        }
        log.info("loadUserByUserName!!::{}",username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(findUser.getUsername())
                .password(findUser.getPassword())
                .roles("USER")
                .build();

//        //인메모리에 username, password, role 설정
//        UserDetails user =
//                org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
//                        .username()
//                        .password("pwd")
//                        .roles("USER")
//                        .build();
//
//        System.out.println("password : " + user.getPassword());
//
//        return new InMemoryUserDetailsManager(user);
//        return null;
    }
}
