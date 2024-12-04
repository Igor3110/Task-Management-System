package by.trofimov.time_management_system.controller;

import lombok.SneakyThrows;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import by.trofimov.time_management_system.dto.JwtRequest;
import by.trofimov.time_management_system.service.AuthService;
import by.trofimov.time_management_system.TaskManagementSystemApplication;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TaskManagementSystemApplication.class)
@AutoConfigureMockMvc
public class TokenAuthenticationControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthService authService;

    private final String HOST_AND_PORT = "http://localhost:%d".formatted(randomServerPort);

    private final JwtRequest admin = new JwtRequest("rapira_by@mail.ru", "Admin");

    private final JwtRequest user = new JwtRequest("test@mail.ru", "User");

    @Test
    public void shouldAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get( HOST_AND_PORT + "/swagger-ui/index.html#/")).andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.post(HOST_AND_PORT + "/api/auth")
                        .content(asJsonString(admin))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/taskmanager/getAll"))
                .andExpect(status().is(401));
    }

    @Test
    public void shouldNotAllowAccessIfUserNotAdmin() throws Exception {
        String token = authService.getToken(user);
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/taskmanager/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/roles/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(403));
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/tasks/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(403));
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/users/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(403));
    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = authService.getToken(admin);
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/taskmanager/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/roles/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/tasks/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get(HOST_AND_PORT + "/api/users/getAll")
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().is(200));
    }

    @SneakyThrows
    private String asJsonString(final Object obj) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
