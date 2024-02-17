package backend.course.spring.marintexhackathon.service;

import backend.course.spring.marintexhackathon.dto.MonitoringDto;
import backend.course.spring.marintexhackathon.entity.Monitoring;
import backend.course.spring.marintexhackathon.repo.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorService {

    private final MonitoringRepository monitorRepo;

    @Autowired
    public MonitorService(MonitoringRepository monitorRepo) {
        this.monitorRepo = monitorRepo;
    }

    public void insertMonitoringData(MonitoringDto monitorDto) {
        monitorDto.setCreateDate(LocalDate.now());

        Monitoring monitoring = Monitoring.builder()
                .fuelAmount(monitorDto.getFuelAmount())
                .electricity(monitorDto.getElectricity())
                .solarBattery(monitorDto.getSolarBattery())
                .createdDate(monitorDto.getCreateDate())
                .build();
        monitorRepo.save(monitoring);
    }

    public List<MonitoringDto> getAllMonitoringData() {
        return monitorRepo.findAll().stream()
                .map(monitoring -> MonitoringDto.builder()
                        .fuelAmount(monitoring.getFuelAmount())
                        .electricity(monitoring.getElectricity())
                        .solarBattery(monitoring.getSolarBattery())
                        .createDate(monitoring.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

}
