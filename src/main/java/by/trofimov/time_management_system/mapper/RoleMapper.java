package by.trofimov.time_management_system.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.dto.RoleDto;
import by.trofimov.time_management_system.entity.Role;

/**
 * Mapper for the Role class.
 */
@UtilityClass
public class RoleMapper {

    /**
     * The method converts an object of the Role class into an object of the RoleDto class.
     * @param role An object of the Role class.
     * @return Class RoleDto.
     */
    public static RoleDto toRoleDto(Role role) {
        if (role.getUsers() == null) {
            role.setUsers(new HashSet<>());
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setUsers(
                role.getUsers().stream()
                        .map(UserInfoMapper::toUserInfoDto)
                        .collect(Collectors.toSet())
        );
        return roleDto;
    }

    /**
     * The method converts an object of the RoleDto class into an object of the Role class.
     * @param roleDto An object of the RoleDto class.
     * @return Class Role.
     */
    public static Role toRole(RoleDto roleDto) {
        if (roleDto.getUsers() == null) {
            roleDto.setUsers(new HashSet<>());
        }
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setUsers(
                roleDto.getUsers().stream()
                        .map(UserInfoMapper::toUser)
                        .collect(Collectors.toSet())
        );
        return role;
    }

}
