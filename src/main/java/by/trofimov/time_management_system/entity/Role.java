package by.trofimov.time_management_system.entity;

import lombok.*;
import java.util.*;
import jakarta.persistence.*;

/**
 * Role entity class.
 */
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<User> users;

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
        user.setRole(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
