package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.service.ElectionResultService;
import ua.lviv.iot.service.ElectionService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;
    private final ElectionResultService electionResultService;

    @GetMapping
    public ResponseEntity<List<ElectionDto>> getAllElectionList() {
        List<ElectionDto> electionList = electionService.getAllElectionList();
        return ResponseEntity.ok(electionList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DetailedElectionDto> getElectionById(@PathVariable Integer id) {
        Integer userId = 1; // TODO Get userId from JWT token
        DetailedElectionDto election = electionService.findElectionById(id, userId);
        if (election != null) {
            return ResponseEntity.ok(election);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/result")
    public ResponseEntity<ElectionResultDto> getElectionResult(@PathVariable Integer id,
                                                               @RequestParam Integer userId){
        ElectionResultDto electionResult = electionResultService.getElectionResult(id, userId);
        if (electionResult != null) {
            return ResponseEntity.ok(electionResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}/analysis")
    public ResponseEntity<ElectionAnalysisDto> getElectionAnalysis(@PathVariable Integer id) {
        ElectionAnalysisDto electionAnalysis = electionResultService.getElectionAnalysis(id);
        if (electionAnalysis != null) {
            return ResponseEntity.ok(electionAnalysis);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
