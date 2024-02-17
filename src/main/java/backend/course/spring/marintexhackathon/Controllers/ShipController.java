package backend.course.spring.marintexhackathon.Controllers;

import backend.course.spring.marintexhackathon.entity.Ship;
import backend.course.spring.marintexhackathon.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }



    @PostMapping("/create")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        Ship savedShip = shipService.saveShip(ship);
        return ResponseEntity.ok(savedShip);
    }

}
