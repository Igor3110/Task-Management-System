package by.trofimov.time_management_system.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import by.trofimov.time_management_system.dto.JwtRequest;
import by.trofimov.time_management_system.dto.UserRegistrationDto;
import by.trofimov.time_management_system.service.AuthService;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private JwtRequest request;

    @Mock
    private UserRegistrationDto userRegistrationDto;

    @InjectMocks
    private AuthController authController;

    @Test
    void testCreateAuthToken() {
        authController.createAuthToken(request);
        verify(authService).getToken(request);
    }

    @Test
    void testCreateNewUser() {
        authController.createNewUser(userRegistrationDto);
        verify(authService).createNewUser(userRegistrationDto);
    }

}
