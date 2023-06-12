package ua.lviv.iot.model.user.cred;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Authorities {
    public static GrantedAuthority getAdminAuthority() {
        return new SimpleGrantedAuthority(ADMIN_AUTHORITY);
    }

    public static GrantedAuthority getUserAuthority() {
        return new SimpleGrantedAuthority(USER_AUTHORITY);
    }

    private static String ADMIN_AUTHORITY = "ADMIN_AUTHORITY";
    private static String USER_AUTHORITY = "USER_AUTHORITY";
}
