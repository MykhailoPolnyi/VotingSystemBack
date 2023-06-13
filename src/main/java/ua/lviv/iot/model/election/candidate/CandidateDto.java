package ua.lviv.iot.model.election.candidate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
public class CandidateDto {
    private Integer id;
    private String name;
    private String description;
}
