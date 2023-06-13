package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.user.UserDto;
import ua.lviv.iot.model.user.UserMapper;
import ua.lviv.iot.model.user.cred.Authorities;
import ua.lviv.iot.model.user.cred.UserCred;
import ua.lviv.iot.model.user.cred.UserCredDto;
import ua.lviv.iot.repository.AddressRepository;
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
    private final AddressRepository addressRepository;
    private final UserCredRepository userCredRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public void createUser(UserCredDto userDto) {
        final var user = UserMapper.toEntity(userDto);
        final var savedAddress = addressRepository.save(user.getAddress());
        user.setAddress(savedAddress);
        final var createdUser = userRepository.save(user);
        final var userCred = UserMapper.toCred(createdUser, encoder.encode(userDto.getPassword()));
        userCredRepository.save(userCred);
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
        userCredRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    public boolean userExists(String identityCode) {
        return userRepository.existsByIdentityCode(identityCode);
    }

    @Override
    public UserCred loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByIdentityCode(username).orElseThrow(
                () -> new UsernameNotFoundException("Cannot find user with identity code: " + username)
        );

        final var userPassword = userCredRepository.findById(user.getId()).orElseThrow(
                () -> new UsernameNotFoundException("User info saved, but no credentials found for name: " + username)
        ).getPassword();

        List<GrantedAuthority> userAuthorities = new ArrayList<>(List.of(Authorities.getUserAuthority()));
        final var isUserAdmin = adminRepository.existsById(user.getId());
        if (isUserAdmin) {
            userAuthorities.add(Authorities.getAdminAuthority());
        }

        return UserCred.builder()
                .id(user.getId())
                .user(user)
                .password(userPassword)
                .authorities(userAuthorities)
                .build();
    }
}
