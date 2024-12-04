package by.trofimov.time_management_system.dto;

import lombok.*;
import java.util.*;
import jakarta.validation.constraints.Size;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for entity Role.
 */
@Setter
@Getter
public class RoleDto {

    private int id;

    @Size(
            min = MIN_TEXT_FIELD_ROLE_LENGTH,
            max = MAX_TEXT_FIELD_ROLE_LENGTH,
            message = MESSAGE_FOR_INCORRECT_ROLE_NAME_TEXT_FIELD
    )
    private String name;

    private Set<UserInfoDto> users = new HashSet<>();

}
