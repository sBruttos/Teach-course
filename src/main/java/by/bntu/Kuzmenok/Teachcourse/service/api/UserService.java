package by.bntu.Kuzmenok.Teachcourse.service.api;

import by.bntu.Kuzmenok.Teachcourse.dto.UserDto;
import by.bntu.Kuzmenok.Teachcourse.entity.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    User save(User user);

    void delete(User user);

    User deactivate(User user);
    User activate(User user);


    List<UserDto> getAll();
}
