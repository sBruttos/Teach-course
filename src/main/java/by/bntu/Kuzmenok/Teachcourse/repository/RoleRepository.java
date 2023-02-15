package by.bntu.Kuzmenok.Teachcourse.repository;

import by.bntu.Kuzmenok.Teachcourse.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(String roleName);
}
