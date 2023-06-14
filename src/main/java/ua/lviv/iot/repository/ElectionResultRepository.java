package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.election.result.ElectionResult;
import ua.lviv.iot.model.election.result.ElectionResultId;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ElectionResultRepository extends JpaRepository<ElectionResult, ElectionResultId> {
    @Query(value = "SELECT SUM(er.voteNumber) " +
            "FROM ElectionResult er " +
            "WHERE er.candidate.election.id = :electionId " +
            "GROUP BY er.candidate.id")
    Integer countVoteByElectionId(@Param("electionId") Integer electionId);

    @Query(value = "SELECT er FROM ElectionResult er " +
            "WHERE er.candidate.election.id = :electionId")
    List<ElectionResult> findAllByElectionId(@Param("electionId") Integer electionId);

    @Query(value = "SELECT COUNT(er) > 0 " +
            "FROM ElectionResult er " +
            "WHERE er.elector.id = :userId " +
            "AND er.candidate.election.id = :electionId")
    Boolean existsByUserIdAndElectionId(@Param("userId") Integer userId,
                                        @Param("electionId") Integer electionId);

    @Query(value = "SELECT er FROM ElectionResult er " +
            "WHERE er.elector.id = :userId " +
            "AND er.candidate.election.id = :electionId")
    List<ElectionResult> findByUserIdAndElectionId(@Param("userId") Integer userId,
                                                   @Param("electionId") Integer electionId);
}
