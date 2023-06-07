package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.election.result.ElectionResult;

@Repository
public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
}
