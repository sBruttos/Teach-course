package by.bntu.Kuzmenok.Teachcourse.repository;

import by.bntu.Kuzmenok.Teachcourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository <Course,Long> {


}
