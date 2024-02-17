package backend.course.spring.marintexhackathon.service.impl;

import backend.course.spring.marintexhackathon.dto.request.MonitoringRequest;
import backend.course.spring.marintexhackathon.dto.response.MonitoringResponse;
import backend.course.spring.marintexhackathon.entity.Monitoring;
import backend.course.spring.marintexhackathon.entity.User;
import backend.course.spring.marintexhackathon.exception.NotFoundException;
import backend.course.spring.marintexhackathon.repository.MonitoringRepository;
import backend.course.spring.marintexhackathon.repository.UserRepository;
import backend.course.spring.marintexhackathon.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements MonitoringService {

    private final MonitoringRepository monitoringRepository;
    private final UserRepository userRepository;

    @Override
    public String postMonitoring(MonitoringRequest monitoringRequest) {
        User user = getAuthUser();

        Monitoring monitoring = Monitoring.builder()
                .fuel(monitoringRequest.getFuel())
                .solarBattery(monitoringRequest.getSolarBattery())
                .electricity(monitoringRequest.getElectricity())
                .createdDate(LocalDate.now()).build();


        user.getMonitorings().add(monitoring);

        userRepository.save(user);
        monitoringRepository.save(monitoring);

        return "Мониторинг успешно создан!";
    }

    @Override
    public MonitoringResponse getMonitoringResponse(Long id) {
        Monitoring monitor = monitoringRepository.findById(id).orElseThrow(() -> new NotFoundException("мониторинг не найден!"));

        return MonitoringResponse.builder()
                .id(monitor.getId())
                .fuel(monitor.getFuel())
                .solarBattery(monitor.getSolarBattery())
                .createdDate(monitor.getCreatedDate())
                .build();
    }

    private User getAuthUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
