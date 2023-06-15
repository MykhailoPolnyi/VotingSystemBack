package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.address.AddressMatcher;
import ua.lviv.iot.model.election.candidate.ElectionAnalysis;
import ua.lviv.iot.model.election.result.ElectionResult;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.model.election.result.ElectionResultMapper;
import ua.lviv.iot.model.election.vote.VoteDto;
import ua.lviv.iot.model.election.vote.VoteMapper;
import ua.lviv.iot.repository.CandidateRepository;
import ua.lviv.iot.repository.ElectionRepository;
import ua.lviv.iot.repository.ElectionResultRepository;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.service.analysis.CityLocalityAddress;
import ua.lviv.iot.service.analysis.ElectionAnalyzer;
import ua.lviv.iot.service.analysis.NationalLocalityAddress;
import ua.lviv.iot.service.analysis.StateLocalityAddress;

import java.time.LocalDate;
import java.util.List;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class ElectionResultService {
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final ElectionResultRepository electionResultRepository;
    private final CandidateRepository candidateRepository;

    public ElectionResultDto getElectionResult(Integer electionId, Integer userId) {
        var electionResultList = electionResultRepository.findAllByElectionId(electionId);

        return ElectionResultMapper.toDto(electionResultList, userId);
    }

    public ElectionAnalysis getElectionAnalysis(Integer electionId) {
        var election = electionRepository.findById(electionId);
        if (election.isEmpty()) {
            return null;
        }
        var electionResultList = electionResultRepository.findAllByElectionId(electionId);
        if (electionResultList == null || electionResultList.isEmpty()) {
            return null;
        }

        ElectionAnalyzer analyzer;

        switch (election.get().getLocalityType()) {
            case CITY:
                analyzer = new ElectionAnalyzer(new CityLocalityAddress());
                break;
            case STATE:
                analyzer = new ElectionAnalyzer(new StateLocalityAddress());
                break;
            case NATIONAL:
                analyzer = new ElectionAnalyzer(new NationalLocalityAddress());
                break;
            default:
                return null;
        }

        return analyzer.analyzeElection(election.get(), electionResultList);
    }

    public VoteDto addVote(VoteDto voteDto) {
        if (!validateVoting(voteDto)) {
            return null;
        }

        List<ElectionResult> vote = VoteMapper.toElectionResultList(voteDto);
        electionResultRepository.saveAll(vote);

        return voteDto;
    }

    public Boolean removeVote(Integer electionId, Integer userId) {
        Boolean hasVoted = electionResultRepository
                .existsByUserIdAndElectionId(userId, electionId);
        if (!hasVoted) {
            return false;
        }

        var deleteList = electionResultRepository.findByUserIdAndElectionId(userId, electionId);
        electionResultRepository.deleteAll(deleteList);

        return true;
    }

    private Boolean validateVoting(VoteDto voteDto) {
        var election = electionRepository
                .findById(voteDto.getElectionId())
                .orElse(null);
        if (election == null) return false;

        var isElectionActive = LocalDate.now()
                .isBefore(election.getEndDate())
                && LocalDate.now()
                .isAfter(election.getStartDate());
        if (!isElectionActive) return false;

        var user = userRepository
                .findById(voteDto.getUserId())
                .orElse(null);
        if (user == null) return false;

        var isLocationMatch = AddressMatcher
                .compare(election.getLocalityType(),
                        user.getAddress(),
                        election.getLocalityAddress());
        if (!isLocationMatch) return false;

        var isAgeMatch = (user.getAge() >= election.getMinAge())
                || (election.getMaxAge() != null
                && user.getAge() <= election.getMaxAge());
        if (!isAgeMatch) return false;

        Boolean hasVoted = electionResultRepository
                .existsByUserIdAndElectionId(
                        voteDto.getUserId(),
                        voteDto.getElectionId());
        if (hasVoted) return false;

        Integer userVoteCount = 0;
        for (var candidate : voteDto.getVotingMap().entrySet()) {
            if (!candidateRepository.existsById(candidate.getKey())) {
                return false;
            }
            userVoteCount += candidate.getValue();
        }

        return userVoteCount.equals(election.getAvailableVotes());
    }
}
