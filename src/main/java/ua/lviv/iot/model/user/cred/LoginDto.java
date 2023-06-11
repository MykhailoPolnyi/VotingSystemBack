package ua.lviv.iot.model.user.cred;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class LoginDto {
    @NonNull
    private String identityCode;
    @NonNull
    private String password;
}