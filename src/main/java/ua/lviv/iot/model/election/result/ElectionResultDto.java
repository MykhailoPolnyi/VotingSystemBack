package ua.lviv.iot.model.election.result;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class ElectionResultDto {
    private HashMap<Integer, Integer> candidateVoteNumberMap;
    private HashMap<Integer, Integer> userChoiceMap;
}
