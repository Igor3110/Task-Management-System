package by.trofimov.time_management_system.service.impl;

import java.util.List;
import by.trofimov.time_management_system.dto.RoleInfoDto;
import by.trofimov.time_management_system.mapper.RoleInfoMapper;
import jakarta.validation.Valid;
import by.trofimov.time_management_system.repository.RoleRepository;
import by.trofimov.time_management_system.entity.Role;
import by.trofimov.time_management_system.service.RoleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import static by.trofimov.time_management_system.util.Constant.*;

/**
 * Implementation of the service layer for the Role class.
 */
@Service
@Validated
public class RoleServiceImpl implements RoleService {

    /**
     * Inject a variable of type RoleRepository.
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * @return Returns all roles from the database.
     */
    @Override
    @Transactional
    public List<RoleInfoDto> findAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleInfoMapper::toRoleInfoDto)
                .toList();
    }

    /**
     * @return Returns roles from the database using Limit and Offset.
     */
    @Override
    @Transactional
    public Page<RoleInfoDto> findAllRolesUsingLimitAndOffset(int offset, int limit) {
        return roleRepository.findAll(PageRequest.of(offset, limit))
                .map(RoleInfoMapper::toRoleInfoDto);
    }

    /**
     * @param id ID of the role you are looking for.
     * @return The method returns the object of the searched role by its ID.
     */
    @Override
    @Transactional
    public RoleInfoDto findRoleById(int id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ROLE_NOT_FOUND_WITH_ID.formatted(id))
        );
        return RoleInfoMapper.toRoleInfoDto(role);
    }

    /**
     * @param name Name of the role you are looking for.
     * @return The method returns the object of the searched role by its Name.
     */
    @Override
    @Transactional
    public RoleInfoDto findRoleByName(String name) {
        Role role =  roleRepository.findRoleByName(name).orElseThrow(
                () -> new EntityNotFoundException(ROLE_NOT_FOUND_WITH_NAME.formatted(name))
        );
        return RoleInfoMapper.toRoleInfoDto(role);
    }

    /**
     * @param roleInfoDto An object that will be saved in the database.
     */
    @Override
    @Transactional
    public RoleInfoDto saveOrUpdateRole(@Valid RoleInfoDto roleInfoDto) {
        if (existsRoleName(roleInfoDto)) {
            throw new EntityExistsException(ROLE_WITH_SAME_NAME_EXISTS);
        }
        Role role = roleRepository.save(RoleInfoMapper.toRole(roleInfoDto));
        return RoleInfoMapper.toRoleInfoDto(role);
    }

    /**
     * @param id ID of the role who will be deleted from the database.
     */
    @Override
    @Transactional
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }

    /**
     * @param roleInfoDto An object that will be saved in the database.
     * @return We check if there is a role with the same name in the database.
     */
    private boolean existsRoleName(RoleInfoDto roleInfoDto) {
        return roleRepository.existsByName(roleInfoDto.getName());
    }

}
