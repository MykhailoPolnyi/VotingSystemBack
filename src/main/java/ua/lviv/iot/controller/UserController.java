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


@CrossOrigin
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        var user = userService.findUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> editUser(UserDto userDto,
                                            @PathVariable Integer id,
                                            @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userIdentity = jwtUtils.getUserNameFromJwtToken(authToken);
        if (userIdentity == null) {
            System.out.println("Cannot get user identity from token");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var userCred = userService.loadUserByUsername(userIdentity);
        if (userCred == null) {
            System.out.println("Cannot find user by identity: " + userIdentity);
            return ResponseEntity.notFound().build();
        }
        if (!userCred.getId().equals(id) || !userCred.getId().equals(userDto.getId())) {
            System.out.println("Trying to delete other user");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        var updatedUser = userService.updateUser(userDto);

        return ResponseEntity.ok(updatedUser);
    }
    // TODO: move user auth check logic and create admin check logic
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id,
                                            @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userIdentity = jwtUtils.getUserNameFromJwtToken(authToken);
        if (userIdentity == null) {
            System.out.println("Cannot get user identity from token");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var userCred = userService.loadUserByUsername(userIdentity);
        if (userCred == null) {
            System.out.println("Cannot find user by identity: " + userIdentity);
            return ResponseEntity.notFound().build();
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