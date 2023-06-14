package ua.lviv.iot.model.election;

import lombok.NonNull;
import ua.lviv.iot.model.election.candidate.Candidate;

public class ElectionAnalysisDto {
    private Integer id;
    @NonNull
    private String location;
    private String candidate;
    private Integer voteCount;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate.getName();
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
