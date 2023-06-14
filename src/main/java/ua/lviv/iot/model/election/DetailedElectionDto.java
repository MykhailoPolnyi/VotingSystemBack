package ua.lviv.iot.model.election;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ua.lviv.iot.model.address.AddressDto;
import ua.lviv.iot.model.election.candidate.CandidateDto;
import ua.lviv.iot.model.election.result.ElectionResultDto;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class DetailedElectionDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("voteCount")
    private Integer voteCount;
    @JsonProperty(value = "availableVotes", required = true)
    private Integer availableVotes;
    @JsonProperty(value = "localityType", required = true)
    private Integer localityType;
    @JsonProperty("localityAddress")
    private AddressDto localityAddress;
    @JsonProperty(value = "hasRetract", required = true)
    private Boolean hasRetract;
    @JsonProperty(value = "minAge", required = true)
    private Integer minAge;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonProperty(value = "startDate", required = true)
    private LocalDate startDate;
    @JsonProperty(value = "endDate", required = true)
    private LocalDate endDate;
    @JsonProperty("adminId")
    private Integer adminId;
    @JsonProperty("candidateList")
    private List<CandidateDto> candidateList;
    @JsonProperty("electionResultDto")
    private ElectionResultDto electionResultDto;
}
