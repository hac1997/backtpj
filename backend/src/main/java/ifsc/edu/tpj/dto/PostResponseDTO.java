package ifsc.edu.tpj.dto;

import ifsc.edu.tpj.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(
        Long postId,
        String title,
        String body,
        List<String> tags,
        UserResponseDTO author,
        LocalDateTime createdAt
) {
    public static PostResponseDTO fromEntity(Post post) {
        if (post == null) {
            return null;
        }
        return new PostResponseDTO(
                post.getPostId(),
                post.getTitle(),
                post.getBody(),
                post.getTags(),
                UserResponseDTO.fromEntity(post.getAuthor()),
                post.getCreatedAt()
        );
    }
}
