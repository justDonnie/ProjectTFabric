package tisobay.projecttiso.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tisobay.projecttiso.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.id, u.email,u.password,u.role from users u ", nativeQuery = true)
    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);
}