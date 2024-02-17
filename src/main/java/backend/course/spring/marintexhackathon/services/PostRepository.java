package backend.course.spring.marintexhackathon.services;

import backend.course.spring.marintexhackathon.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
