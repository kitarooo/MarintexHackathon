package backend.course.spring.marintexhackathon.entity;

import backend.course.spring.marintexhackathon.entity.base_entity.BaseEntity;
import backend.course.spring.marintexhackathon.enums.Ship;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "monitorings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Monitoring extends BaseEntity {
    double fuel;
    double solarBattery;
    double electricity;
    LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    Ship ship;
}
