package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.model.user.cred.UserCred;

public interface UserCredRepository extends JpaRepository<UserCred, Integer> {
}
