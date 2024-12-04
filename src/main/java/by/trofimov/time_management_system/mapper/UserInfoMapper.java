package by.trofimov.time_management_system.mapper;

import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.entity.User;
import by.trofimov.time_management_system.dto.UserInfoDto;

/**
 * Mapper for the User class.
 */
@UtilityClass
public class UserInfoMapper {

    /**
     * The method converts an object of the User class into an object of the UserInfoDto class.
     * @param user An object of the User class.
     * @return Class UserInfoDto.
     */
    public static UserInfoDto toUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setPassword(user.getPassword());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setRole(RoleInfoMapper.toRoleInfoDto(user.getRole()));
        return userInfoDto;
    }

    /**
     * The method converts an object of the UserInfoDto class into an object of the User class.
     * @param userInfoDto An object of the UserInfoDto class.
     * @return Class User.
     */
    public static User toUser(UserInfoDto userInfoDto) {
        User user = new User();
        user.setId(userInfoDto.getId());
        user.setUsername(userInfoDto.getUsername());
        user.setPassword(userInfoDto.getPassword());
        user.setEmail(userInfoDto.getEmail());
        user.setRole(RoleInfoMapper.toRole(userInfoDto.getRole()));
        return user;
    }

}
