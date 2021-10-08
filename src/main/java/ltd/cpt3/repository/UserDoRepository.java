package ltd.cpt3.repository;

import ltd.cpt3.entity.data.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Optional;

/**
 * @author yang
 */
@Repository
@Table(name = "user")
public interface UserDoRepository extends JpaRepository<UserDo, Integer> {

    /**
     * Find user by username.
     *
     * @param username The username.
     * @return The optional user.
     */
    Optional<UserDo> findByUsername(String username);

    /**
     * Judge if the user is exists by username.
     *
     * @param username The username.
     * @return True for exists.
     */
    @Deprecated
    Boolean existsByUsername(String username);

}
