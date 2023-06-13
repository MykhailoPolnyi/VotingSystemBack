package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<ElectionDto> createdElections = adminService.getCreatedElectionList(adminId);
        return ResponseEntity.ok(createdElections);
    }


    @PostMapping
    public ResponseEntity<DetailedElectionDto> createElection(@RequestBody DetailedElectionDto electionDto) {
        DetailedElectionDto createdElection = electionService.createElection(electionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElection);
    }

    @PutMapping("/{electionId}")
    public ResponseEntity<DetailedElectionDto> editElection(@RequestBody DetailedElectionDto electionDto,
                                                            @PathVariable Integer electionId,
                                                            @RequestParam Integer adminId) {
        DetailedElectionDto editedElection = electionService.updateElection(electionDto, electionId, adminId);
        if (editedElection != null) {
            return ResponseEntity.ok(editedElection);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(path = "/{electionId}")
    public ResponseEntity<?> deleteElectionById(@PathVariable Integer electionId){
        boolean deletionStatus = electionService.deleteElection(electionId);
        if (deletionStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
