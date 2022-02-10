package com.soulsarch.PasswordManager.entity;

import com.soulsarch.PasswordManager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(name = "moderatorTrue", columnDefinition = "TINYINT", nullable = false)
    private int moderatorTrue;

    @Column(name = "reg_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDate regTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Role getRole() {
        return moderatorTrue == 1 ? Role.MODERATOR : Role.USER;
    }
}
