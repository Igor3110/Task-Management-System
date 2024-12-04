package by.trofimov.time_management_system.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import by.trofimov.time_management_system.dto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import by.trofimov.time_management_system.entity.*;
import by.trofimov.time_management_system.service.*;
import by.trofimov.time_management_system.repository.*;
import by.trofimov.time_management_system.mapper.TaskMapper;
import by.trofimov.time_management_system.entity.User;
import by.trofimov.time_management_system.repository.UserRepository;
import by.trofimov.time_management_system.token.JwtTokenManager;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Implementation of the service layer for the Task class.
 */
@Service
@Validated
public class TaskServiceImpl implements TaskService {

    /**
     * Inject a variable of type UserRepository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Inject a variable of type TaskRepository.
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Inject a variable of type CommentRepository.
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * @return Returns all tasks from the database.
     */
    @Override
    @Transactional
    public List<TaskDto> findAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toTaskDto)
                .toList();
    }

    /**
     * @return Returns tasks from the database using Limit and Offset.
     */
    @Override
    @Transactional
    public Page<TaskDto> findAllTasksUsingLimitAndOffset(int offset, int limit) {
        return taskRepository.findAll(PageRequest.of(offset, limit))
                .map(TaskMapper::toTaskDto);
    }

    /**
     * @param id ID of the task you are looking for.
     * @return The method returns the object of the searched task by its ID.
     */
    @Override
    @Transactional
    public TaskDto findTaskById(int id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted(id))
        );
        return TaskMapper.toTaskDto(task);
    }

    /**
     * @param executorId ID of the executor you are looking for.
     * @return The method returns executors when doing this task.
     */
    @Override
    public List<TaskDto> findTasksByExecutor(int executorId) {
        List<Task> tasks = taskRepository.findAllExecutorTasks(executorId);
        return tasks.stream()
                .map(TaskMapper::toTaskDto)
                .toList();
    }

    /**
     * @param taskDto An object that will be saved in the database.
     */
    @Override
    @Transactional
    public TaskDto saveOrUpdateTask(@Valid TaskDto taskDto) {
        Task task = taskRepository.save(TaskMapper.toTask(taskDto));
        return TaskMapper.toTaskDto(task);
    }

    /**
     * @param id ID of the task who will be deleted from the database.
     */
    @Override
    @Transactional
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }

    /**
     * @param userAddCommentDto Dto object than will be used.
     * @param token             JWT token.
     * @return An object that will be saved in the database.
     */
    @Override
    @Transactional
    public TaskDto addCommentForTask(@Valid UserAddCommentDto userAddCommentDto, String token) {
        int taskId = userAddCommentDto.getTaskId();
        String message = userAddCommentDto.getMessage();
        User user = getUserByToken(token);
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted(taskId))
        );
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
     * @param commentId Comment Id.
     */
    @Override
    @Transactional
    public void deleteCommentForTask(int commentId) {
        commentRepository.deleteById(commentId);
    }

    /**
     * The method adds a executor to the task.
     *
     * @param userTaskDto - Dto for adding a task for User.
     */
    @Override
    @Transactional
    public TaskDto addExecutorForTask(UserTaskDto userTaskDto) {
        int userId = userTaskDto.getUserId();
        int taskId = userTaskDto.getTaskId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID.formatted(userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted(taskId))
        );
        task.addExecutor(user);
        return TaskMapper.toTaskDto(taskRepository.save(task));
    }

    /**
     * @param userId - User ID.
     * @param taskId - Task ID.
     */
    @Override
    @Transactional
    public void deleteExecutorForTask(int userId, int taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(TASK_NOT_FOUND_WITH_ID.formatted(taskId))
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID.formatted(userId))
        );
        task.getExecutors().remove(user);
        taskRepository.save(task);
    }

    /**
     * @return - Get a User object from a token.
     */
    private User getUserByToken(String token) {
        String username = new JwtTokenManager().getUsername(token);
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_WITH_USERNAME.formatted(username))
        );
    }

}
