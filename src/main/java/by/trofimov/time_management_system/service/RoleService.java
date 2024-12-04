package by.trofimov.time_management_system.service;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import by.trofimov.time_management_system.dto.*;

/**
 * Interface of the Service layer for the Role entity.
 */
public interface RoleService {

    /**
     * @return Returns all roles from the database.
     */
    List<RoleInfoDto> findAllRoles();

    /**
     * @return Returns roles from the database using Limit and Offset.
     */
    Page<RoleInfoDto> findAllRolesUsingLimitAndOffset(int offset, int limit);

    /**
     * @param id ID of the role you are looking for.
     * @return The method returns the object of the searched role by its ID.
     */
    RoleInfoDto findRoleById(int id);

    /**
     * @param name Name of the role you are looking for.
     * @return The method returns the object of the searched role by its Name.
     */
    RoleInfoDto findRoleByName(String name);

    /**
     * @param roleInfoDto An object that will be saved in the database.
     */
    RoleInfoDto saveOrUpdateRole(@Valid RoleInfoDto roleInfoDto);

    /**
     * @param id ID of the role who will be deleted from the database.
     */
    void deleteRoleById(int id);

}
