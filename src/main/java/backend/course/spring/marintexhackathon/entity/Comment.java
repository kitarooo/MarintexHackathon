package backend.course.spring.marintexhackathon.entity;

import backend.course.spring.marintexhackathon.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {
    Integer AuthorID;
    Integer PostID;
    Integer LikeCNT;
    Integer DisLikeCNT;
    String Content;
    String AuthorUserName;
}
