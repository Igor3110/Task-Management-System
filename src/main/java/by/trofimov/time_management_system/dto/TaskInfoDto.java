package by.trofimov.time_management_system.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for entity Task.
 */
@Setter
@Getter
public class TaskInfoDto {

    private int id;

    @Size(
            min = MIN_TEXT_FIELD_LENGTH,
            max = MAX_TEXT_FIELD_LENGTH,
            message = MESSAGE_FOR_INCORRECT_TEXT_FIELD
    )
    private String name;

}
