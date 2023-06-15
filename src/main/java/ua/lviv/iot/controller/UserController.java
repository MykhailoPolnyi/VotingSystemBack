package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.user.UserDto;
import ua.lviv.iot.model.user.cred.Authorities;
import ua.lviv.iot.security.SecurityUtils;
import ua.lviv.iot.security.jwt.JwtUtils;
import ua.lviv.iot.service.UserService;


@CrossOrigin(origins ="*")
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        var user = userService.findUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto,
                                            @PathVariable Integer id,
                                            @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!userDto.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }
        if (!userCred.getId().equals(userDto.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        var updatedUser = userService.updateUser(userDto);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id,
                                            @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!userCred.getId().equals(id)) {
            System.out.println("Trying to delete other user");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        System.out.println("Deleting user: " + userCred);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}