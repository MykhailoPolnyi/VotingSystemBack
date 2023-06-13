package ua.lviv.iot.model.election;

import ua.lviv.iot.model.address.AddressMapper;
import ua.lviv.iot.model.election.candidate.CandidateMapper;
import ua.lviv.iot.model.election.result.ElectionResultDto;
import ua.lviv.iot.model.user.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElectionMapper {
    public static DetailedElectionDto toDetailedDto(Election election,
                                                    ElectionResultDto electionResultDto,
                                                    Integer voteCount) {
        var localityAddressDto = AddressMapper
                .toDto(election.getLocalityAddress());
        var candidateDto = CandidateMapper
                .toDtoList(election.getCandidateAssociationList());
        return DetailedElectionDto.builder()
                .id(election.getId())
                .name(election.getName())
                .description(election.getDescription())
                .voteCount(voteCount)
                .availableVotes(election.getAvailableVotes())
                .localityType(election.getLocalityType().ordinal())
                .localityAddress(localityAddressDto)
                .hasRetract(election.getHasRetract())
                .minAge(election.getMinAge())
                .maxAge(election.getMaxAge())
                .startDate(election.getStartDate())
                .endDate(election.getEndDate())
                .adminId(election.getAdmin().getId())
                .candidateList(candidateDto)
                .electionResultDto(electionResultDto)
                .build();
    }

    public static Election toEntity(DetailedElectionDto electionDto) {
        var admin = Admin.builder()
                .id(electionDto.getAdminId())
                .build();
        var localityAddress = AddressMapper
                .toEntity(electionDto.getLocalityAddress());
        var localityType = LocalityType
                .valueOf(electionDto.getLocalityType());
        var candidate = CandidateMapper
                .toEntityList(electionDto);

        return Election.builder()
                .id(electionDto.getId())
                .name(electionDto.getName())
                .description(electionDto.getDescription())
                .availableVotes(electionDto.getAvailableVotes())
                .localityType(localityType)
                .localityAddress(localityAddress)
                .hasRetract(electionDto.getHasRetract())
                .minAge(electionDto.getMinAge())
                .maxAge(electionDto.getMaxAge())
                .startDate(electionDto.getStartDate())
                .endDate(electionDto.getEndDate())
                .admin(admin)
                .candidateAssociationList(candidate)
                .build();
    }

    public static List<ElectionDto> toDtoList(List<Election> electionList,
                                              Map<Integer, Integer> electionVoteCountMap) {
        var electionDtoList = new ArrayList<ElectionDto>();
        for (var election : electionList) {
            var electionDto = toDto(election, electionVoteCountMap.get(election.getId()));
            electionDtoList.add(electionDto);
        }
        return electionDtoList;
    }

    private static ElectionDto toDto(Election election, Integer votesCount) {
        var localityAddress = AddressMapper
                .toDto(election.getLocalityAddress());
        return ElectionDto.builder()
                .id(election.getId())
                .name(election.getName())
                .description(election.getDescription())
                .voteCount(votesCount)
                .availableVotes(election.getAvailableVotes())
                .localityType(election.getLocalityType().ordinal())
                .localityAddress(localityAddress)
                .hasRetract(election.getHasRetract())
                .minAge(election.getMinAge())
                .maxAge(election.getMaxAge())
                .startDate(election.getStartDate())
                .endDate(election.getEndDate())
                .build();
    }
}
