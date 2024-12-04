package by.trofimov.time_management_system.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for adding a comment for the Task.
 */
@Getter
@Setter
public class UserAddCommentDto {

    private int taskId;

    @Size(
            min = MIN_TEXT_FIELD_LENGTH_FOR_COMMENT,
            max = MAX_TEXT_FIELD_LENGTH_FOR_COMMENT,
            message = MESSAGE_FOR_INCORRECT_COMMENT_TEXT_FIELD
    )
    private String message;

}
