package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
