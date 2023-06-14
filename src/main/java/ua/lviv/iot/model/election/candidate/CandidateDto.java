package ua.lviv.iot.model.election.candidate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateDto {
    private Integer id;
    private String name;
    private String description;
}
