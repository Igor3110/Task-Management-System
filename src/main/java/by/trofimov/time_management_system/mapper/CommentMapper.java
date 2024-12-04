package by.trofimov.time_management_system.mapper;

import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.entity.Comment;
import by.trofimov.time_management_system.dto.CommentDto;

/**
 * Mapper for the Comment class.
 */
@UtilityClass
public class CommentMapper {

    /**
     * The method converts an object of the Comment class into an object of the CommentDto class.
     * @param comment An object of the Comment class.
     * @return Class CommentDto.
     */
    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setMessage(comment.getMessage());
        commentDto.setDateAdded(comment.getDateAdded());
        commentDto.setTask(comment.getTask());
        commentDto.setAuthor(comment.getAuthor());
        return commentDto;
    }

    /**
     * The method converts an object of the CommentDto class into an object of the Comment class.
     * @param commentDto An object of the CommentDto class.
     * @return Class Comment.
     */
    public static Comment toComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setMessage(commentDto.getMessage());
        comment.setDateAdded(commentDto.getDateAdded());
        comment.setTask(commentDto.getTask());
        comment.setAuthor(commentDto.getAuthor());
        return comment;
    }

}
