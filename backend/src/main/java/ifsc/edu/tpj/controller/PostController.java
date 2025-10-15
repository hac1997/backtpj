package ifsc.edu.tpj.controller;

import ifsc.edu.tpj.model.Post;

import ifsc.edu.tpj.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // @GetMapping
    // public List<Post> findAll() {
    // }

    // @GetMapping("/{id}")
    // public Post findById(@PathVariable Long id) {
    // }

    // @PostMapping
    // public Post create(@RequestBody Post post) {
    //     return postService.create(post);
    // }

    // @PutMapping("/{id}")
    // public Post update(@PathVariable Long id, @RequestBody Post updatedPost) {
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }
}
