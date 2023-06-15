package ua.lviv.iot.service.analysis;

import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.LocalityType;

public interface LocalityAddress {
    String getLocationName(Address address, LocalityType locality);
}
