package backend.course.spring.marintexhackathon.services;

import backend.course.spring.marintexhackathon.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
