package backend.course.spring.marintexhackathon.services;

import backend.course.spring.marintexhackathon.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}