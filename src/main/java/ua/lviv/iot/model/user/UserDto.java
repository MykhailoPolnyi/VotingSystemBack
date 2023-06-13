package ua.lviv.iot.model.user;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;
import java.util.Date;


@Data
@SuperBuilder
@NoArgsConstructor
public class UserDto {
    protected Integer id;
    protected String firstName;
    protected String secondName;
    protected String identityCode;
    protected String email;
    protected String phoneNumber;
    protected LocalDate birthDate;
    protected AddressDto address;
    protected Integer sex;
}