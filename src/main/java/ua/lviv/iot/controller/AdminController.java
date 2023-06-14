package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.security.SecurityUtils;
import ua.lviv.iot.security.jwt.JwtUtils;
import ua.lviv.iot.service.AdminService;
import ua.lviv.iot.service.ElectionService;
import ua.lviv.iot.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/election")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    private final ElectionService electionService;
    private final JwtUtils jwtUtils;

    @GetMapping(path = "/editable")
    public ResponseEntity<List<ElectionDto>> getEditableElectionList(@RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!adminService.isUserAdmin(userCred.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<ElectionDto> createdElections = electionService.getEditableElectionList(userCred.getId());
        return ResponseEntity.ok(createdElections);
    }


    @PostMapping
    public ResponseEntity<DetailedElectionDto> createElection(@RequestBody DetailedElectionDto electionDto,
                                                              @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        if (userService.getUserFromAuthToken(authToken) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        DetailedElectionDto createdElection = electionService.createElection(electionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElection);
    }

    @PutMapping("/{electionId}")
    public ResponseEntity<DetailedElectionDto> editElection(@RequestBody DetailedElectionDto electionDto,
                                                            @PathVariable Integer electionId,
                                                            @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!adminService.isUserAdmin(userCred.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (adminService.canAdminEditElection(userCred.getId(), electionId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (!electionDto.getId().equals(electionId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DetailedElectionDto editedElection = electionService.updateElection(electionDto);
        if (editedElection != null) {
            return ResponseEntity.ok(editedElection);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(path = "/{electionId}")
    public ResponseEntity<?> deleteElectionById(@PathVariable Integer electionId,
                                                @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!adminService.isUserAdmin(userCred.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        boolean deletionStatus = electionService.deleteElection(electionId);
        if (deletionStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
