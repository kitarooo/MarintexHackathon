package backend.course.spring.marintexhackathon.repo;

import backend.course.spring.marintexhackathon.entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring,Integer> {

}

