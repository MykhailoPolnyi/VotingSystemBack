package ua.lviv.iot.service.LocationStrategy;

import ua.lviv.iot.model.election.Election;

public class CityLocation implements LocalityTypeStrategy {
    @Override
    public String analyzeElection(Election election) {
        return election.getLocalityAddress().getDistrict();
    }
}
