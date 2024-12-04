package by.trofimov.time_management_system.entity;

import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

    private static final double EPS = 1e-9;
    private Task task;

    @Before
    public void createNewTask() {
        task = new Task();
    }

    @Test
    public void newTaskShouldHaveZeroId() {
        assertEquals(0, task.getId(), EPS);
    }

    @Test
    public void newTaskShouldHaveZeroName() {
        assertNull(task.getName());
    }

    @Test
    public void newTaskShouldHaveZeroAuthor() {
        assertNull(task.getAuthor());
    }

    @Test
    public void newTaskShouldHaveZeroTaskStatus() {
        assertNull(task.getTaskStatus());
    }

    @Test
    public void newTaskShouldHaveZeroTaskPriority() {
        assertNull(task.getTaskPriority());
    }

    @Test
    public void newTaskShouldHaveZeroDeadline() {
        assertNull(task.getTaskPriority());
    }

    @Test
    public void newTaskShouldHaveZeroTaskComments() {
        assertNull(task.getComments());
    }

    @Test
    public void newTaskShouldHaveZeroTaskExecutors() {
        assertNull(task.getExecutors());
    }

}
