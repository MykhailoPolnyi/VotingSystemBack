package ua.lviv.iot.service.analysis;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.candidate.Candidate;
import ua.lviv.iot.model.election.result.ElectionResult;
import ua.lviv.iot.service.analysis.location.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionAnalyzer {

    public Map<Candidate, Integer> getCandidateVotes(Election election) {
        List<ElectionResult> electionResults = electionResultRepository.findAllByElectionId(election.getId());
        Map<Candidate, Integer> candidateVotes = new HashMap<>();

        //get all candidate
        for (ElectionResult result : electionResults) {
            Candidate candidate = result.getCandidate();

            candidateVotes.put(candidate, 0);
        }

        // get vote to all candidate
        for (Map.Entry<Candidate, Integer> entry : candidateVotes.entrySet()) {
            Candidate candidate = entry.getKey();
            int voteCount = entry.getValue();

            for (ElectionResult result : electionResults) {
                if (result.getCandidate() == candidate){
                    voteCount = voteCount + result.getVoteNumber();
                }
            }

            entry.setValue(voteCount);
        }
            return candidateVotes;
    }

    public List<ElectionAnalysisDto> analyzeLocation(Election election, List<ElectionResult> results) {
        switch (election.getLocalityType()) {
            case CITY:
                LocalityAnalysisStrategy cityStrategy = new CityLocation();
                return cityStrategy.analyzeElection(election, results);
            case STATE:
                LocalityAnalysisStrategy stateStrategy = new StateLocation();
                return stateStrategy.analyzeElection(election, results);
            case NATIONAL:
                LocalityAnalysisStrategy nationalStrategy = new NationalLocation();
                return nationalStrategy.analyzeElection(election, results);
            default:
                return null;
        }



        Map<Candidate, Integer> electionResults = getCandidateVotes(election);
        List<ElectionAnalysisDto> analysisDtoList = new ArrayList<>();

        for (Map.Entry<Candidate, Integer> entry : electionResults.entrySet()) {
            Candidate candidate = entry.getKey();
            int voteCount = entry.getValue();

            ElectionAnalysisDto analysisDto = new ElectionAnalysisDto();
            analysisDto.setCandidate(candidate);
            analysisDto.setVoteCount(voteCount);
            analysisDto.setLocation(getLocationByElection(election));

            analysisDtoList.add(analysisDto);
        }

        return analysisDtoList;
    }
}
