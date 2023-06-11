package ua.lviv.iot.model.address;

import lombok.NonNull;

public class AddressMapper {
    @NonNull
    public static AddressDto toDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .homeAddress(address.getHomeAddress())
                .city(address.getCity())
                .district(address.getDistrict())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static Address toEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .district(addressDto.getDistrict())
                .state(addressDto.getState())
                .postalCode(addressDto.getPostalCode())
                .build();
    }
}