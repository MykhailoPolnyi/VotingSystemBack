package ua.lviv.iot.model.election;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;

@Data
@SuperBuilder
public class ElectionDto {
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
}
