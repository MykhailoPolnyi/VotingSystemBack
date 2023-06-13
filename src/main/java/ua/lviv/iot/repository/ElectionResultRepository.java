package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.result.ElectionResult;

import java.util.List;

@Repository
public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
    Integer sumVoteNumberByElectionId(Integer electionId);
    List<ElectionResult> findAllByElectionId(Integer electionId);
}
