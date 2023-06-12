package ua.lviv.iot.model.election.candidate;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CandidateDto {
    private Integer id;
    @NonNull
    private String name;
    private String description;
}
