package ua.lviv.iot.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;


@Data
@SuperBuilder
@NoArgsConstructor
public class UserDto {
    @JsonProperty("id")
    protected Integer id;
    @JsonProperty(value = "firstName", required = true)
    protected String firstName;
    @JsonProperty(value = "secondName", required = true)
    protected String secondName;
    @JsonProperty(value = "identityCode", required = true)
    protected String identityCode;
    @JsonProperty(value = "email", required = true)
    protected String email;
    @JsonProperty(value = "phoneNumber", required = true)
    protected String phoneNumber;
    @JsonProperty(value = "birthDate", required = true)
    protected LocalDate birthDate;
    @JsonProperty(value = "address", required = true)
    protected AddressDto address;
    @JsonProperty("sex")
    protected Integer sex;
}