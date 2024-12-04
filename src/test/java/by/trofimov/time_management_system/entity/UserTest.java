package by.trofimov.time_management_system.entity;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

    private static final double EPS = 1e-9;
    private User user;

    @Before
    public void createNewUser() {
        user = new User();
    }

    @Test
    public void newUserShouldHaveZeroId() {
        assertEquals(0, user.getId(), EPS);
    }

    @Test
    public void newUserShouldHaveZeroUsername() {
        assertNull(user.getUsername());
    }

    @Test
    public void newUserShouldHaveZeroEmail() {
        assertNull(user.getEmail());
    }

    @Test
    public void newUserShouldHaveZeroPassword() {
        assertNull(user.getPassword());
    }

    @Test
    public void newUserShouldHaveZeroRole() {
        assertNull(user.getRole());
    }

}
