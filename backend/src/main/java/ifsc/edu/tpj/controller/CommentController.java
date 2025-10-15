package ifsc.edu.tpj.controller;

import ifsc.edu.tpj.dto.CommentRequestDTO;
import ifsc.edu.tpj.dto.CommentResponseDTO;
import ifsc.edu.tpj.service.CommentService;
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
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> findByPost(@PathVariable Long postId) {
        List<CommentResponseDTO> comments = commentService.findByPost(postId).stream()
                .map(CommentResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(CommentResponseDTO.fromEntity(commentService.findById(id)));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> create(@PathVariable Long postId, @Valid @RequestBody CommentRequestDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommentResponseDTO.fromEntity(commentService.create(postId, commentDTO)));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO commentDTO) {
        return ResponseEntity.ok(CommentResponseDTO.fromEntity(commentService.update(id, commentDTO)));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
