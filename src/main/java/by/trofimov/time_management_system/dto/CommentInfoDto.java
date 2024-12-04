package by.trofimov.time_management_system.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Size;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for entity Comment.
 */
@Setter
@Getter
public class CommentInfoDto {

    private int id;

    @Size(
            min = MIN_TEXT_FIELD_LENGTH_FOR_COMMENT,
            max = MAX_TEXT_FIELD_LENGTH_FOR_COMMENT,
            message = MESSAGE_FOR_INCORRECT_COMMENT_TEXT_FIELD
    )
    private String message;

    private int authorId;

    private String authorUsername;

}
