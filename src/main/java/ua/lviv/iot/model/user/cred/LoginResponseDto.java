package ua.lviv.iot.model.user.cred;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String jwt;
    private Integer id;
    private Boolean isAdmin;
    private String type;
}
