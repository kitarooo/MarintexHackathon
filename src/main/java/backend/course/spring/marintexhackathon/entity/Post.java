package backend.course.spring.marintexhackathon.entity;

import backend.course.spring.marintexhackathon.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post extends BaseEntity {
    LocalDateTime duringPeriod;
    LocalDateTime fullPeriod;
    double amountFuel;
    String description;
    //merge

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    User user;

}
