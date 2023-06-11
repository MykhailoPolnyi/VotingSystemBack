package ua.lviv.iot.model.user;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.Address;

import java.util.Date;

@Data
@SuperBuilder
public class User {
    protected Long id;
    @NonNull
    protected String firstName;
    @NonNull
    protected String secondName;
    @NonNull
    protected String identityCode;
    @NonNull
    protected String emailAddress;
    @NonNull
    protected String phoneNumber;
    @NonNull
    protected Date birthDate;
    @NonNull
    protected Address address;
    protected Sex sex;
}