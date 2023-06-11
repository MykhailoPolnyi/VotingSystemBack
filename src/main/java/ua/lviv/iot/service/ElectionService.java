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
        return null;
    }

    public List<ElectionDto> getElectionListByActiveStatus(Boolean isActive) {
        return null;
    }

    public DetailedElectionDto findElectionById(Integer id) {
        return null;
    }

    public DetailedElectionDto createElection(DetailedElectionDto electionDto) {
        return null;
    }

    public DetailedElectionDto updateElection(DetailedElectionDto electionDto) {
        return null;
    }

    public Boolean deleteElection(Integer id) {
        return null;
    }
  }
