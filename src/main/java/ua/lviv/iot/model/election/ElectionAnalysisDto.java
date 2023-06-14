package ua.lviv.iot.model.election;

import lombok.NonNull;

import java.util.HashMap;

public class ElectionAnalysisDto {
    private Integer id;
    @NonNull
    private String location;
    private String candidate;
    private Integer voteCount;

    private HashMap<Integer, Integer> candidateVoteNumberMap;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
