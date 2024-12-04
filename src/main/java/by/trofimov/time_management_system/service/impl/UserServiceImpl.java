package by.trofimov.time_management_system.service.impl;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import by.trofimov.time_management_system.dto.UserDto;
import by.trofimov.time_management_system.entity.User;
import by.trofimov.time_management_system.mapper.UserMapper;
import by.trofimov.time_management_system.service.UserService;
import by.trofimov.time_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Implementation of the service layer for the User class.
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    /**
     * Inject a variable of type UserRepository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Inject a variable of type TaskRepository.
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * @return Returns all users from the database.
     */
    @Override
    @Transactional
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDto)
                .toList();
    }

    /**
     * @return Returns users from the database using Limit and Offset.
     */
    @Override
    @Transactional
    public Page<UserDto> findAllUsersUsingLimitAndOffset(int offset, int limit) {
        return userRepository.findAll(PageRequest.of(offset, limit))
                .map(UserMapper::toUserDto);
    }

    /**
     * @param id ID of the user you are looking for.
     * @return The method returns the object of the searched user by its ID.
     */
    @Override
    @Transactional
    public UserDto findUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID.formatted(id))
        );
        return UserMapper.toUserDto(user);
    }

    /**
     * @param username Username.
     * @return We return the user by his name.
     */
    @Override
    @Transactional
    public UserDto findUserByUsername(String username) {
        User user =  userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_USERNAME.formatted(username))
        );
        return UserMapper.toUserDto(user);
    }

    /**
     * @param email Email of the user.
     * @return We return the user by his email.
     */
    @Override
    @Transactional
    public UserDto findUserByEmail(String email) {
        User user =  userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_EMAIL.formatted(email))
        );
        return UserMapper.toUserDto(user);
    }

    /**
     * @param userDto An object that will be saved in the database.
     */
    @Override
    @Transactional
    public UserDto saveOrUpdateUser(@Valid UserDto userDto) {
        if (existsUsernameOrEmail(userDto)) {
            throw new EntityExistsException(USER_WITH_SAME_NAME_OR_EMAIL_EXISTS);
        }
        User user = userRepository.save(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    /**
     * @param id ID of the user who will be deleted from the database.
     */
    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * @param userDto An object that will be saved in the database.
     * @return We check if there is a user with the same name or email in the database.
     */
    private boolean existsUsernameOrEmail(UserDto userDto) {
        return userRepository.existsByUsernameOrEmail(
                userDto.getUsername(),
                userDto.getEmail()
        );
    }

}
