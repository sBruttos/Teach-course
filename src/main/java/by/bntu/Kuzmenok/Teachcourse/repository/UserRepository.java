package by.bntu.Kuzmenok.Teachcourse.repository;

import by.bntu.Kuzmenok.Teachcourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
