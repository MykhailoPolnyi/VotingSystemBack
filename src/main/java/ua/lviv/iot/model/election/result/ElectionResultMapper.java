package ua.lviv.iot.model.election.result;

import java.util.HashMap;
import java.util.List;

public class ElectionResultMapper {
    public static ElectionResultDto toDto(List<ElectionResult> electionResultList, Integer userId) {
        var candidateVoteNumberMap = toCandidateVoteNumberMap(electionResultList);
        var userChoiceMap = toUserChoiceMap(electionResultList, userId);
        return ElectionResultDto.builder()
                .candidateVoteNumberMap(candidateVoteNumberMap)
                .userChoiceMap(userChoiceMap)
                .build();
    }

    private static HashMap<Integer, Integer> toUserChoiceMap(List<ElectionResult> electionResultList, Integer userId) {
        var userChoiceMap = new HashMap<Integer, Integer>();
        for (var electionResult : electionResultList) {
            if (userId != null && !electionResult.getElector().getId().equals(userId)) {
                continue;
            }
            var candidateId = electionResult.getCandidate().getId();
            var votesNumber = electionResult.getVoteNumber();

            if (userChoiceMap.containsKey(candidateId)) {
                userChoiceMap.put(candidateId, userChoiceMap.get(candidateId) + votesNumber);
            } else {
                userChoiceMap.put(candidateId, votesNumber);
            }
        }
        return userChoiceMap;
    }

    private static HashMap<Integer, Integer> toCandidateVoteNumberMap(List<ElectionResult> electionResultList) {
        return toUserChoiceMap(electionResultList, null);
    }


}
