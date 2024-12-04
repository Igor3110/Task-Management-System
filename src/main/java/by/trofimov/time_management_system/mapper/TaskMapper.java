package by.trofimov.time_management_system.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.dto.TaskDto;
import by.trofimov.time_management_system.entity.Task;

/**
 * Mapper for the Task class.
 */
@UtilityClass
public class TaskMapper {

    /**
     * The method converts an object of the Task class into an object of the TaskDto class.
     * @param task An object of the Task class.
     * @return Class TaskDto.
     */
    public static TaskDto toTaskDto(Task task) {
        if (task.getComments() == null) {
             task.setComments(new HashSet<>());
        }
        if (task.getExecutors() == null) {
            task.setExecutors(new HashSet<>());
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setAuthor(UserInfoMapper.toUserInfoDto(task.getAuthor()));
        taskDto.setTaskStatus(task.getTaskStatus());
        taskDto.setTaskPriority(task.getTaskPriority());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setComments(
                task.getComments().stream()
                        .map(CommentInfoMapper::toCommentInfoDto)
                        .collect(Collectors.toSet())
        );
        taskDto.setExecutors(
                task.getExecutors().stream()
                        .map(UserInfoMapper::toUserInfoDto)
                        .collect(Collectors.toSet())
        );
        return taskDto;
    }

    /**
     * The method converts an object of the TaskDto class into an object of the Task class.
     * @param taskDto An object of the TaskDto class.
     * @return Class Task.
     */
    public static Task toTask(TaskDto taskDto) {
        if (taskDto.getComments() == null) {
            taskDto.setComments(new HashSet<>());
        }
        if (taskDto.getExecutors() == null) {
            taskDto.setExecutors(new HashSet<>());
        }
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setAuthor(UserInfoMapper.toUser(taskDto.getAuthor()));
        task.setTaskStatus(taskDto.getTaskStatus());
        task.setTaskPriority(taskDto.getTaskPriority());
        task.setDeadline(taskDto.getDeadline());
        task.setComments(
                taskDto.getComments().stream()
                        .map(CommentInfoMapper::toComment)
                        .collect(Collectors.toSet())
        );
        task.setExecutors(
                taskDto.getExecutors().stream()
                        .map(UserInfoMapper::toUser)
                        .collect(Collectors.toSet())
        );
        return task;
    }

}
