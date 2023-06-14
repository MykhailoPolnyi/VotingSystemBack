package ua.lviv.iot.service;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.LocalityType;
import ua.lviv.iot.model.election.result.ElectionResult;
import ua.lviv.iot.repository.ElectionResultRepository;
import ua.lviv.iot.service.LocationStrategy.*;

import java.util.ArrayList;
import java.util.List;

public class ElectionAnalyzer {
    private final ElectionResultRepository electionResultRepository;

    public String getLocationByElection(Election election) {
        switch (election.getLocalityType()) {
            case DISTRICT:
                LocalityTypeStrategy districtStrategy = new DistrictLocation();
                return districtStrategy.analyzeElection(election);
            case CITY:
                LocalityTypeStrategy cityStrategy = new CityLocation();
                return cityStrategy.analyzeElection(election);
            case STATE:
                LocalityTypeStrategy stateStrategy = new StateLocation();
                return stateStrategy.analyzeElection(election);
            case NATIONAL:
                LocalityTypeStrategy nationalStrategy = new NationalLocation();
                return nationalStrategy.analyzeElection(election);
            default:
                throw new IllegalArgumentException("Unsupported locality type: " + election.getLocalityType());

        }
    }

    public ElectionAnalyzer(ElectionResultRepository electionResultRepository) {
        this.electionResultRepository = electionResultRepository;
    }

    public List<ElectionAnalysisDto> analyze(Election election) {
        List<ElectionResult> electionResults = electionResultRepository.findAllByElectionId(election.getId());
        List<ElectionAnalysisDto> analysisDtoList = new ArrayList<>();

        for (ElectionResult result : electionResults) {
            ElectionAnalysisDto analysisDto = new ElectionAnalysisDto();
             analysisDto.setCandidate(result.getCandidate());
             analysisDto.setVoteCount(result.getVoteNumber());
             analysisDto.setLocation(getLocationByElection(election));

            analysisDtoList.add(analysisDto);
        }

        return analysisDtoList;
    }
}
