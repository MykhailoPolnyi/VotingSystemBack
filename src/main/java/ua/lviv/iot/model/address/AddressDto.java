package ua.lviv.iot.model.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty("homeAddress")
    private String homeAddress;
    @JsonProperty("city")
    private String city;
    @JsonProperty("district")
    private String district;
    @JsonProperty("state")
    private String state;
    @JsonProperty("postalCode")
    private String postalCode;
}