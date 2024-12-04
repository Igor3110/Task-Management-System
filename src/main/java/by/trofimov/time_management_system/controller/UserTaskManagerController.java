package by.trofimov.time_management_system.controller;

import java.util.List;
import by.trofimov.time_management_system.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import by.trofimov.time_management_system.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import by.trofimov.time_management_system.service.UserTaskManagerService;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Controller class for the User role.
 */
@Tag(name = "Controller for the user to manage tasks.")
@RestController
@RequestMapping("/api")
public class UserTaskManagerController {

    @Autowired
    private UserTaskManagerService userTaskManagerService;

    /**
     * @return Returns all tasks of a given user.
     */
    @Operation(
            summary = "Get all user tasks.",
            description = "The method returns all user tasks where the user is the executor."
    )
    @GetMapping("/taskmanager/getAll")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<TaskDto>> findAllExecutorTasks(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return ResponseEntity.ok(userTaskManagerService.findAllExecutorTasks(token));
    }

    /**
     * @return Tasks for the executor using limit and offset.
     */
    @Operation(
            summary = "Get user tasks using limit and offset.",
            description = "The method returns user tasks where the user is the executor using limit and offset."
    )
    @GetMapping("/taskmanager/getByLimit")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Page<TaskDto>> findExecutorTasksByLimitAndOffset(
            @RequestParam int offset, @RequestParam int limit, HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return ResponseEntity.ok(userTaskManagerService.findExecutorTasksByLimitAndOffset(offset, limit, token));
    }

    /**
     * @return Task for the executor by ID.
     */
    @Operation(
            summary = "Get user task by ID.",
            description = "The method returns a task where the user is the executor by ID."
    )
    @GetMapping("/taskmanager/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> findTaskWhereUserIsExecutorByTaskId(
            @PathVariable int id, HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return ResponseEntity.ok(userTaskManagerService.findTaskWhereUserIsExecutorByTaskId(id, token));
    }

    /**
     * @return The method checks whether the status specified in Dto is correct.
     */
    @Operation(
            summary = "Change task status in the user task.",
            description = "The method changes task status where user is the executor."
    )
    @PostMapping("/taskmanager/changeTaskStatus")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> changeStatusOfTask(
            @RequestBody ChangeStatusOfTaskDto changeStatusOfTaskDto,
            HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return ResponseEntity.ok(userTaskManagerService.changeStatusOfTask(changeStatusOfTaskDto, token));
    }

    /**
     * @return The method add comment for task where user is executor.
     */
    @Operation(
            summary = "Add comment for user task.",
            description = "The method adds a comment to task where user is the executor."
    )
    @PostMapping("/taskmanager/addCommentForTask")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TaskDto> addCommentForTaskWhereUserIsExecutor(
            @RequestBody UserAddCommentDto userAddCommentDto,
            HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return ResponseEntity.ok(
                userTaskManagerService.addCommentForTaskWhereUserIsExecutor(userAddCommentDto, token)
        );
    }

}
