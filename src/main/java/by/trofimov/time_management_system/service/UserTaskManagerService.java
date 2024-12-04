package by.trofimov.time_management_system.service;

import java.util.List;
import by.trofimov.time_management_system.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

/**
 * Interface of the Service layer for the User role.
 */
public interface UserTaskManagerService {

    /**
     * @param token JWT token.
     * @return All tasks for the executor.
     */
    List<TaskDto> findAllExecutorTasks(String token);

    /**
     * @param offset The starting position for the output of records.
     * @param limit Number of records to display.
     * @param token JWT token.
     * @return Tasks for the executor using limit and offset.
     */
    Page<TaskDto> findExecutorTasksByLimitAndOffset(int offset, int limit, String token);

    /**
     * @param taskId Task ID.
     * @param token JWT token.
     * @return Task by ID.
     */
    TaskDto findTaskWhereUserIsExecutorByTaskId(int taskId, String token);

    /**
     * @param changeStatusOfTaskDto Dto for change status of Task.
     * @param token JWT token.
     * @return An object that will be saved in the database.
     */
    TaskDto changeStatusOfTask(@Valid ChangeStatusOfTaskDto changeStatusOfTaskDto, String token);

    /**
     * @param userAddCommentDto Dto object than will be used.
     * @param token JWT token.
     * @return An object that will be saved in the database.
     */
    TaskDto addCommentForTaskWhereUserIsExecutor(@Valid UserAddCommentDto userAddCommentDto, String token);

}
