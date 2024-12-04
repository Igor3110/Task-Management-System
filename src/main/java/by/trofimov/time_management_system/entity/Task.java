package by.trofimov.time_management_system.entity;

import lombok.*;
import java.util.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.type.SqlTypes;
import org.hibernate.annotations.JdbcTypeCode;

/**
 * Task entity class.
 */
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", columnDefinition = "task_status_enum")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_priority", columnDefinition = "task_priority_enum")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private TaskPriority taskPriority;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<Comment> comments;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " employee_id", referencedColumnName = "id"))
    @ToString.Exclude
    private Set<User> executors;

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        comments.add(comment);
        comment.setTask(this);
    }

    public void addExecutor(User executor) {
        if (executors == null) {
            executors = new HashSet<>();
        }
        executors.add(executor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) &&
                Objects.equals(author, task.author) && taskStatus == task.taskStatus &&
                taskPriority == task.taskPriority && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, taskStatus, taskPriority, deadline);
    }

}
