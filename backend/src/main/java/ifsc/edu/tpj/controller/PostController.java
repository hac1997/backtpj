package ifsc.edu.tpj.controller;

import ifsc.edu.tpj.dto.PostRequestDTO;
import ifsc.edu.tpj.dto.PostResponseDTO;
import ifsc.edu.tpj.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> findAll() {
        List<PostResponseDTO> posts = postService.findAll().stream()
                .map(PostResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(PostResponseDTO.fromEntity(postService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostRequestDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PostResponseDTO.fromEntity(postService.create(postDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PostRequestDTO postDTO) {
        return ResponseEntity.ok(PostResponseDTO.fromEntity(postService.update(id, postDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
