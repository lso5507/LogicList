package swlee.logiclist.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import swlee.logiclist.domain.User;

public interface UserService extends UserDetailsService {
    /**
     * @Pram
     * User user - 유저
     * @return User
     */
    public User save(User user); //첫번째 가입만 받음(관리자)


    /**
     * @Prameter
     * String username - 유저이름
     * @return boolean - 성공여부
     */
    public boolean delete(String username);


}
