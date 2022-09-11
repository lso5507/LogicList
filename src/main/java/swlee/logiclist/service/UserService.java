package swlee.logiclist.service;

import swlee.logiclist.domain.User;

public interface UserService {
    /**
     * @Pram
     * User user - 유저
     * @return User
     */
    public User save(User user); //첫번째 가입만 받음(관리자)

    /**
     * @Prameter
     * User user - 유저
     * @return User
     */
    public User findByName(User user);

    /**
     * @Prameter
     * String username - 유저이름
     * @return boolean - 성공여부
     */
    public boolean delete(String username);


}
