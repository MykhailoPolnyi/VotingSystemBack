package ua.lviv.iot.service.analysis;

import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.LocalityType;

public class NationalLocalityAddress implements LocalityAddress {
    @Override
    public String getLocationName(Address address, LocalityType locality) {
        return address.getState();
    }
}
