package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.security.SecurityUtils;
import ua.lviv.iot.service.ElectionResultService;
import ua.lviv.iot.service.ElectionService;
import ua.lviv.iot.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;
    private final ElectionResultService electionResultService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ElectionDto>> getAllElectionList(@RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        if (userService.getUserFromAuthToken(authToken) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<ElectionDto> electionList = electionService.getAllElectionList();
        return ResponseEntity.ok(electionList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DetailedElectionDto> getElectionById(@PathVariable Integer id,
                                                               @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        DetailedElectionDto election = electionService.findElectionById(id, userCred.getId());
        if (election != null) {
            return ResponseEntity.ok(election);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/result")
    public ResponseEntity<ElectionResultDto> getElectionResult(@PathVariable Integer id,
                                                               @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ElectionResultDto electionResult = electionResultService.getElectionResult(id, userCred.getId());
        if (electionResult != null) {
            return ResponseEntity.ok(electionResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/analysis")
    public ResponseEntity<ElectionAnalysisDto> getElectionAnalysis(@PathVariable Integer id,
                                                                   @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        if (userService.getUserFromAuthToken(authToken) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ElectionAnalysisDto electionAnalysis = electionResultService.getElectionAnalysis(id);
        if (electionAnalysis != null) {
            return ResponseEntity.ok(electionAnalysis);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
