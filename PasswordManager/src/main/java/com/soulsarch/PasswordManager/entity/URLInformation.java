package com.soulsarch.PasswordManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class URLInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="url", nullable = false)
    private String url;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="nickname")
    private String nickname;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="description")
    private String description;

    public URLInformation(String url, String email, String nickName, String password, String description) {
        this.url = url;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.description = description;
    }


}
