package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.model.election.vote.VoteDto;
import ua.lviv.iot.service.ElectionResultService;

@CrossOrigin
@RestController
@RequestMapping(path = "/vote")
@RequiredArgsConstructor
public class VoteController {
    private final ElectionResultService electionResultService;

    @PostMapping
    public ResponseEntity<ElectionResultDto> vote(VoteDto voteDto) {
        // TODO Implement method
        return null;
    }

    @DeleteMapping(path = "/{electionId")
    public ResponseEntity<?> cancelVote(@PathVariable Integer electionId, Integer userId) {
        // TODO Implement method
        return null;
    }

}
