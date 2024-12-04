package by.trofimov.time_management_system.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import by.trofimov.time_management_system.dto.UserAddCommentDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import by.trofimov.time_management_system.entity.*;
import by.trofimov.time_management_system.dto.TaskDto;
import by.trofimov.time_management_system.mapper.TaskMapper;
import by.trofimov.time_management_system.repository.*;
import by.trofimov.time_management_system.token.JwtTokenManager;
import by.trofimov.time_management_system.service.UserTaskManagerService;
import by.trofimov.time_management_system.dto.ChangeStatusOfTaskDto;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Implementation of the service layer for the User role.
 */
@Service
@Validated
public class UserTaskManagerServiceImpl implements UserTaskManagerService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Inject a variable of type UserRepository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @param token JWT token.
     * @return All tasks for the executor.
     */
    @Override
    @Transactional
    public List<TaskDto> findAllExecutorTasks(String token) {
        User user = getUserByToken(token);
        List<Task> tasks = taskRepository.findAllExecutorTasks(user.getId());
        return tasks.stream()
                .map(TaskMapper::toTaskDto)
                .toList();
    }

    /**
     * @param offset The starting position for the output of records.
     * @param limit Number of records to display.
     * @param token JWT token.
     * @return Tasks for the executor using limit and offset.
     */
    @Override
    @Transactional
    public Page<TaskDto> findExecutorTasksByLimitAndOffset(int offset, int limit, String token) {
        User user = getUserByToken(token);
        Page<Task> tasks =
                taskRepository.findExecutorTasksByLimitAndOffset(PageRequest.of(offset, limit), user.getId());
        return tasks.map(TaskMapper::toTaskDto);
    }

    /**
     * @param taskId Task ID.
     * @param token JWT token.
     * @return Task by ID.
     */
    @Override
    @Transactional
    public TaskDto findTaskWhereUserIsExecutorByTaskId(int taskId, String token) {
        User user = getUserByToken(token);
        Task task = taskRepository.findTaskWhereUserIsExecutorByTaskId(user.getId(), taskId);
        if (task == null) {
            throw new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted("taskId"));
        }
        return TaskMapper.toTaskDto(task);
    }

    /**
     * @param changeStatusOfTaskDto Dto for change status of Task.
     * @param token WT token.
     * @return TaskDto
     */
    @Override
    @Transactional
    public TaskDto changeStatusOfTask(@Valid ChangeStatusOfTaskDto changeStatusOfTaskDto, String token) {
        if (!checkTaskStatus(changeStatusOfTaskDto)) {
            throw new ValidationException(TASK_STATUS_NOT_FOUND);
        }
        User user = getUserByToken(token);
        Task task =
                taskRepository.findTaskWhereUserIsExecutorByTaskId(user.getId(), changeStatusOfTaskDto.getTaskId());
        if (task == null) {
            throw new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted("taskId"));
        }
        task.setTaskStatus(changeStatusOfTaskDto.getTaskStatus());
        return TaskMapper.toTaskDto(taskRepository.save(task));
    }

    /**
     * @param userAddCommentDto Dto object than will be used.
     * @param token JWT token.
     * @return An object that will be saved in the database.
     */
    @Override
    @Transactional
    public TaskDto addCommentForTaskWhereUserIsExecutor(@Valid UserAddCommentDto userAddCommentDto, String token) {
        User user = getUserByToken(token);
        int taskId = userAddCommentDto.getTaskId();
        String message = userAddCommentDto.getMessage();
        Task task = taskRepository.findTaskWhereUserIsExecutorByTaskId(user.getId(), taskId);
        if (task == null) {
            throw new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted("taskId"));
        }
        Comment comment = Comment.builder()
                .message(message)
                .dateAdded(LocalDateTime.now())
                .task(task)
                .author(user)
                .build();
        task.addComment(comment);
        return TaskMapper.toTaskDto(taskRepository.save(task));
    }

    /**
     * @param changeStatusOfTaskDto Dto for change status of Task.
     * @return The method checks whether the status specified in Dto is correct.
     */
    private boolean checkTaskStatus(ChangeStatusOfTaskDto changeStatusOfTaskDto) {
        boolean check = false;
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.equals(changeStatusOfTaskDto.getTaskStatus())) {
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * @param token JWT token.
     * @return - Get a User object from a token.
     */
    private User getUserByToken(String token) {
        String username = new JwtTokenManager().getUsername(token);
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_USERNAME.formatted(username))
        );
    }

}
