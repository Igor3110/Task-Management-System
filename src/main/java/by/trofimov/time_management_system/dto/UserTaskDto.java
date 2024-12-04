package by.trofimov.time_management_system.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto for adding a task for the User.
 */
@Getter
@Setter
public class UserTaskDto {

    private int userId;

    private int taskId;

}
