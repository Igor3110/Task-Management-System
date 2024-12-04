package by.trofimov.time_management_system.mapper;

import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.entity.Task;
import by.trofimov.time_management_system.dto.TaskInfoDto;

/**
 * Mapper for the Task class.
 */
@UtilityClass
public class TaskInfoMapper {

    /**
     * The method converts an object of the Task class into an object of the TaskInfoDto class.
     * @param task An object of the Task class.
     * @return Class TaskInfoDto.
     */
    public static TaskInfoDto toTaskInfoDto(Task task) {
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        taskInfoDto.setId(task.getId());
        taskInfoDto.setName(task.getName());
        return taskInfoDto;
    }

    /**
     * The method converts an object of the TaskInfoDto class into an object of the Task class.
     * @param taskInfoDto An object of the TaskInfoDto class.
     * @return Class Task.
     */
    public static Task toTask(TaskInfoDto taskInfoDto) {
        Task task = new Task();
        task.setId(taskInfoDto.getId());
        task.setName(taskInfoDto.getName());
        return task;
    }

}
