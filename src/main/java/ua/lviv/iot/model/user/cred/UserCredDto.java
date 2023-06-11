package ua.lviv.iot.model.user.cred;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.user.UserDto;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserCredDto extends UserDto {
    @NonNull
    private String password;
}