package com.soulsarch.PasswordManager.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class URLInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String URL;
    private String email;
    @Column(name="nickname")
    private String nickname;
    private String password;

    public URLInformation(String URL, String email, String nickName, String password) {
        this.URL = URL;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public URLInformation() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLInformation that = (URLInformation) o;
        return id == that.id && URL.equals(that.URL) && email.equals(that.email) && Objects.equals(nickname, that.nickname) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, URL, email, nickname, password);
    }

    @Override
    public String toString() {
        return "URLInformation{" +
                "URL='" + URL + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
