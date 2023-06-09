package ua.lviv.iot.model.user;

import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.address.AddressMapper;
import ua.lviv.iot.model.user.cred.UserCred;

public class UserMapper {
    private UserMapper() {}
    public static UserDto toDto(User user) {
        var addressDto = AddressMapper.toDto(user.getAddress());
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .identityCode(user.getIdentityCode())
                .email(user.getEmailAddress())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .address(addressDto)
                .sex(user.getSex().ordinal())
                .build();
    }

    public static User toEntity(UserDto dto) {
        Address address = AddressMapper.toEntity(dto.getAddress());
        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName().strip())
                .secondName(dto.getSecondName().strip())
                .identityCode(dto.getIdentityCode().strip())
                .emailAddress(dto.getEmail().strip())
                .phoneNumber(dto.getPhoneNumber().strip())
                .birthDate(dto.getBirthDate())
                .address(address)
                .sex(Sex.valueOf(dto.getSex()))
                .build();
    }

    public static UserCred toCred(User user, String password) {
        return UserCred.builder()
                .id(user.getId())
                .user(user)
                .password(password)
                .build();
    }
}