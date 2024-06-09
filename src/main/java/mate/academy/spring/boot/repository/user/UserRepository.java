package mate.academy.spring.boot.repository.user;

import java.util.Optional;
import mate.academy.spring.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Optional<UserDetails> findByEmail(String email);
}
