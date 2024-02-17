package backend.course.spring.marintexhackathon.dto;

import backend.course.spring.marintexhackathon.entity.Monitoring;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class MonitoringDto {
    double fuelAmount;
    double solarBattery;
    double electricity;
    LocalDate createDate;

    public MonitoringDto() {
        createDate = LocalDate.now();
    }

}
