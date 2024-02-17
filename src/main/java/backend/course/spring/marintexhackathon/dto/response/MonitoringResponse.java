package backend.course.spring.marintexhackathon.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonitoringResponse {
    Long id;
    double fuel;
    double solarBattery;
    double electricity;
    LocalDate createdDate;
}
