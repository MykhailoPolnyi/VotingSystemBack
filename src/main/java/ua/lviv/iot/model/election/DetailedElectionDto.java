package ua.lviv.iot.model.election;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
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
    @NonNull
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("voteCount")
    private Integer voteCount;
    @NonNull
    @JsonProperty("availableVotes")
    private Integer availableVotes;
    @NonNull
    @JsonProperty("localityType")
    private Integer localityType;
    @JsonProperty("localityAddress")
    private AddressDto localityAddress;
    @NonNull
    @JsonProperty("hasRetract")
    private Boolean hasRetract;
    @NonNull
    @JsonProperty("minAge")
    private Integer minAge;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @NonNull
    @JsonProperty("startDate")
    private LocalDate startDate;
    @NonNull
    @JsonProperty("endDate")
    private LocalDate endDate;
    @JsonProperty("adminId")
    private Integer adminId;
    @JsonProperty("candidateList")
    private List<CandidateDto> candidateList;
    @JsonProperty("electionResultDto")
    private ElectionResultDto electionResultDto;
}
