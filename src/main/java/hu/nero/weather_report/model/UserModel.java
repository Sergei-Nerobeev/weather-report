package hu.nero.weather_report.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name="username")
    @Size(min = 3,max = 6, message = "Username should be from 3 to 6 chars")
    private String username;

    @Column(name="password")
    @Size(min = 3,max = 6, message = "Username should be from 3 to 6 chars")
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<RoleModel> roles;
}
