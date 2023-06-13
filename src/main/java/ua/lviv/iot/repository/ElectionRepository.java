package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.election.Election;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Integer> {
    @Query(value = "SELECT e FROM Election e " +
            "WHERE e.startDate < current_date " +
            "AND e.endDate > current_date")
    List<Election> findActiveElectionList();

    @Query(value = "SELECT e FROM Election e " +
            "WHERE e.endDate < current_date")
    List<Election> findClosedElectionList();

    @Query(value = "SELECT e FROM Election e " +
            "WHERE e.admin.id = :adminId " +
            "AND e.startDate > current_date")
    List<Election> findEditableElectionList(@Param("adminId") Integer adminId);
}
