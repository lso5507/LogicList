package swlee.logiclist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        User byUser = userRepository.findByName(user.getUsername());
        return byUser;

    }

    @Override
    public boolean delete(String username) {
        boolean result = userRepository.delete(username);
        return result;
    }
}
