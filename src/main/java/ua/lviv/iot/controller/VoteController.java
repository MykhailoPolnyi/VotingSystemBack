package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.vote.VoteDto;
import ua.lviv.iot.service.ElectionResultService;

@CrossOrigin
@RestController
@RequestMapping(path = "/vote")
@RequiredArgsConstructor
public class VoteController {
    private final ElectionResultService electionResultService;

    @PostMapping
    public ResponseEntity<VoteDto> vote(@RequestBody VoteDto voteDto) {
        VoteDto electionResult = electionResultService.addVote(voteDto);
        if (electionResult != null) {
            return ResponseEntity.ok(electionResult);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{electionId}")
    public ResponseEntity<?> cancelVote(@PathVariable Integer electionId) {
        Integer userId = 1; //TODO get userId from JWT token
        boolean cancellationStatus = electionResultService.removeVote(electionId, userId);
        if (cancellationStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
