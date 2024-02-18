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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements MonitoringService {

    private final MonitoringRepository monitoringRepository;
    private final UserRepository userRepository;

    @Override
    public String postMonitoring(MonitoringRequest monitoringRequest) {
        User user = getAuthUser();

        Monitoring monitoring = Monitoring.builder()
                .electricity(monitoringRequest.getElectricity())
                .fuel(monitoringRequest.getFuel())
                .solarBattery(monitoringRequest.getSolarBattery())
                .createdDate(LocalDate.now())
                .ship(monitoringRequest.getShip())
                .build();


        user.getMonitorings().add(monitoring);

        userRepository.save(user);
        monitoringRepository.save(monitoring);

        if (monitoring.getFuel() >= 100 || monitoring.getElectricity() >= 100 || monitoring.getSolarBattery() >= 100) {
            return "Вы исчерпали энергии больше нормы!";
        } else {
            return "Мониторинг успешно создан!";
        }
    }

    @Override
    public MonitoringResponse getMonitoringResponse(Long id) {
        Monitoring monitor = monitoringRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("мониторинг не найден!"));

        return MonitoringResponse.builder()
                .id(monitor.getId())
                .fuel(monitor.getFuel())
                .solarBattery(monitor.getSolarBattery())
                .createdDate(monitor.getCreatedDate())
                .electricity(monitor.getElectricity())
                .ship(monitor.getShip())
                .build();

    }

    @Override
    public List<MonitoringResponse> findAll() {
        List<Monitoring> monitorings = monitoringRepository.findAll();
        List<MonitoringResponse> result = new ArrayList<>();

        for (Monitoring monitoring : monitorings) {
            result.add(MonitoringResponse.builder()
                    .id(monitoring.getId())
                    .ship(monitoring.getShip())
                    .solarBattery(monitoring.getSolarBattery())
                    .fuel(monitoring.getFuel())
                    .electricity(monitoring.getElectricity())
                    .createdDate(monitoring.getCreatedDate())
                    .build());
        }

        return result;
    }

    private User getAuthUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}