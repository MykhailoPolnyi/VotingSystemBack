package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.user.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
