package ifsc.edu.tpj.service;

import ifsc.edu.tpj.dto.PostRequestDTO;
import ifsc.edu.tpj.model.Post;
import ifsc.edu.tpj.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public Post create(PostRequestDTO requestDTO) {
        var author = userService.findUserById(requestDTO.userId());
        return postRepository.save(Post.builder()
                        .title(requestDTO.title())
                        .tags(requestDTO.tags())
                        .body(requestDTO.body())
                        .author(author)
                .build());
    }
}
