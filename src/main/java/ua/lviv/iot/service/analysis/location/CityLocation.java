package ua.lviv.iot.service.analysis.location;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;

import java.util.List;

public class CityLocation implements LocalityAnalysisStrategy {
    @Override
    public List<ElectionAnalysisDto> analyzeElection(Election election) {
        return election.getLocalityAddress().getDistrict();
    }
}
