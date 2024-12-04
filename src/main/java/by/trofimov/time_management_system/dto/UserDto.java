package by.trofimov.time_management_system.dto;

import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for entity User.
 */
@Getter
@Setter
public class UserDto {

    private int id;

    @Size(
            min = MIN_TEXT_FIELD_LENGTH,
            max = MAX_TEXT_FIELD_LENGTH,
            message = MESSAGE_FOR_INCORRECT_TEXT_FIELD
    )
    private String username;

    @Email(message = INCORRECT_EMAIL)
    @Size(
            min = MIN_TEXT_FIELD_LENGTH,
            max = MAX_TEXT_FIELD_LENGTH,
            message = MESSAGE_FOR_INCORRECT_TEXT_FIELD
    )
    private String email;

    @Size(
            min = MIN_PASSWORD_FIELD_LENGTH,
            max = MAX_PASSWORD_FIELD_LENGTH,
            message = MESSAGE_FOR_INCORRECT_PASSWORD
    )
    private String password;

    private RoleInfoDto role;

    private Set<TaskInfoDto> tasks = new HashSet<>();

}
