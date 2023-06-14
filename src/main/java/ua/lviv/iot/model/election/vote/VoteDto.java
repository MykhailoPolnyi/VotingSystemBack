package ua.lviv.iot.model.election.vote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;


@Data
@Builder
public class VoteDto {
    @JsonProperty("electionId")
    private Integer electionId;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("votingMap")
    private HashMap<Integer, Integer> votingMap;
}
