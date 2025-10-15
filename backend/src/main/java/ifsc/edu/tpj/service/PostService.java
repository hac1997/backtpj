package ifsc.edu.tpj.service;

import ifsc.edu.tpj.dto.PostRequestDTO;
import ifsc.edu.tpj.model.Post;
import ifsc.edu.tpj.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public Post create(PostRequestDTO requestDTO) {
        var author = userService.findUserById(requestDTO.userId());
        return postRepository.save(Post.builder()
                .title(requestDTO.title())
                .tags(requestDTO.tags())
                .body(requestDTO.body())
                .author(author)
                .build());
    }

    public Post update(Long id, PostRequestDTO requestDTO) {
        Post post = findById(id);

        if (!post.getAuthor().getUserId().equals(requestDTO.userId())) {
            throw new RuntimeException("Only the author can edit this post");
        }

        if (post.getCreatedAt().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new RuntimeException("Cannot edit post after 24 hours");
        }

        post.setTitle(requestDTO.title());
        post.setBody(requestDTO.body());
        post.setTags(requestDTO.tags());

        return postRepository.save(post);
    }

    public void delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }
}
