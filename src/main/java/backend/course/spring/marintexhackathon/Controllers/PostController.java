package backend.course.spring.marintexhackathon.Controllers;

import backend.course.spring.marintexhackathon.entity.Post;
import backend.course.spring.marintexhackathon.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("get")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/get{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.findPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.savePost(post);
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

}