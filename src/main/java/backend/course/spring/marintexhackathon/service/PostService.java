package backend.course.spring.marintexhackathon.service;

import backend.course.spring.marintexhackathon.dto.request.PostRequest;
import backend.course.spring.marintexhackathon.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    String createPost(PostRequest request);
    PostResponse getPostById(Long id);
    List<PostResponse> getAllIfPublic();
    String updatePostById(Long id, PostRequest request);
    String deletePostById(Long id);
}
