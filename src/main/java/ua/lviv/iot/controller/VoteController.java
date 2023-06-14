package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.vote.VoteDto;
import ua.lviv.iot.security.SecurityUtils;
import ua.lviv.iot.service.ElectionResultService;
import ua.lviv.iot.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/vote")
@RequiredArgsConstructor
public class VoteController {
    private final ElectionResultService electionResultService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<VoteDto> vote(VoteDto voteDto,
                                        @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!userCred.getId().equals(voteDto.getUserId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        VoteDto electionResult = electionResultService.addVote(voteDto);
        if (electionResult != null) {
            return ResponseEntity.ok(electionResult);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{electionId}")
    public ResponseEntity<?> cancelVote(@PathVariable Integer electionId,
                                        @RequestHeader(name = SecurityUtils.AUTH_HEADER) String authToken) {
        var userCred = userService.getUserFromAuthToken(authToken);
        if (userCred == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean cancellationStatus = electionResultService.removeVote(electionId, userCred.getId());
        if (cancellationStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
