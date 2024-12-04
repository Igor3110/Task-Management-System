package by.trofimov.time_management_system.entity;

import org.junit.*;
import static org.junit.Assert.*;

public class RoleTest {

    private static final double EPS = 1e-9;
    private Role role;

    @Before
    public void createNewRole() {
        role = new Role();
    }

    @Test
    public void newRoleShouldHaveZeroId() {
        assertEquals(0, role.getId(), EPS);
    }

    @Test
    public void newRoleShouldHaveZeroName() {
        assertNull(role.getName());
    }

    @Test
    public void afterExecutionMethodAddUserShouldHaveOneElement() {
        role.addUser(new User());
        assertEquals(1, role.getUsers().size());
    }

}
