package ua.lviv.iot.service.LocationStrategy;

import ua.lviv.iot.model.election.Election;

public interface LocalityTypeStrategy {
    String analyzeElection(Election election);
}
