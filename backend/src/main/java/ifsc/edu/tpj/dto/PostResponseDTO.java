package ifsc.edu.tpj.dto;

import java.time.LocalDateTime;
import java.util.List;

import ifsc.edu.tpj.model.Post;

public record PostResponseDTO (
    Long postId,
    String title,
    String body,
    List<String> tags,
    LocalDateTime createdAt,
    UserResponseDTO author
) {
    public static PostResponseDTO fromEntity(Post post){
        if (post == null) {
            return null;
        }

        return new PostResponseDTO(
          post.getPostId(),
          post.getTitle(),
          post.getBody(),
          post.getTags(),
          post.getCreatedAt(),
          UserResponseDTO.fromEntity(post.getAuthor()));

    }
}
