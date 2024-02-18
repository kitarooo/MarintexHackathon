package backend.course.spring.marintexhackathon.dto.response;

import backend.course.spring.marintexhackathon.entity.User;
import backend.course.spring.marintexhackathon.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    Long id;
    String title;
    LocalDate start;
    LocalDate finish;
    double amountFuel;
    String description;

    @Enumerated(EnumType.STRING)
    Status status;
}