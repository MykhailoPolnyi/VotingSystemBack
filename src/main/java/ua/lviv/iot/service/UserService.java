package ua.lviv.iot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.user.UserDto;
import ua.lviv.iot.repository.UserRepository;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto findUser(Integer id) {
        // TODO Implement method
        return null;
    }

    public UserDto updateUser(UserDto userDto) {
        // TODO Implement method
        return null;
    }

    public Boolean deleteUser(Integer id) {
        // TODO Implement method
        return null;
    }
}
