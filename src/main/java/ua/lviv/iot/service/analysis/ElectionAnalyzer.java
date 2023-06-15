package ua.lviv.iot.service.analysis;

import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.LocalityType;
import ua.lviv.iot.model.election.candidate.ElectionAnalysis;
import ua.lviv.iot.model.election.result.ElectionResult;

import java.util.HashMap;
import java.util.List;

public class ElectionAnalyzer {
    public ElectionAnalysis analyzeElection(Election election, List<ElectionResult> results) {
        var analysis = new ElectionAnalysis();
        for (ElectionResult vote : results) {
            var candidateName = vote.getCandidate().getName();
            var location = receiveLocationNameForLocality(
                    vote.getElector().getAddress(),
                    election.getLocalityType()
            );
            var votes = vote.getVoteNumber();

            var resultsMap = analysis.get(location);
            if (resultsMap == null) {
                var newResultsMap = new HashMap<String, Integer>();
                newResultsMap.put(candidateName, votes);
                analysis.put(location, newResultsMap);
            } else if (resultsMap.containsKey(candidateName)) {
                var oldVotes = resultsMap.get(candidateName);
                resultsMap.put(candidateName, oldVotes + votes);
            } else {
                resultsMap.put(candidateName, votes);
            }
        }

        return analysis;
    }

    private String receiveLocationNameForLocality(Address address, LocalityType locality) {
        //get LocalityAddress
        switch (locality) {
            case CITY:
                LocalityAddress cityLocalityAddress = new CityLocalityAddress();
                return cityLocalityAddress.getLocationName(address);
            case STATE:
                LocalityAddress stateLocalityAddress = new StateLocalityAddress();
                return stateLocalityAddress.getLocationName(address);
            case NATIONAL:
                LocalityAddress nationalLocalityAddress = new NationalLocalityAddress();
                return nationalLocalityAddress.getLocationName(address);
            default:
                return null;
        }
    }
}
