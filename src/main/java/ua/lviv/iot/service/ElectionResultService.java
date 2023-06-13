package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.result.ElectionResult;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.model.election.result.ElectionResultMapper;
import ua.lviv.iot.model.election.vote.VoteDto;
import ua.lviv.iot.model.election.vote.VoteMapper;
import ua.lviv.iot.repository.CandidateRepository;
import ua.lviv.iot.repository.ElectionRepository;
import ua.lviv.iot.repository.ElectionResultRepository;
import ua.lviv.iot.repository.UserRepository;

import java.util.List;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class ElectionResultService {
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final ElectionResultRepository electionResultRepository;
    private final CandidateRepository candidateRepository;
    private final ElectionAnalyzer electionAnalyzer;

    public ElectionResultDto getElectionResult(Integer electionId, Integer userId) {
        var electionResultList = electionResultRepository.findAllByElectionId(electionId);

        return ElectionResultMapper.toDto(electionResultList, userId);
    }

    public ElectionAnalysisDto getElectionAnalysis(Integer electionId) {
        // TODO Implement method
        return null;
    }

    public VoteDto addVote(VoteDto voteDto) {
        if(!validateVoting(voteDto)){
            return null;
        }

        List<ElectionResult> vote = VoteMapper.toElectionResultList(voteDto);
        electionResultRepository.saveAll(vote);

        return voteDto;
    }

    public Boolean removeVote(Integer electionId, Integer userId) {
        if(!electionRepository.existsById(electionId)){
            return null;
        }

        electionResultRepository.deleteVoteByElectionIdAndUserId(electionId, userId);
        return true;
    }

    private Boolean validateVoting(VoteDto voteDto) {
        var election = electionRepository.findById(voteDto.getElectionId()).orElse(null);
        if(election == null){
            return false;
        }

        if(!userRepository.existsById(voteDto.getUserId())){
            return false;
        }
        Integer userVoteCount = 0;
        for (var candidate : voteDto.getVotingMap().entrySet()) {
            if(!candidateRepository.existsById(candidate.getKey())){
                return false;
            }
            userVoteCount += candidate.getValue();
        }

        return userVoteCount.equals(election.getAvailableVotes());
    }
}
