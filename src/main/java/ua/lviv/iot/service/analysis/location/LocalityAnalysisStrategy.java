package ua.lviv.iot.service.analysis.location;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.result.ElectionResult;

import java.util.List;

public interface LocalityAnalysisStrategy {
    List<ElectionAnalysisDto> analyzeElection(Election election, List<ElectionResult> results);
}
