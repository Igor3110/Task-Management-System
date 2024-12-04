package by.trofimov.time_management_system.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto for registering a new user.
 */
@Getter
@Setter
public class UserRegistrationDto {

    private String username;
    private String email;
    private String password;
    private String verifyPassword;

}
