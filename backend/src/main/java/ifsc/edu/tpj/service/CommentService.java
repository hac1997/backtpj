package ifsc.edu.tpj.service;

import ifsc.edu.tpj.dto.CommentRequestDTO;
import ifsc.edu.tpj.model.Comment;
import ifsc.edu.tpj.model.Post;
import ifsc.edu.tpj.repository.CommentRepository;
import ifsc.edu.tpj.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostRepository postRepository;

    public List<Comment> findByPost(Long postId) {
        return commentRepository.findByPost_PostId(postId);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    public Comment create(Long postId, CommentRequestDTO dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        return commentRepository.save(Comment.builder()
                .author(userService.findUserById(dto.userId()))
                .body(dto.body())
                .post(post)
                .build());
    }

    public Comment update(Long id, CommentRequestDTO dto) {
        Comment comment = findById(id);

        if (!comment.getAuthor().getUserId().equals(dto.userId())) {
            throw new RuntimeException("Only the author can edit this comment");
        }

        if (comment.getCreatedAt().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new RuntimeException("Cannot edit comment after 24 hours");
        }

        comment.setBody(dto.body());
        return commentRepository.save(comment);
    }

    public void delete(Long id) {
        Comment comment = findById(id);
        commentRepository.delete(comment);
    }
}
