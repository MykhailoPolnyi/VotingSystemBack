package ua.lviv.iot.model.election.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CandidateDto {
    @JsonProperty("id")
    private Integer id;
    @NonNull
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
}
