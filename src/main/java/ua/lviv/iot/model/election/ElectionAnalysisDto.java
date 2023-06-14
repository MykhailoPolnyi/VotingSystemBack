package ua.lviv.iot.model.election;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ElectionAnalysisDto {
    private Integer id;
    private String location;
    private String candidate;
    private Integer voteCount;
}
