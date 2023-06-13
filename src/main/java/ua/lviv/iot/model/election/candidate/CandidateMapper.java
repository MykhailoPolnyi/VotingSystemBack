package ua.lviv.iot.model.election.candidate;

import ua.lviv.iot.model.election.DetailedElectionDto;
import ua.lviv.iot.model.election.Election;

import java.util.ArrayList;
import java.util.List;

public class CandidateMapper {
    public static CandidateDto toDto(Candidate candidate) {
        return CandidateDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .build();
    }

    public static Candidate toEntity(CandidateDto candidateDto, Integer electionId){
        var election = Election.builder()
                .id(electionId)
                .build();
        return Candidate.builder()
                .id(candidateDto.getId())
                .name(candidateDto.getName())
                .description(candidateDto.getDescription())
                .election(election)
                .build();
    }

    public static List<CandidateDto> toDtoList(List<Candidate> candidateList) {
        var candidateDtoList = new ArrayList<CandidateDto>();
        for (var candidate : candidateList) {
            var candidateDto = toDto(candidate);
            candidateDtoList.add(candidateDto);
        }
        return candidateDtoList;
    }

    public static List<Candidate> toEntityList(DetailedElectionDto electionDto) {
        var candidateList = new ArrayList<Candidate>();
        for (var candidateDto : electionDto.getCandidateList()) {
            var candidate = toEntity(candidateDto, electionDto.getId());
            candidateList.add(candidate);
        }
        return candidateList;
    }
}
