package backend.course.spring.marintexhackathon.repository;

import backend.course.spring.marintexhackathon.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM users_posts WHERE user_id= :userId AND posts_id= :postsId", nativeQuery = true)
    void deleteByUserIdAndPostId(@Param("userId") Long userId, @Param("postsId") Long postsId);
}
