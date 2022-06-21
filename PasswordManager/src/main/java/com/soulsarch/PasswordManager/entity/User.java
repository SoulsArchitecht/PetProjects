package com.soulsarch.PasswordManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soulsarch.PasswordManager.model.Role;
import com.soulsarch.PasswordManager.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

/*    @Column(name = "moderatorTrue", columnDefinition = "TINYINT", nullable = false)
    private int moderatorTrue;*/

    @Column(name = "reg_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDate regTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
/*    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<URLInformation> urlInformationList;*/
    /*    public Role getRole() {
        return moderatorTrue == 1 ? Role.MODERATOR : Role.USER;
    }*/
}
