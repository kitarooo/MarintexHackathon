package backend.course.spring.marintexhackathon.repository;

import backend.course.spring.marintexhackathon.entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring,Long> {
}
