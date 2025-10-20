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

    public Post findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post não encontrado" + id));
    }

    public Post update(Long id, PostRequestDTO dto){
        Post post = findById(id);

        if (!post.getAuthor().getUserId().equals(dto.userId())) {
            throw new RuntimeException("somente autores podem editar o próprio post");
        }

        post.setBody(dto.body());
        post.setTitle(dto.title());
        post.setTags(dto.tags());
        
        return postRepository.save(post);
    
    }

    // futuramente, quando implementarmos as ferramentas de moderação,
    // devemos incluir a possibilidade de deletar ou
    // tirar fora de visualização um comentário
    public Post delete(Long id){
        Post post = findById(id);
        postRepository.delete(post);
        return post;
    }
}
