package by.trofimov.time_management_system.dto;

import by.trofimov.time_management_system.entity.TaskStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto for change status of entity Task.
 */
@Setter
@Getter
public class ChangeStatusOfTaskDto {

    private int taskId;

    private TaskStatus taskStatus;

}
