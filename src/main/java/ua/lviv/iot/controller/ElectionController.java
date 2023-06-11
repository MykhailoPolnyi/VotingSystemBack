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
    public ResponseEntity<List<ElectionDto>> getAllElectionList(@RequestParam String activityFilter,
                                                                @RequestParam Integer userId) {
        // TODO Implement method
        return null;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DetailedElectionDto> getElectionById(@PathVariable Integer id,
                                                               @RequestParam Integer userId) {
        // TODO Implement method
        return null;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ElectionResultDto> getElectionResult(@PathVariable Integer id,
                                                               @RequestParam Integer userId){
        // TODO Implement method
        return null;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ElectionAnalysisDto> getElectionAnalysis(@PathVariable Integer id) {
        // TODO Implement method
        return null;
    }
}
