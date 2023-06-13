package ua.lviv.iot.model.user;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;


@Data
@SuperBuilder
public class UserDto {
    protected Integer id;
    @NonNull
    protected String firstName;
    @NonNull
    protected String secondName;
    @NonNull
    protected String identityCode;
    @NonNull
    protected String email;
    @NonNull
    protected String phoneNumber;
    @NonNull
    protected LocalDate birthDate;
    @NonNull
    protected AddressDto address;
    protected Integer sex;
}