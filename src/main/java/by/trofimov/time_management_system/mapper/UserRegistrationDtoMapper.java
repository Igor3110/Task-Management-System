package by.trofimov.time_management_system.mapper;

import by.trofimov.time_management_system.entity.User;
import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.dto.UserRegistrationDto;

/**
 * Mapper for the user registration class.
 */
@UtilityClass
public class UserRegistrationDtoMapper {

    public static User toUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        return user;
    }

}
