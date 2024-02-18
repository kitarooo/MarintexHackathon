package backend.course.spring.marintexhackathon.controller;

import backend.course.spring.marintexhackathon.dto.request.PostRequest;
import backend.course.spring.marintexhackathon.dto.response.PostResponse;
import backend.course.spring.marintexhackathon.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl postService;

    @PostMapping("/create")
    public String createPost(@RequestBody PostRequest request) {
        return postService.createPost(request);
    }

    @PatchMapping("/update/{id}")
    public String updatePost(@RequestBody PostRequest request, @PathVariable Long id) {
        return postService.updatePostById(id, request);
    }

    @DeleteMapping("{id}")
    public String deletePost(@PathVariable Long id) {
        return postService.deletePostById(id);
    }

    @GetMapping("{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public List<PostResponse> getAllPosts() {
        return postService.getAllIfPublic();
    }

    @GetMapping("/get")
    public List<PostResponse> getMyPosts() {
        return postService.getPostsByUser();
    }

}
