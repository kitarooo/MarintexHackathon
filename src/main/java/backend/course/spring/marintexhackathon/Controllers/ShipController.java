package backend.course.spring.marintexhackathon.Controllers;

import backend.course.spring.marintexhackathon.entity.Ship;
import backend.course.spring.marintexhackathon.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }
    @GetMapping("/get")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> ships = shipService.findAllShips();
        return ResponseEntity.ok(ships);
    }

    @GetMapping("/get{id}")
    public ResponseEntity<Ship> getShipByID(@PathVariable long id) {
        return shipService.findShipByID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        Ship savedShip = shipService.saveShip(ship);
        return ResponseEntity.ok(savedShip);
    }

}
