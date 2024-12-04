package by.trofimov.time_management_system.service.impl;

import org.springframework.stereotype.Service;
import by.trofimov.time_management_system.dto.UserDto;
import by.trofimov.time_management_system.mapper.UserMapper;
import by.trofimov.time_management_system.security.CustomUserDetails;
import by.trofimov.time_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * A class that implements UserDetailsService.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Inject a variable of type UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * @param username Username
     * @return New object CustomUserDetails.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.findUserByUsername(username);
        return new CustomUserDetails(UserMapper.toUser(userDto));
    }

}
