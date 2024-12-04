package by.trofimov.time_management_system.repository;

import by.trofimov.time_management_system.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for the Comment entity.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
