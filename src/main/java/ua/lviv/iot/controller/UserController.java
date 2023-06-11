package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.user.UserDto;
import ua.lviv.iot.service.UserService;


@CrossOrigin
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return null;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> editUser(UserDto userDto, @PathVariable Integer id) {
        return null;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        return null;
    }
}
