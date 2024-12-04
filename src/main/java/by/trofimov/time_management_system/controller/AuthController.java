package by.trofimov.time_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import by.trofimov.time_management_system.dto.*;
import org.springframework.web.bind.annotation.*;
import by.trofimov.time_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Controller class for authentication.
 */
@Tag(name = "Controller for user registration and authentication.")
@RestController
@RequestMapping("/api")
public class AuthController {

    /**
     * Inject a variable of type AuthService.
     */
    @Autowired
    private AuthService authService;

    /**
     * @return Returns a token.
     */
    @Operation(
            summary = "We receive a JWT token.",
            description = "The method accepts a JwtRequest and returns the generated JWT token."
    )
    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request) throws BadCredentialsException {
        String token = authService.getToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * @param userRegistrationDto Dto for entity User.
     * @return If User is found, return status 200 and User, otherwise return status 404.
     */
    @Operation(
            summary = "Adds a new user to the database.",
            description = "The method accepts a userRegistrationDto and returns UserDto."
    )
    @PostMapping("/registration")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return authService.createNewUser(userRegistrationDto);
    }

}
