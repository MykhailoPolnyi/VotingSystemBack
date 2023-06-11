package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.model.election.vote.VoteDto;
import ua.lviv.iot.repository.ElectionRepository;
import ua.lviv.iot.repository.UserRepository;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class ElectionResultService {
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final ElectionResultService electionResultService;
    private final ElectionAnalyzer electionAnalyzer;

    public ElectionResultDto getElectionResult(Integer electionId, Integer userId) {
        // TODO Implement method
        return null;
    }

    public ElectionAnalysisDto getElectionAnalysis(Integer electionId) {
        // TODO Implement method
        return null;
    }

    public VoteDto addVote(VoteDto vote) {
        // TODO Implement method
        return null;
    }

    public Boolean removeVote(Integer electionId, Integer userId) {
        // TODO Implement method
        return null;
    }

    private Boolean validateVoting(VoteDto voteDto) {
        // TODO Implement method
        return null;
    }
}
