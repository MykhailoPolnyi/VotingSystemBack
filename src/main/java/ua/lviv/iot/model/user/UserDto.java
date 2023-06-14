package ua.lviv.iot.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;


@Data
@SuperBuilder
public class UserDto {
    @JsonProperty("id")
    protected Integer id;
    @NonNull
    @JsonProperty("firstName")
    protected String firstName;
    @NonNull
    @JsonProperty("secondName")
    protected String secondName;
    @NonNull
    @JsonProperty("identityCode")
    protected String identityCode;
    @NonNull
    @JsonProperty("email")
    protected String email;
    @NonNull
    @JsonProperty("phoneNumber")
    protected String phoneNumber;
    @NonNull
    @JsonProperty("birthDate")
    protected LocalDate birthDate;
    @NonNull
    @JsonProperty("address")
    protected AddressDto address;
    @JsonProperty("sex")
    protected Integer sex;
}