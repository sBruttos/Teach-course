package by.bntu.Kuzmenok.Teachcourse.repository;

import by.bntu.Kuzmenok.Teachcourse.entity.Role;
import by.bntu.Kuzmenok.Teachcourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(@Param("email") String email);
}
