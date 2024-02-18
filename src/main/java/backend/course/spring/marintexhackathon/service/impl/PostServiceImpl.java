package backend.course.spring.marintexhackathon.service.impl;

import backend.course.spring.marintexhackathon.dto.request.PostRequest;
import backend.course.spring.marintexhackathon.dto.response.PostResponse;
import backend.course.spring.marintexhackathon.entity.Post;
import backend.course.spring.marintexhackathon.entity.User;
import backend.course.spring.marintexhackathon.enums.Status;
import backend.course.spring.marintexhackathon.exception.BaseException;
import backend.course.spring.marintexhackathon.exception.NotFoundException;
import backend.course.spring.marintexhackathon.repository.PostRepository;
import backend.course.spring.marintexhackathon.repository.UserRepository;
import backend.course.spring.marintexhackathon.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public String createPost(PostRequest request) {
        User user = getAuthUser();

        Post post = Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .amountFuel(request.getAmountFuel())
                .status(request.getStatus())
                .start(request.getStart())
                .finish(request.getFinish())
                .build();

        user.getPosts().add(post);
        postRepository.save(post);
        userRepository.save(user);

        return "Пост успешно создан!";
    }

    @Override
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Пост не найден!"));

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .amountFuel(post.getAmountFuel())
                .status(post.getStatus())
                .start(post.getStart())
                .finish(post.getFinish())
                .build();
    }

    @Override
    public List<PostResponse> getAllIfPublic() {
        List<Post> posts = postRepository.findAll();
        List<PostResponse> result = new ArrayList<>();

        for (Post post : posts) {
            if (post.getStatus() == Status.PUBLIC) {
                result.add(PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .amountFuel(post.getAmountFuel())
                        .start(post.getStart())
                        .finish(post.getFinish())
                        .status(post.getStatus())
                        .build());
            } else {
                continue;
            }
        }

        return result;
    }

    @Override
    public String updatePostById(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Пост не найден!"));

        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setStatus(request.getStatus());
        post.setAmountFuel(request.getAmountFuel());
        post.setStart(request.getStart());
        post.setFinish(request.getFinish());

        postRepository.save(post);

        return "Данные успешно обновлены!";
    }

    @Override
    public String deletePostById(Long id) {
        User user = getAuthUser();
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Пост не найден!"));
        postRepository.deleteByUserIdAndPostId(user.getId(), id);
        postRepository.delete(post);

        return "Пост успешно удален!";
    }

    @Override
    public List<PostResponse> getPostsByUser() {
        User user = getAuthUser();
        List<PostResponse> result = new ArrayList<>();

        for (Post post : user.getPosts()) {
            result.add(PostResponse.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .description(post.getDescription())
                            .status(post.getStatus())
                            .amountFuel(post.getAmountFuel())
                            .start(post.getStart())
                            .finish(post.getFinish())
                            .username(user.getUsername())
                            .build());
        }

        return result;
    }

    private User getAuthUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}