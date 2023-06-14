package ua.lviv.iot.model.election.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonProperty("description")
    private String description;
}
