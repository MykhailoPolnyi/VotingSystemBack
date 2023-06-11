package ua.lviv.iot.model.user;

import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.address.AddressDto;
import ua.lviv.iot.model.address.AddressMapper;
import ua.lviv.iot.model.user.cred.UserCred;
import ua.lviv.iot.model.user.cred.UserCredDto;

public class UserMapper {
    private UserMapper() {}
    public static UserDto toDto(User user) {
        AddressDto addressDto = AddressMapper.toDto(user.getAddress());
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
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .identityCode(dto.getIdentityCode())
                .emailAddress(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .birthDate(dto.getBirthDate())
                .address(address)
                .sex(sexFromInt(dto.getSex()))
                .build();
    }

    public static UserCred toCred(UserCredDto dto) {
        Address address = AddressMapper.toEntity(dto.getAddress());
        return UserCred.builder()
                .user(toEntity(dto))
                .password(dto.getPassword())
                .build();
    }

    private static Sex sexFromInt(Integer value) {
        if (value > Sex.values().length) {
            return null;
        } else {
            return Sex.values()[value];
        }
    }
}