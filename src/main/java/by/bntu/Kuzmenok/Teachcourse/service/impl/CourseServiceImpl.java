package by.bntu.Kuzmenok.Teachcourse.service.impl;

import by.bntu.Kuzmenok.Teachcourse.entity.Course;
import by.bntu.Kuzmenok.Teachcourse.repository.CourseRepository;
import by.bntu.Kuzmenok.Teachcourse.service.api.CourseService;
import by.bntu.Kuzmenok.Teachcourse.service.api.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final FileService fileService;

    private final CourseRepository courseRepository;

    public CourseServiceImpl(FileService fileService, CourseRepository courseRepository) {
        this.fileService = fileService;
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(new Course());
    }

    @Override
    public Course save(Course course){

        course = Course.builder()
                .name(course.getName())
                .description(course.getDescription())
                .build();

        return courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
