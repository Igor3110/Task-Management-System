package by.trofimov.time_management_system.mapper;

import lombok.experimental.UtilityClass;
import by.trofimov.time_management_system.dto.RoleInfoDto;
import by.trofimov.time_management_system.entity.Role;

/**
 * Mapper for the Role class.
 */
@UtilityClass
public class RoleInfoMapper {

    /**
     * The method converts an object of the Role class into an object of the RoleInfoDto class.
     * @param role An object of the Role class.
     * @return Class RoleInfoDto.
     */
    public static RoleInfoDto toRoleInfoDto(Role role) {
        RoleInfoDto roleInfoDto = new RoleInfoDto();
        roleInfoDto.setId(role.getId());
        roleInfoDto.setName(role.getName());
        return roleInfoDto;
    }

    /**
     * The method converts an object of the RoleInfoDto class into an object of the Role class.
     * @param roleInfoDto An object of the RoleInfoDto class.
     * @return Class Role.
     */
    public static Role toRole(RoleInfoDto roleInfoDto) {
        Role role = new Role();
        role.setId(roleInfoDto.getId());
        role.setName(roleInfoDto.getName());
        return role;
    }

}
