package ua.lviv.iot.model.election;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.election.candidate.CandidateDto;
import ua.lviv.iot.model.election.result.ElectionResultDto;

import java.util.List;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DetailedElectionDto extends ElectionDto {
    private Integer adminId;
    private List<CandidateDto> candidateList;
    private ElectionResultDto electionResultDto;
}
