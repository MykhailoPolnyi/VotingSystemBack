package ua.lviv.iot.service.analysis.location;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.result.ElectionResult;

import java.util.ArrayList;
import java.util.List;

public class NationalLocation implements LocalityAnalysisStrategy {
    @Override
    public List<ElectionAnalysisDto> analyzeElection(Election election, List<ElectionResult> results) {
        List<ElectionAnalysisDto> analysisDtoList = new ArrayList<>();
        for (ElectionResult result: results) {

        }
    }
}
