package by.bntu.Kuzmenok.Teachcourse.service.api;

import by.bntu.Kuzmenok.Teachcourse.dto.UserDto;
import by.bntu.Kuzmenok.Teachcourse.entity.Course;
import by.bntu.Kuzmenok.Teachcourse.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    Course getById(Long id);

    Course save(Course course);

    void delete(Course course);

}
