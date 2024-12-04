package by.trofimov.time_management_system.controller;

import java.util.Collections;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpServletRequest;
import by.trofimov.time_management_system.dto.TaskDto;
import by.trofimov.time_management_system.dto.UserTaskDto;
import by.trofimov.time_management_system.dto.UserAddCommentDto;
import by.trofimov.time_management_system.service.TaskService;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private Page<TaskDto> page;

    @Mock
    private TaskDto taskDto;

    @Mock
    private UserTaskDto userTaskDto;

    @Mock
    private UserAddCommentDto userAddCommentDto;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private static MockHttpServletRequest mockRequest;
    private final String token = "token";
    private final int userId = 1;
    private final int executorId = 1;
    private final int taskId = 1;
    private final int commentId = 1;

    @BeforeAll
    public static void initMockRequest() {
        mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(AUTHORIZATION, "Bearer token");
    }

    @Test
    void testFindAllTasks() {
        when(taskService.findAllTasks()).thenReturn(Collections.emptyList());
        taskController.findAllTasks().getBody();
        verify(taskService).findAllTasks();
    }

    @Test
    void testFindAllTasksUsingLimitAndOffset() {
        when(taskService.findAllTasksUsingLimitAndOffset(0, 1)).thenReturn(page);
        taskController.findAllTasksUsingLimitAndOffset(0, 1).getBody();
        verify(taskService).findAllTasksUsingLimitAndOffset(0, 1);
    }

    @Test
    void testFindTasksByExecutor() {
        when(taskService.findTasksByExecutor(executorId)).thenReturn(Collections.emptyList());
        taskController.findTasksByExecutor(executorId).getBody();
        verify(taskService).findTasksByExecutor(executorId);
    }

    @Test
    void testFindTaskById() {
        when(taskService.findTaskById(taskId)).thenReturn(taskDto);
        taskController.findTaskById(taskId);
        verify(taskService).findTaskById(taskId);
    }

    @Test
    void testSaveTask() {
        when(taskService.saveOrUpdateTask(taskDto)).thenReturn(taskDto);
        taskController.saveTask(taskDto);
        verify(taskService).saveOrUpdateTask(taskDto);
    }

    @Test
    void testUpdateTask() {
        when(taskService.saveOrUpdateTask(taskDto)).thenReturn(taskDto);
        taskController.updateTask(taskDto);
        verify(taskService).saveOrUpdateTask(taskDto);
    }

    @Test
    void testDeleteTask() {
        taskController.deleteTask(taskId);
        verify(taskService).deleteTaskById(taskId);
    }

    @Test
    void testAddCommentForTask() {
        when(taskService.addCommentForTask(userAddCommentDto, token)).thenReturn(taskDto);
        taskController.addCommentForTask(userAddCommentDto, mockRequest);
        verify(taskService).addCommentForTask(userAddCommentDto, token);
    }

    @Test
    void testDeleteCommentForTask() {
        taskController.deleteCommentForTask(commentId);
        verify(taskService).deleteCommentForTask(commentId);
    }

    @Test
    void testAddExecutorForTask() {
        when(taskService.addExecutorForTask(userTaskDto)).thenReturn(taskDto);
        taskController.addExecutorForTask(userTaskDto);
        verify(taskService).addExecutorForTask(userTaskDto);
    }

    @Test
    void testDeleteExecutorForTask() {
        taskController.deleteExecutorForTask(userId, taskId);
        verify(taskService).deleteExecutorForTask(userId, taskId);
    }

}
