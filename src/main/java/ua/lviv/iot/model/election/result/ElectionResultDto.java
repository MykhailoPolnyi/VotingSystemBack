package ua.lviv.iot.model.election.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class ElectionResultDto {
    @JsonProperty("candidateVoteNumberMap")
    private HashMap<Integer, Integer> candidateVoteNumberMap;
    @JsonProperty("userChoiceMap")
    private HashMap<Integer, Integer> userChoiceMap;
}
