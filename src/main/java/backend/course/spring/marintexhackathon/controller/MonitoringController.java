package backend.course.spring.marintexhackathon.controller;

import backend.course.spring.marintexhackathon.dto.request.MonitoringRequest;
import backend.course.spring.marintexhackathon.dto.response.MonitoringResponse;
import backend.course.spring.marintexhackathon.service.MonitoringService;
import backend.course.spring.marintexhackathon.service.impl.MonitoringServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/monitorings")
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringServiceImpl monitoringService;

    @PostMapping("/createMonitor")
    public String postMonitoring(@RequestBody MonitoringRequest request){
        return monitoringService.postMonitoring(request);
    }

    @GetMapping("/{id}")
    public MonitoringResponse getMonitoring(@PathVariable Long id){
        return monitoringService.getMonitoringResponse(id);
    }

    @GetMapping("/getAll")
    public List<MonitoringResponse> getAll() {
        return monitoringService.findAll();
    }
}
