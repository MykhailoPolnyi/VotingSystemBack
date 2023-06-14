package ua.lviv.iot.service;

import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.ElectionAnalysisDto;
import ua.lviv.iot.model.election.LocalityType;
import ua.lviv.iot.service.LocationStrategy.*;

public class ElectionAnalyzer {
    private LocalityTypeStrategy strategy;

    public ElectionAnalysisDto analyze(Election election) {
        switch (election.getLocalityType()) {
            case DISTRICT:
                strategy = new DistrictLocation();
                break;
            case CITY:
                strategy = new CityLocation();
                break;
            case STATE:
                strategy = new StateLocation();
                break;
            case NATIONAL:
                strategy = new NationalLocation();
                break;
            default:
                throw new IllegalArgumentException("Unsupported locality type: " + election.getLocalityType());

        }

        return strategy.analyzeElection(election);
    }
}
