package com.soulsarch.PasswordManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soulsarch.PasswordManager.model.Role;
import com.soulsarch.PasswordManager.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

/*    @Column(name = "moderatorTrue", columnDefinition = "TINYINT", nullable = false)
    private int moderatorTrue;*/

    @Column(name = "reg_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime regTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    public User(String username, String password, String fullname, String phone) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    private List<URLInformation> urlInformationList;
    /*    public Role getRole() {
        return moderatorTrue == 1 ? Role.MODERATOR : Role.USER;
    }*/
}
