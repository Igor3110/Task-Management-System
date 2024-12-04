package by.trofimov.time_management_system.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import by.trofimov.time_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param name Name of the user you are looking for.
     * @return The method returns the object of the searched user by its Name.
     */
    Optional<User> findUserByUsername(String name);

    /**
     * @param email Email of the user you are looking for.
     * @return The method returns the object of the searched user by its Email.
     */
    Optional<User> findUserByEmail(String email);

    /**
     * @param username Name of the user you are looking for.
     * @param email Email of the user you are looking for.
     * @return We check if there is a user with the same name or email in the database.
     */
    boolean existsByUsernameOrEmail(String username, String email);

}
