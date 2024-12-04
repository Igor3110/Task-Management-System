package by.trofimov.time_management_system.dto;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;
import by.trofimov.time_management_system.entity.*;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Dto for entity Task.
 */
@Setter
@Getter
public class TaskDto {

    private int id;

    @Size(
            min = MIN_TEXT_FIELD_LENGTH,
            max = MAX_TEXT_FIELD_LENGTH,
            message = MESSAGE_FOR_INCORRECT_TEXT_FIELD
    )
    private String name;

    private UserInfoDto author;

    private TaskStatus taskStatus;

    private TaskPriority taskPriority;

    private LocalDateTime deadline;

    private Set<CommentInfoDto> comments = new HashSet<>();

    private Set<UserInfoDto> executors = new HashSet<>();

}
