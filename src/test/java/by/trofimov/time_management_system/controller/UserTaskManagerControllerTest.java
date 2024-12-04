package by.trofimov.time_management_system.controller;

import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpServletRequest;
import by.trofimov.time_management_system.service.UserTaskManagerService;
import by.trofimov.time_management_system.dto.ChangeStatusOfTaskDto;
import by.trofimov.time_management_system.dto.TaskDto;
import by.trofimov.time_management_system.dto.UserAddCommentDto;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTaskManagerControllerTest {

    @Mock
    private TaskDto taskDto;

    @Mock
    private Page<TaskDto> page;

    @Mock
    private ChangeStatusOfTaskDto changeStatusOfTaskDto;

    @Mock
    private UserAddCommentDto userAddCommentDto;

    @Mock
    private UserTaskManagerService userTaskManagerService;

    @InjectMocks
    private UserTaskManagerController userTaskManagerController;

    private static MockHttpServletRequest mockRequest;
    private final String token = "token";
    private final int taskId = 1;

    @BeforeAll
    public static void initMockRequest() {
        mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader("Authorization", "Bearer token");
    }

    @Test
    void testFindAllExecutorTasks() {
        when(userTaskManagerService.findAllExecutorTasks(token)).thenReturn(Collections.emptyList());
        userTaskManagerController.findAllExecutorTasks(mockRequest).getBody();
        verify(userTaskManagerService).findAllExecutorTasks(token);
    }

    @Test
    void testFindExecutorTasksByLimitAndOffset() {
        when(userTaskManagerService.findExecutorTasksByLimitAndOffset(0, 1, token)).thenReturn(page);
        userTaskManagerController.findExecutorTasksByLimitAndOffset(0, 1, mockRequest).getBody();
        verify(userTaskManagerService).findExecutorTasksByLimitAndOffset(0, 1, token);
    }

    @Test
    void findTaskWhereUserIsExecutorByTaskId() {
        when(userTaskManagerService.findTaskWhereUserIsExecutorByTaskId(taskId, token)).thenReturn(taskDto);
        userTaskManagerController.findTaskWhereUserIsExecutorByTaskId(taskId, mockRequest).getBody();
        verify(userTaskManagerService).findTaskWhereUserIsExecutorByTaskId(taskId, token);
    }

    @Test
    void testChangeStatusOfTask() {
        when(userTaskManagerService.changeStatusOfTask(changeStatusOfTaskDto, token)).thenReturn(taskDto);
        userTaskManagerController.changeStatusOfTask(changeStatusOfTaskDto, mockRequest).getBody();
        verify(userTaskManagerService).changeStatusOfTask(changeStatusOfTaskDto, token);
    }

    @Test
    void testAddCommentForTaskWhereUserIsExecutor() {
        when(userTaskManagerService.addCommentForTaskWhereUserIsExecutor(userAddCommentDto, token)).thenReturn(taskDto);
        userTaskManagerController.addCommentForTaskWhereUserIsExecutor(userAddCommentDto, mockRequest).getBody();
        verify(userTaskManagerService).addCommentForTaskWhereUserIsExecutor(userAddCommentDto, token);
    }

}
