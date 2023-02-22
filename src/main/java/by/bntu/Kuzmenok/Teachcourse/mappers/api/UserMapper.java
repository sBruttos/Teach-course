package by.bntu.Kuzmenok.Teachcourse.mappers.api;

import by.bntu.Kuzmenok.Teachcourse.dto.UserDto;
import by.bntu.Kuzmenok.Teachcourse.entity.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
