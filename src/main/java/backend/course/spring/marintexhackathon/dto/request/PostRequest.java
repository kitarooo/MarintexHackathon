package backend.course.spring.marintexhackathon.dto.request;

import backend.course.spring.marintexhackathon.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {
    String title;
    LocalDate start;
    LocalDate finish;
    double amountFuel;
    String description;
    boolean isPublic;

    @Enumerated(EnumType.STRING)
    Status status;
}
