package by.trofimov.time_management_system.mapper;

import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.entity.Comment;
import by.trofimov.time_management_system.dto.CommentInfoDto;

/**
 * Mapper for the Comment class.
 */
@UtilityClass
public class CommentInfoMapper {

    /**
     * The method converts an object of the Comment class into an object of the CommentInfoDto class.
     * @param comment An object of the Comment class.
     * @return Class CommentInfoDto.
     */
    public static CommentInfoDto toCommentInfoDto(Comment comment) {
        CommentInfoDto commentInfoDto = new CommentInfoDto();
        commentInfoDto.setId(comment.getId());
        commentInfoDto.setMessage(comment.getMessage());
        commentInfoDto.setAuthorId(comment.getAuthor().getId());
        commentInfoDto.setAuthorUsername(comment.getAuthor().getUsername());
        return commentInfoDto;
    }

    /**
     * The method converts an object of the CommentInfoDto class into an object of the Comment class.
     * @param commentInfoDto An object of the CommentInfoDto class.
     * @return Class Comment.
     */
    public static Comment toComment(CommentInfoDto commentInfoDto) {
        Comment comment = new Comment();
        comment.setId(commentInfoDto.getId());
        comment.setMessage(commentInfoDto.getMessage());
        return comment;
    }

}
