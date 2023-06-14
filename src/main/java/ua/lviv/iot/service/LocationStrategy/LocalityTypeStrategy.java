package ua.lviv.iot.service.LocationStrategy;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;

public interface LocalityTypeStrategy {
    ElectionAnalysisDto analyzeElection(Election election);
}
