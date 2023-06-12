package ua.lviv.iot.model.election.vote;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;


@Data
@Builder
public class VoteDto {
    private Integer electionId;
    private Integer userId;
    private HashMap<Integer, Integer> votingMap;
}
