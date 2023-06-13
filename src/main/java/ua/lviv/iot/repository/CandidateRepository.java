package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.election.candidate.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
