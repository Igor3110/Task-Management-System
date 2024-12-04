package by.trofimov.time_management_system.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Objects;
import java.time.LocalDateTime;

/**
 * Comment entity class.
 */
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(message, comment.message) &&
                Objects.equals(dateAdded, comment.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, dateAdded);
    }

}
