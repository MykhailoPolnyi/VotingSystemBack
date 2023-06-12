package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.user.UserDto;
import ua.lviv.iot.model.user.UserMapper;
import ua.lviv.iot.model.user.cred.Authorities;
import ua.lviv.iot.model.user.cred.UserCred;
import ua.lviv.iot.model.user.cred.UserCredDto;
import ua.lviv.iot.repository.AdminRepository;
import ua.lviv.iot.repository.UserCredRepository;
import ua.lviv.iot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final UserCredRepository userCredRepository;

    @Transactional
    public UserDto createUser(UserCredDto userDto) {
        final var user = UserMapper.toEntity(userDto);
        final var createdUser = userRepository.save(user);
        final var userCred = UserMapper.toCred(createdUser, userDto.getPassword());
        userCredRepository.save(userCred);
        return UserMapper.toDto(createdUser);
    }

    public UserDto findUser(Integer id) {
        final var user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return UserMapper.toDto(user.get());
    }

    public UserDto updateUser(UserDto userDto) {
        final var updatedUser = userRepository.save(UserMapper.toEntity(userDto));
        return UserMapper.toDto(updatedUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByIdentityCode(username).orElseThrow(
                () -> new UsernameNotFoundException("Cannot find user with identity code: " + username)
        );

        var userPassword = userCredRepository.findById(user.getId()).orElseThrow(
                () -> new UsernameNotFoundException("User info saved, but no credentials found for name: " + username)
        ).getPassword();

        List<GrantedAuthority> userAuthorities = new ArrayList<>(List.of(Authorities.getUserAuthority()));
        var isUserAdmin = adminRepository.existsById(user.getId());
        if (isUserAdmin) {
            userAuthorities.add(Authorities.getAdminAuthority());
        }

        return UserCred.builder()
                .user(user)
                .password(userPassword)
                .authorities(userAuthorities)
                .build();
    }
}
