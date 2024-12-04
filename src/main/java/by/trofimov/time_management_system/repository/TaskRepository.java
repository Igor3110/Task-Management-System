package by.trofimov.time_management_system.repository;

import java.util.List;
import by.trofimov.time_management_system.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository class for the Task entity.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    /**
     * Query to search for all tasks of a given user.
     */
    String FIND_ALL_EXECUTOR_TASKS = """
               SELECT *
               FROM tasks
               WHERE id IN (
                    SELECT task_id
                    FROM employee_task
                    WHERE employee_id = :userId
               )
            """;

    /**
     * A query to search for a given user's task by ID.
     */
    String FIND_EXECUTOR_TASK_BY_ID = """
               SELECT *
               FROM tasks
               WHERE id = (
                    SELECT task_id
                    FROM employee_task
                    WHERE employee_id = :userId AND task_id = :taskId
                    LIMIT 1
               )
            """;

    @Query(value = FIND_ALL_EXECUTOR_TASKS, nativeQuery = true)
    List<Task> findAllExecutorTasks(@Param("userId") Integer userId);

    @Query(value = FIND_ALL_EXECUTOR_TASKS, nativeQuery = true)
    Page<Task> findExecutorTasksByLimitAndOffset(PageRequest pageRequest, @Param("userId") Integer userId);

    @Query(value = FIND_EXECUTOR_TASK_BY_ID, nativeQuery = true)
    Task findTaskWhereUserIsExecutorByTaskId(
                @Param("userId") Integer userId,
                @Param("taskId") Integer taskId
            );

}
