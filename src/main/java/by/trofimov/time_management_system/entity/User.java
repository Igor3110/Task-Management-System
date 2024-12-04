package by.trofimov.time_management_system.entity;

import lombok.*;
import java.util.*;
import jakarta.persistence.*;

/**
 * User entity class.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "executors", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Set<Task> tasks;

    /**
     * The method adds a task to the User.
     *
     * @param task A task that will be added to the user.
     */
    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new HashSet<>();
        }
        tasks.add(task);
        task.setAuthor(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }

}
