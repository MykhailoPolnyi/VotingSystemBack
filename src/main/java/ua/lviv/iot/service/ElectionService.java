package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.repository.CandidateRepository;
import ua.lviv.iot.repository.ElectionRepository;
import ua.lviv.iot.repository.ElectionResultRepository;
import ua.lviv.iot.repository.UserRepository;

import java.util.List;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class ElectionService {
    private final UserRepository userService;
    private final ElectionRepository electionService;
    private final CandidateRepository candidateRepository;
    private final ElectionResultRepository electionResultRepository;

    public List<ElectionDto> getAllElectionList() {
        // TODO Implement method
        return null;
    }

    public List<ElectionDto> getElectionListByActiveStatus(Boolean isActive) {
        // TODO Implement method
        return null;
    }

    public DetailedElectionDto findElectionById(Integer id) {
        // TODO Implement method
        return null;
    }

    public DetailedElectionDto createElection(DetailedElectionDto electionDto) {
        // TODO Implement method
        return null;
    }

    public DetailedElectionDto updateElection(DetailedElectionDto electionDto, Integer electionId, Integer adminId) {
        // TODO Implement method
        return null;
    }

    public Boolean deleteElection(Integer id) {
        // TODO Implement method
        return null;
    }
  }
