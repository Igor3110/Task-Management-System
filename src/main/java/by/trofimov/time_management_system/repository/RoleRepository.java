package by.trofimov.time_management_system.repository;

import java.util.Optional;
import by.trofimov.time_management_system.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for the Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * @param name Name of the role you are looking for.
     * @return The method returns the object of the searched role by its Name.
     */
    Optional<Role> findRoleByName(String name);

    /**
     * @param name Role name
     * @return We check if there is a role with the same name in the database.
     */
    boolean existsByName(String name);

}
