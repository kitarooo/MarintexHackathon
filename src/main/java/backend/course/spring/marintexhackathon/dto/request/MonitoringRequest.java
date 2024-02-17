package backend.course.spring.marintexhackathon.dto.request;

import backend.course.spring.marintexhackathon.enums.Ship;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonitoringRequest {
    double fuel;
    double solarBattery;
    double electricity;
    LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    Ship ship;
}
