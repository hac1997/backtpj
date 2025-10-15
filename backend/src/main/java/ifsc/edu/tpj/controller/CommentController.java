package ifsc.edu.tpj.controller;

import ifsc.edu.tpj.dto.CommentRequestDTO;
import ifsc.edu.tpj.model.Comment;
import ifsc.edu.tpj.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> findByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findByPost(postId));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> create(@PathVariable Long postId, @Valid @RequestBody CommentRequestDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(postId, commentDTO));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.update(id, commentDTO));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
