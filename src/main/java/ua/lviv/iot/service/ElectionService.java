package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.model.election.ElectionMapper;
import ua.lviv.iot.model.election.result.ElectionResultMapper;
import ua.lviv.iot.repository.CandidateRepository;
import ua.lviv.iot.repository.ElectionRepository;
import ua.lviv.iot.repository.ElectionResultRepository;

import java.util.HashMap;
import java.util.List;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class ElectionService {
    private final ElectionRepository electionRepository;
    private final ElectionResultRepository electionResultRepository;
    private final CandidateRepository candidateRepository;

    public List<ElectionDto> getAllElectionList() {
        var electionList = electionRepository.findAll();
        var electionVoteNumberMap = getElectionVoteCountMap(electionList);

        return ElectionMapper.toDtoList(electionList, electionVoteNumberMap);
    }

    public List<ElectionDto> getElectionListByActiveStatus(Boolean isActive) {
        List<Election> electionList;
        if (isActive) {
            electionList = electionRepository.findActiveElectionList();
        } else {
            electionList = electionRepository.findClosedElectionList();
        }
        var electionVoteCountMap = getElectionVoteCountMap(electionList);

        return ElectionMapper.toDtoList(electionList, electionVoteCountMap);
    }

    public List<ElectionDto> getEditableElectionList(Integer adminId) {

        var electionList = electionRepository.findEditableElectionList(adminId);
        var electionVoteNumberMap = getElectionVoteCountMap(electionList);

        return ElectionMapper.toDtoList(electionList, electionVoteNumberMap);
    }

    public DetailedElectionDto findElectionById(Integer id, Integer userId) {
        var election = electionRepository.findById(id).orElse(null);
        if (election == null) {
            return null;
        }

        var electionResultList = electionResultRepository.findAllByElectionId(id);
        var electionResultDto = ElectionResultMapper.toDto(electionResultList, userId);

        var voteCount = electionResultRepository.countVoteByElectionId(id);

        return ElectionMapper.toDetailedDto(election, electionResultDto, voteCount);
    }

    public DetailedElectionDto createElection(DetailedElectionDto electionDto) {
        var election = ElectionMapper.toEntity(electionDto);
        var candidateList = election.getCandidateAssociationList();
        election = electionRepository.save(election);
        for (var candidate : candidateList) {
            candidate.setElection(election);
        }
        candidateRepository.saveAll(candidateList);
        election.setCandidateAssociationList(candidateList);
        return ElectionMapper.toDetailedDto(election, null, 0);
    }

    public DetailedElectionDto updateElection(DetailedElectionDto electionDto) {
        if (!electionRepository.existsById(electionDto.getId())) {
            return null;
        }

        var election = ElectionMapper.toEntity(electionDto);
        var candidateList = election.getCandidateAssociationList();
        candidateRepository.saveAll(candidateList);
        election = electionRepository.save(election);
        var voteCount = electionResultRepository.countVoteByElectionId(election.getId());
        return ElectionMapper.toDetailedDto(election, null, voteCount);
    }

    public Boolean deleteElection(Integer id) {
        if (!electionRepository.existsById(id)) {
            return null;
        }
        electionRepository.deleteById(id);
        return true;
    }

    private HashMap<Integer, Integer> getElectionVoteCountMap(List<Election> electionList) {
        var electionVoteNumberMap = new HashMap<Integer, Integer>();
        for (var election : electionList) {
            var voteCount = electionResultRepository.countVoteByElectionId(election.getId());
            electionVoteNumberMap.put(election.getId(), voteCount);
        }
        return electionVoteNumberMap;
    }
}
