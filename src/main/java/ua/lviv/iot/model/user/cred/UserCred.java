package ua.lviv.iot.model.user.cred;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.lviv.iot.model.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Builder
public class UserCred implements UserDetails {
    @NonNull
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @NonNull
    @Column(nullable = false)
    private String password;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return user.getIdentityCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}