package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.model.user.cred.LoginDto;
import ua.lviv.iot.model.user.cred.LoginResponseDto;
import ua.lviv.iot.model.user.cred.UserCred;
import ua.lviv.iot.model.user.cred.UserCredDto;
import ua.lviv.iot.security.jwt.JwtUtils;
import ua.lviv.iot.service.AdminService;
import ua.lviv.iot.service.UserService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AccessController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AdminService adminService;

    @PostMapping(path = "/signup")
    public ResponseEntity<LoginResponseDto> register(@RequestBody UserCredDto userCredDto) {
        if (userService.userExists(userCredDto.getIdentityCode())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.createUser(userCredDto);

        var authResult = authenticate(userCredDto.getIdentityCode(), userCredDto.getPassword());

        return new ResponseEntity<>(authResult, HttpStatus.CREATED);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        var authResult = authenticate(loginDto.getIdentityCode(), loginDto.getPassword());

        return ResponseEntity.ok(authResult);
    }

    private LoginResponseDto authenticate(String identityCode, String password) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(identityCode, password)
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = jwtUtils.generateJwtToken(auth);

        var userCred = (UserCred) auth.getPrincipal();
        var isAdmin = adminService.isUserAdmin(userCred.getUser().getId());

        return LoginResponseDto.builder()
                .jwt(jwt)
                .id(userCred.getUser().getId())
                .isAdmin(isAdmin)
                .type("Bearer")
                .build();
    }
}
