package backend.course.spring.marintexhackathon.entity;

import backend.course.spring.marintexhackathon.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Monitoring extends BaseEntity {
    double fuelAmount;
    double solarBattery;
    double electricity;
    LocalDate createdDate;
}
