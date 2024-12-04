package by.trofimov.time_management_system.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import by.trofimov.time_management_system.service.RoleService;
import by.trofimov.time_management_system.dto.RoleInfoDto;
import org.springframework.data.domain.Page;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private Page<RoleInfoDto> page;

    @Mock
    private RoleInfoDto roleInfoDto;

    @InjectMocks
    private RoleController roleController;

    private final int roleId = 1;
    private final String roleName = "USER_ADMIN";

    @Test
    void testFindAllRoles() {
        when(roleService.findAllRoles()).thenReturn(Collections.emptyList());
        roleController.findAllRoles().getBody();
        verify(roleService).findAllRoles();
    }

    @Test
    void testFindAllRolesUsingLimitAndOffset() {
        when(roleService.findAllRolesUsingLimitAndOffset(0, 1)).thenReturn(page);
        roleController.findAllRolesUsingLimitAndOffset(0, 1);
        verify(roleService).findAllRolesUsingLimitAndOffset(0, 1);
    }

    @Test
    void testFindRoleById() {
        when(roleService.findRoleById(roleId)).thenReturn(roleInfoDto);
        roleController.findRoleById(roleId).getBody();
        verify(roleService).findRoleById(roleId);
    }

    @Test
    void testFindRoleByName() {
        when(roleService.findRoleByName(roleName)).thenReturn(roleInfoDto);
        roleController.findRoleByName(roleName).getBody();
        verify(roleService).findRoleByName(roleName);
    }

    @Test
    void testSaveRole() {
        when(roleService.saveOrUpdateRole(roleInfoDto)).thenReturn(roleInfoDto);
        roleController.saveRole(roleInfoDto);
        verify(roleService).saveOrUpdateRole(roleInfoDto);
    }

    @Test
    void testUpdateRole() {
        when(roleService.saveOrUpdateRole(roleInfoDto)).thenReturn(roleInfoDto);
        roleController.updateRole(roleInfoDto);
        verify(roleService).saveOrUpdateRole(roleInfoDto);
    }

    @Test
    void testDeleteRole() {
        roleController.deleteRole(roleId);
        verify(roleService).deleteRoleById(roleId);
    }

}
