package ua.lviv.iot.model.user.cred;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private String identityCode;
    private String password;
}