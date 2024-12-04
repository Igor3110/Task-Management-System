package by.trofimov.time_management_system.entity;

import org.junit.*;
import static org.junit.Assert.*;

public class CommentTest {

    private static final double EPS = 1e-9;
    private Comment comment;

    @Before
    public void createNewComment() {
        comment = new Comment();
    }

    @Test
    public void newCommentShouldHaveZeroId() {
        assertEquals(0, comment.getId(), EPS);
    }

    @Test
    public void newCommentShouldHaveZeroMessage() {
        assertNull(comment.getMessage());
    }

    @Test
    public void newCommentShouldHaveZeroDateAdded() {
        assertNull(comment.getDateAdded());
    }

    @Test
    public void newCommentShouldHaveZeroTask() {
        assertNull(comment.getTask());
    }

    @Test
    public void newCommentShouldHaveZeroAuthor() {
        assertNull(comment.getAuthor());
    }

}
