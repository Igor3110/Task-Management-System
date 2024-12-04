package by.trofimov.time_management_system.service;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import by.trofimov.time_management_system.dto.*;

/**
 * Interface of the Service layer for the Task entity.
 */
public interface TaskService {

    /**
     * @return Returns all tasks from the database.
     */
    List<TaskDto> findAllTasks();

    /**
     * @return Returns tasks from the database using Limit and Offset.
     */
    Page<TaskDto> findAllTasksUsingLimitAndOffset(int offset, int limit);

    /**
     * @param id ID of the task you are looking for.
     * @return The method returns the object of the searched task by its ID.
     */
    TaskDto findTaskById(int id);

    /**
     * @param executorId ID of the executor you are looking for.
     * @return The method returns executors when doing this task.
     */
    List<TaskDto> findTasksByExecutor(int executorId);

    /**
     * @param taskDto An object that will be saved in the database.
     */
    TaskDto saveOrUpdateTask(@Valid TaskDto taskDto);

    /**
     * @param id ID of the task who will be deleted from the database.
     */
    void deleteTaskById(int id);

    /**
     * @param userAddCommentDto Dto object than will be used.
     * @param token JWT token.
     * @return An object that will be saved in the database.
     */
    TaskDto addCommentForTask(@Valid UserAddCommentDto userAddCommentDto, String token);

    /**
     * @param commentId Comment Id.
     */
    void deleteCommentForTask(int commentId);

    /**
     * @param userTaskDto - Dto for adding a task for User.
     */
    TaskDto addExecutorForTask(UserTaskDto userTaskDto);

    /**
     * @param userId - User ID.
     * @param taskId - Task ID.
     */
    void deleteExecutorForTask(int userId, int taskId);

}
