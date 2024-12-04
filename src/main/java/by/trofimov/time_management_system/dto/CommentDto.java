package by.trofimov.time_management_system.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;
import by.trofimov.time_management_system.entity.Task;
import by.trofimov.time_management_system.entity.User;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for entity Comment.
 */
@Setter
@Getter
public class CommentDto {

    private int id;

    @Size(
            min = MIN_TEXT_FIELD_LENGTH_FOR_COMMENT,
            max = MAX_TEXT_FIELD_LENGTH_FOR_COMMENT,
            message = MESSAGE_FOR_INCORRECT_COMMENT_TEXT_FIELD
    )
    private String message;

    private LocalDateTime dateAdded;

    private Task task;

    private User author;

}
