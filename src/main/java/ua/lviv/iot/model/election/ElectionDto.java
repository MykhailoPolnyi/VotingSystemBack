package ua.lviv.iot.model.election;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;

@Data
@SuperBuilder
public class ElectionDto {
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
}
