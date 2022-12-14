package swlee.logiclist.repository;

import swlee.logiclist.domain.User;

public interface UserRepository {
    /**
     * @Pram
     * String Username - 유저이름
     * String password - 유저패스워드(평문)
     * @return User
     */
    public User save(User user); //첫번째 가입만 받음(관리자)

    /**
     * @Prameter
     * String Username - 유저이름
     * @return User
     */
    public User findByName(String username);

    /**
     * @Prameter
     * String Username - 유저이름
     * @return boolean
     */
    public boolean delete(String username);

}
