package ua.lviv.iot.service.analysis;

import ua.lviv.iot.model.address.Address;

public class StateLocalityAddress implements LocalityAddress {
    @Override
    public String getLocationName(Address address) {
        return address.getCity();
    }
}