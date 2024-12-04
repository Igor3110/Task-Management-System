package by.trofimov.time_management_system.controller;

import java.util.List;
import java.time.LocalDateTime;
import by.trofimov.time_management_system.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import by.trofimov.time_management_system.service.TaskService;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Controller class for the Task entity.
 */
@Tag(name = "Controller for entity Task (Administrator only).")
@RestController
@RequestMapping("/api")
public class TaskController {

    /**
     * Inject a variable of type TaskService.
     */
    @Autowired
    private TaskService taskService;

    /**
     * @return Returns all tasks.
     */
    @Operation(
            summary = "Get all tasks from Database.",
            description = "The method returns all tasks from Database."
    )
    @GetMapping("/tasks/getAll")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<TaskDto>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    /**
     * @return Returns tasks using Limit and Offset.
     */
    @Operation(
            summary = "Get all tasks using limit and offset.",
            description = "The method returns all tasks using limit and offset."
    )
    @GetMapping("/tasks/getByLimit")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Page<TaskDto>> findAllTasksUsingLimitAndOffset(
            @Valid @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @Valid @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit
    ) {
        return ResponseEntity.ok(taskService.findAllTasksUsingLimitAndOffset(offset, limit));
    }

    /**
     * @return Returns all tasks of the executor.
     */
    @Operation(
            summary = "Get all tasks of the executor from Database.",
            description = "The method returns all tasks of the executor from Database."
    )
    @GetMapping("/tasks/getExecutorTasks")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<TaskDto>> findTasksByExecutor(@RequestParam int executorId) {
        return ResponseEntity.ok(taskService.findTasksByExecutor(executorId));
    }

    /**
     * @param id Task ID.
     * @return Returns tasks by ID.
     */
    @Operation(
            summary = "Get task by ID.",
            description = "The method returns task by ID."
    )
    @GetMapping("/tasks/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable int id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    /**
     * @param taskDto - TaskDto object.
     * @return - Saves task to the database.
     */
    @Operation(
            summary = "Add task to Database.",
            description = "The method saves task to Database and returns TaskDto."
    )
    @PostMapping("/tasks")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.saveOrUpdateTask(taskDto));
    }

    /**
     * @param taskDto - TaskDto object.
     * @return - Updates task to the database.
     */
    @Operation(
            summary = "Change task.",
            description = "The method changes task and returns TaskDto."
    )
    @PutMapping("/tasks")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.saveOrUpdateTask(taskDto));
    }

    /**
     * @param id - task ID.
     * @return - Removes task by ID from the database.
     */
    @Operation(
            summary = "Delete task from Database.",
            description = "The method delete task by ID and returns BaseResponse."
    )
    @DeleteMapping("/tasks/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BaseResponse> deleteTask(@PathVariable int id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse(
                        ENTITY_WAS_SUCCESSFULLY_DELETED.formatted(id),
                        LocalDateTime.now())
                );
    }

    /**
     * @param userAddCommentDto Dto object than will be used.
     * @return Object CommentInfoDto
     */
    @Operation(
            summary = "Add a comment to the task.",
            description = "The method adds a comment to the task and returns TaskDto."
    )
    @PostMapping("/tasks/addComment")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> addCommentForTask(
            @RequestBody UserAddCommentDto userAddCommentDto,
            HttpServletRequest request
    ) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return ResponseEntity.ok(taskService.addCommentForTask(userAddCommentDto, token));
    }

    /**
     * @param commentId Comment ID.
     * @return Delete comment by Task ID and Comment ID.
     */
    @Operation(
            summary = "Delete a comment from the task.",
            description = "The method delete a comment from the task and returns BaseResponse."
    )
    @DeleteMapping("/tasks/deleteComment")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BaseResponse> deleteCommentForTask(@RequestParam int commentId) {
        taskService.deleteCommentForTask(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse(
                        COMMENT_WITH_ID_WAS_SUCCESSFULLY_DELETED.formatted(commentId),
                        LocalDateTime.now())
                );
    }

    /**
     * @param userTaskDto UserTaskDto object.
     * @return Object TaskDto.
     */
    @Operation(
            summary = "Add an executor to the task.",
            description = "The method adds a executor to the task and returns TaskDto."
    )
    @PostMapping("/tasks/addExecutor")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> addExecutorForTask(@RequestBody UserTaskDto userTaskDto) {
        return ResponseEntity.ok(taskService.addExecutorForTask(userTaskDto));
    }

    /**
     * @param userId User ID.
     * @param taskId Task ID.
     * @return Delete comment by User ID and Comment ID.
     */
    @Operation(
            summary = "Delete an executor from the task.",
            description = "The method delete an executor from the task and returns BaseResponse."
    )
    @DeleteMapping("/tasks/deleteExecutor")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BaseResponse> deleteExecutorForTask(@RequestParam int userId, @RequestParam int taskId) {
        taskService.deleteExecutorForTask(userId, taskId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse(
                        EXECUTOR_FOR_TASK_WITH_ID_WAS_SUCCESSFULLY_DELETED.formatted(userId, taskId),
                        LocalDateTime.now())
                );
    }

}
