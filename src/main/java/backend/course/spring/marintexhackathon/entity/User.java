package backend.course.spring.marintexhackathon.entity;

import backend.course.spring.marintexhackathon.entity.base_entity.BaseEntity;
import backend.course.spring.marintexhackathon.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {
    String email;
    String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Ship> ships;

    @Enumerated(EnumType.STRING)
    Role role;
}
