package by.trofimov.time_management_system.service;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import by.trofimov.time_management_system.dto.UserDto;
import by.trofimov.time_management_system.dto.UserTaskDto;

/**
 * Interface of the Service layer for the User entity.
 */
public interface UserService {

    /**
     * @return Returns all users from the database.
     */
    List<UserDto> findAllUsers();

    /**
     * @return Returns users from the database using Limit and Offset.
     */
    Page<UserDto> findAllUsersUsingLimitAndOffset(int offset, int limit);

    /**
     * @param id ID of the user you are looking for.
     * @return The method returns the object of the searched user by its ID.
     */
    UserDto findUserById(int id);

    /**
     * @param username Username.
     * @return We return the user by his name.
     */
    UserDto findUserByUsername(String username);

    /**
     * @param email Email of the user.
     * @return We return the user by his email.
     */
    UserDto findUserByEmail(String email);

    /**
     * @param userDto An object that will be saved in the database.
     */
    UserDto saveOrUpdateUser(@Valid UserDto userDto);

    /**
     * @param id ID of the user who will be deleted from the database.
     */
    void deleteUserById(int id);

}
