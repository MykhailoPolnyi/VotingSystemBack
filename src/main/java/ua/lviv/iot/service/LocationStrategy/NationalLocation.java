package ua.lviv.iot.service.LocationStrategy;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;

public class NationalLocation implements LocalityTypeStrategy {
    @Override
    public ElectionAnalysisDto analyzeElection(Election election) {
        ElectionAnalysisDto analysisDto = new ElectionAnalysisDto();
        //set candidate from election
        //analysisDto.setCandidate(election.getC);
        //set vote from election to candidate
        //analysisDto.setVoteCount(election.);
        analysisDto.setLocation(election.getLocalityAddress().getCity());
        return analysisDto;
    }
}
