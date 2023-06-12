package ua.lviv.iot.model.election.vote;

import ua.lviv.iot.model.election.candidate.Candidate;
import ua.lviv.iot.model.election.result.ElectionResult;
import ua.lviv.iot.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class VoteMapper {
    public static List<ElectionResult> toElectionResultList(VoteDto voteDto) {
        var electionResultList = new ArrayList<ElectionResult>();
        for (var entry : voteDto.getVotingMap().entrySet()) {
            var elector = User.builder()
                    .id(voteDto.getUserId())
                    .build();
            var candidate = Candidate.builder()
                    .id(entry.getKey())
                    .build();

            var electionResult = ElectionResult.builder()
                    .elector(elector)
                    .candidate(candidate)
                    .voteNumber(entry.getValue())
                    .build();
            electionResultList.add(electionResult);
        }
        return electionResultList;
    }
}
