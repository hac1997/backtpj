package ifsc.edu.tpj.dto;

import ifsc.edu.tpj.model.Comment;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        Long commentId,
        String body,
        UserResponseDTO author,
        LocalDateTime createdAt
) {
    public static CommentResponseDTO fromEntity(Comment comment) {
        if (comment == null) {
            return null;
        }
        return new CommentResponseDTO(
                comment.getCommentId(),
                comment.getBody(),
                UserResponseDTO.fromEntity(comment.getAuthor()),
                comment.getCreatedAt()
        );
    }
}
