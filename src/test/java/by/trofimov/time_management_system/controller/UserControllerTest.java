package by.trofimov.time_management_system.controller;

import java.util.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import by.trofimov.time_management_system.service.*;
import by.trofimov.time_management_system.entity.User;
import by.trofimov.time_management_system.dto.UserDto;
import by.trofimov.time_management_system.dto.UserRegistrationDto;
import org.springframework.data.domain.Page;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @Mock
    Page<UserDto> page;

    @Mock
    private User user;

    @Mock
    private UserDto userDto;

    @Mock
    private UserRegistrationDto userRegistrationDto;

    @InjectMocks
    private UserController userController;

    private final int userId = 1;
    private final String username = "Admin";

    @Test
    void testFindAllUsers() {
        when(userService.findAllUsers()).thenReturn(Collections.emptyList());
        userController.findAllUsers().getBody();
        verify(userService).findAllUsers();
    }

    @Test
    void testFindAllUsersByLimitAndOffset() {
        when(userService.findAllUsersUsingLimitAndOffset(0,1)).thenReturn(page);
        userController.findAllUsersUsingLimitAndOffset(0, 1);
        verify(userService).findAllUsersUsingLimitAndOffset(0, 1);
    }

    @Test
    void testFindUserById() {
        when(userService.findUserById(userId)).thenReturn(userDto);
        userController.findUserById(userId);
        verify(userService).findUserById(userId);
    }

    @Test
    void testFindUserByName() {
        when(userService.findUserByUsername(username)).thenReturn(userDto);
        userController.findUserByName(username);
        verify(userService).findUserByUsername(username);
    }

    @Test
    void testFindUserByEmail() {
        when(userService.findUserByEmail(username)).thenReturn(userDto);
        userController.findUserByEmail(username);
        verify(userService).findUserByEmail(username);
    }

    @Test
    void testAddUser() {
        userController.saveUser(userRegistrationDto);
        verify(authService).createNewUser(userRegistrationDto);
    }

    @Test
    void testUpdateUser() {
        when(userService.saveOrUpdateUser(userDto)).thenReturn(userDto);
        userController.updateUser(userDto);
        verify(userService).saveOrUpdateUser(userDto);
    }

    @Test
    void testDeleteUser() {
        userController.deleteUser(userId);
        verify(userService).deleteUserById(userId);
    }

}
