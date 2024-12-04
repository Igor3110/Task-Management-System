package by.trofimov.time_management_system.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.dto.UserDto;
import by.trofimov.time_management_system.entity.User;

/**
 * Mapper for the User class.
 */
@UtilityClass
public class UserMapper {

    /**
     * The method converts an object of the User class into an object of the UserDto class.
     * @param user An object of the User class.
     * @return Class UserDto.
     */
    public static UserDto toUserDto(User user) {
        if (user.getTasks() == null) {
            user.setTasks(new HashSet<>());
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(RoleInfoMapper.toRoleInfoDto(user.getRole()));
        userDto.setTasks(
                user.getTasks().stream()
                        .map(TaskInfoMapper::toTaskInfoDto)
                        .collect(Collectors.toSet())
        );
        return userDto;
    }

    /**
     * The method converts an object of the UserDto class into an object of the User class.
     * @param userDto An object of the UserDto class.
     * @return Class User.
     */
    public static User toUser(UserDto userDto) {
        if (userDto.getTasks() == null) {
            userDto.setTasks(new HashSet<>());
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(RoleInfoMapper.toRole(userDto.getRole()));
        user.setTasks(
                userDto.getTasks().stream()
                        .map(TaskInfoMapper::toTask)
                        .collect(Collectors.toSet())
        );
        return user;
    }

}
