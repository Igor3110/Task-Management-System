package by.trofimov.time_management_system.service;

import by.trofimov.time_management_system.dto.*;
import by.trofimov.time_management_system.entity.User;
import by.trofimov.time_management_system.mapper.RoleInfoMapper;
import by.trofimov.time_management_system.mapper.UserMapper;
import by.trofimov.time_management_system.token.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import by.trofimov.time_management_system.mapper.UserRegistrationDtoMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static by.trofimov.time_management_system.util.Constant.ROLE_USER;

/**
 * The service class is used to obtain the token.
 */
@Service
public class AuthService {

    /**
     * Inject a variable of type AuthenticationManager.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Inject a variable of type UserDetailsService.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Inject a variable of type JwtTokenManager.
     */
    @Autowired
    private JwtTokenManager jwtTokenManager;

    /**
     * Inject a variable of type UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * Inject a variable of type RoleService.
     */
    @Autowired
    private RoleService roleService;

    /**
     * @return - returns the token for the user.
     */
    public String getToken(JwtRequest request) {
        UserDto userDto = userService.findUserByEmail(request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        return jwtTokenManager.generateJwtToken(userDetails);
    }

    /**
     * @param userRegistrationDto DTO for entity User
     * @return New User entity
     */
    public ResponseEntity<UserDto> createNewUser(UserRegistrationDto userRegistrationDto) {
        User user = UserRegistrationDtoMapper.toUser(userRegistrationDto);
        String bcryptPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(bcryptPassword);
        RoleInfoDto roleinfoDto = roleService.findRoleByName(ROLE_USER);
        user.setRole(RoleInfoMapper.toRole(roleinfoDto));
        UserDto userDto = userService.saveOrUpdateUser(UserMapper.toUserDto(user));
        return ResponseEntity.ok(userDto);
    }

}
