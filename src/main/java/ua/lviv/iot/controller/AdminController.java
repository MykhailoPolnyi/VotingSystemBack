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

    @GetMapping(path = "/editable/{adminId}")
    public ResponseEntity<List<ElectionDto>> getEditableElectionList(@PathVariable Integer adminId) {
        List<ElectionDto> createdElections = electionService.getEditableElectionList(adminId);
        return ResponseEntity.ok(createdElections);
    }


    @PostMapping
    public ResponseEntity<DetailedElectionDto> createElection(@RequestBody DetailedElectionDto electionDto) {
        DetailedElectionDto createdElection = electionService.createElection(electionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElection);
    }

    @PutMapping("/{electionId}")
    public ResponseEntity<DetailedElectionDto> editElection(@RequestBody DetailedElectionDto electionDto,
                                                            @PathVariable Integer electionId) {
        Integer adminId = 1; // TODO Get adminId from JWT token
        if (adminService.canAdminEditElection(adminId, electionId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (!electionDto.getId().equals(electionId)){
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
    public ResponseEntity<?> deleteElectionById(@PathVariable Integer electionId){
        boolean deletionStatus = electionService.deleteElection(electionId);
        if (deletionStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
