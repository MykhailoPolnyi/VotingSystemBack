package ua.lviv.iot.model.address;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Address {
    private Long id;
    private String homeAddress;
    private String city;
    private String district;
    private String state;
    private String postalCode;
}