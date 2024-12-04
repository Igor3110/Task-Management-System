package by.trofimov.time_management_system.controller;

import java.util.List;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import by.trofimov.time_management_system.dto.RoleInfoDto;
import by.trofimov.time_management_system.dto.BaseResponse;
import by.trofimov.time_management_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import static by.trofimov.time_management_system.util.Constant.ENTITY_WAS_SUCCESSFULLY_DELETED;

/**
 * Controller class for the Role entity.
 */
@Tag(name = "Controller for entity Role (Administrator only).")
@RestController
@RequestMapping("/api")
public class RoleController {

    /**
     * Inject a variable of type RoleService.
     */
    @Autowired
    private RoleService roleService;

    /**
     * @return Returns all roles.
     */
    @Operation(
            summary = "Get all roles from Database.",
            description = "The method returns all roles from Database."
    )
    @GetMapping("/roles/getAll")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<RoleInfoDto>> findAllRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    /**
     * @return Returns roles using Limit and Offset.
     */
    @Operation(
            summary = "Get all roles using limit and offset.",
            description = "The method returns all roles using limit and offset."
    )
    @GetMapping("/roles/getByLimit")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Page<RoleInfoDto>> findAllRolesUsingLimitAndOffset(
            @Valid @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @Valid @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit
    ) {
        return ResponseEntity.ok(roleService.findAllRolesUsingLimitAndOffset(offset, limit));
    }

    /**
     * @param id Role ID.
     * @return Returns role by ID.
     */
    @Operation(
            summary = "Get role by ID.",
            description = "The method returns role by ID."
    )
    @GetMapping("/roles/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<RoleInfoDto> findRoleById(@PathVariable int id) {
        return ResponseEntity.ok(roleService.findRoleById(id));
    }

    /**
     * @param name Role name.
     * @return Returns role by Name.
     */
    @Operation(
            summary = "Get role by name.",
            description = "The method returns role by name."
    )
    @GetMapping("/roles/getByName")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<RoleInfoDto> findRoleByName(@RequestParam String name) {
        return ResponseEntity.ok(roleService.findRoleByName(name));
    }

    /**
     * @param roleInfoDto - RoleInfoDto object.
     * @return - Saves role to the database.
     */
    @Operation(
            summary = "Add role to Database.",
            description = "The method saves role to Database and returns RoleInfoDto."
    )
    @PostMapping("/roles")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<RoleInfoDto> saveRole(@RequestBody RoleInfoDto roleInfoDto) {
        return ResponseEntity.ok(roleService.saveOrUpdateRole(roleInfoDto));
    }

    /**
     * @param roleInfoDto - RoleInfoDto object.
     * @return - Updates role to the database.
     */
    @Operation(
            summary = "Change role.",
            description = "The method changes role and returns RoleInfoDto."
    )
    @PutMapping("/roles")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<RoleInfoDto> updateRole(@RequestBody RoleInfoDto roleInfoDto) {
        return ResponseEntity.ok(roleService.saveOrUpdateRole(roleInfoDto));
    }

    /**
     * @param id - role ID.
     * @return - Removes role by ID from the database.
     */
    @Operation(
            summary = "Delete role from Database.",
            description = "The method delete role by ID and returns BaseResponse."
    )
    @DeleteMapping("/roles/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BaseResponse> deleteRole(@PathVariable int id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse(ENTITY_WAS_SUCCESSFULLY_DELETED.formatted(id), LocalDateTime.now()));
    }

}
