package by.bntu.Kuzmenok.Teachcourse.service.impl;

import by.bntu.Kuzmenok.Teachcourse.dto.UserDto;
import by.bntu.Kuzmenok.Teachcourse.entity.User;
import by.bntu.Kuzmenok.Teachcourse.mappers.api.UserMapper;
import by.bntu.Kuzmenok.Teachcourse.repository.UserRepository;
import by.bntu.Kuzmenok.Teachcourse.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User deactivate(User user) {
        user.setActive(false);
        return user;
    }

    @Override
    public User activate(User user) {
        user.setActive(true);
        return user;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
