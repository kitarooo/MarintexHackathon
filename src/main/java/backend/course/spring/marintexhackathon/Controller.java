package backend.course.spring.marintexhackathon;

import backend.course.spring.marintexhackathon.dto.MonitoringDto;
import backend.course.spring.marintexhackathon.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitoring")
public class Controller {

    private final MonitorService monitorService;

    @Autowired
    public Controller(MonitorService monitorService) {
        this.monitorService = monitorService;
    }



    @PostMapping("/insert")
    public ResponseEntity<String> insertMonitoringData(@RequestBody MonitoringDto monitorDto) {
        monitorService.insertMonitoringData(monitorDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Monitoring data inserted successfully");
    }




    @GetMapping("/all")
    public ResponseEntity<List<MonitoringDto>> getAllMonitoringData() {
        List<MonitoringDto> monitoringData = monitorService.getAllMonitoringData();
        return new ResponseEntity<>(monitoringData, HttpStatus.OK);
    }

}
