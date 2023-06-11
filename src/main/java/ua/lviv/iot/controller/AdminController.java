package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.service.AdminService;
import ua.lviv.iot.service.ElectionService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/election")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final ElectionService electionService;

    @GetMapping(path = "/created/{adminId}")
    public ResponseEntity<List<ElectionDto>> getCreatedElectionList(@PathVariable Integer adminId) {
        // TODO Implement method
        return null;
    }


    @PostMapping
    public ResponseEntity<DetailedElectionDto> createElection(@RequestBody DetailedElectionDto electionDto) {
        // TODO Implement method
        return null;
    }

    @PutMapping("/{electionId}")
    public ResponseEntity<DetailedElectionDto> editElection(@RequestBody DetailedElectionDto electionDto,
                                                            @PathVariable Integer electionId,
                                                            @RequestParam Integer adminId) {
        // TODO Implement method
        return null;
    }

    @DeleteMapping(path = "/{electionId}")
    public ResponseEntity<?> deleteElectionById(@PathVariable Integer electionId,
                                                @RequestParam Integer userId){
        // TODO Implement method
        return null;
    }
}
