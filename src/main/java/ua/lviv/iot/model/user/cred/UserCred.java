package ua.lviv.iot.model.user.cred;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ua.lviv.iot.model.user.User;

@Data
@Builder
public class UserCred {
    @NonNull
    private User user;
    @NonNull
    private String password;
}