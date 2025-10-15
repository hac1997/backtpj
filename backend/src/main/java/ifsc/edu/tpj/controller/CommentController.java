package ifsc.edu.tpj.controller;

import ifsc.edu.tpj.model.Comment;

import ifsc.edu.tpj.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // @GetMapping
    // public List<Comment> findAll() {
    // }

    // @GetMapping("/{id}")
    // public Comment findById(@PathVariable Long id) {

    // }

    // @PostMapping
    // public Comment create(@RequestBody Comment comment) {
    // }

    // @PutMapping("/{id}")
    // public Comment update(@PathVariable Long id, @RequestBody Comment updatedComment) {
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
}
