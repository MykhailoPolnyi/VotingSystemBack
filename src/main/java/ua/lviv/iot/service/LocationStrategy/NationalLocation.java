package ua.lviv.iot.service.LocationStrategy;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;

public class NationalLocation implements LocalityTypeStrategy {
    @Override
    public String analyzeElection(Election election) {
        return election.getLocalityAddress().getState();
    }
}
