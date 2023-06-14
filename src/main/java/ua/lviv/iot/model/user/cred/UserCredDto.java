package ua.lviv.iot.model.user.cred;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.user.UserDto;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class UserCredDto extends UserDto {
    private String password;
}