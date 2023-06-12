package ua.lviv.iot.model.address;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AddressDto {
    private Integer id;
    private String homeAddress;
    private String city;
    private String district;
    private String state;
    private String postalCode;
}