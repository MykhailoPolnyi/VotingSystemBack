package ua.lviv.iot.service.analysis;

import ua.lviv.iot.model.address.Address;

public interface LocalityAddress {
    String getLocationName(Address address);
}
