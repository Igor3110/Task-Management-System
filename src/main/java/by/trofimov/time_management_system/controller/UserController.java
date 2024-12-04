package by.trofimov.time_management_system.controller;

import java.util.List;
import java.time.LocalDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import by.trofimov.time_management_system.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import by.trofimov.time_management_system.dto.BaseResponse;
import by.trofimov.time_management_system.service.UserService;
import by.trofimov.time_management_system.service.AuthService;
import by.trofimov.time_management_system.dto.UserRegistrationDto;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Controller class for the User entity.
 */
@Tag(name = "Controller for entity User (Administrator only).")
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * Inject a variable of type UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * Inject a variable of type AuthService.
     */
    @Autowired
    private AuthService authService;

    /**
     * @return Returns all users.
     */
    @Operation(
            summary = "Get all users from Database.",
            description = "The method returns all users from Database."
    )
    @GetMapping("/users/getAll")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    /**
     * @return Returns users using Limit and Offset.
     */
    @Operation(
            summary = "Get all users using limit and offset.",
            description = "The method returns all users using limit and offset."
    )
    @GetMapping("/users/getByLimit")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Page<UserDto>> findAllUsersUsingLimitAndOffset(
            @Valid @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @Valid @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit
    ) {
        return ResponseEntity.ok(userService.findAllUsersUsingLimitAndOffset(offset, limit));
    }

    /**
     * @param id User ID.
     * @return Returns user by ID.
     */
    @Operation(
            summary = "Get user by ID.",
            description = "The method returns user by ID."
    )
    @GetMapping("/users/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserDto> findUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    /**
     * @param username Username.
     * @return Returns user by Name.
     */
    @Operation(
            summary = "Get user by username.",
            description = "The method returns user by username."
    )
    @GetMapping("/users/getByName")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserDto> findUserByName(@RequestParam String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    /**
     * @param email Email.
     * @return Returns user by Name.
     */
    @Operation(
            summary = "Get user by email.",
            description = "The method returns user by email."
    )
    @GetMapping("/users/getByEmail")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserDto> findUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    /**
     * @param userRegistrationDto - UserDto object.
     * @return - Saves user to the database.
     */
    @Operation(
            summary = "Add user to Database.",
            description = "The method saves user to Database and returns UserDto."
    )
    @PostMapping("/users")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return authService.createNewUser(userRegistrationDto);
    }

    /**
     * @param userDto - UserDto object.
     * @return - Updates user to the database.
     */
    @Operation(
            summary = "Change user.",
            description = "The method changes user and returns UserDto."
    )
    @PutMapping("/users")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveOrUpdateUser(userDto));
    }

    /**
     * @param id - user ID.
     * @return - Removes user by ID from the database.
     */
    @Operation(
            summary = "Delete user from Database.",
            description = "The method delete user by ID and returns BaseResponse."
    )
    @DeleteMapping("/users/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse(
                        ENTITY_WAS_SUCCESSFULLY_DELETED.formatted(id),
                        LocalDateTime.now())
                );
    }

}
