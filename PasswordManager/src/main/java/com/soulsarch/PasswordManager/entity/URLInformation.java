package com.soulsarch.PasswordManager.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class URLInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="url", nullable = false)
    private String url;
    private String email;
    @Column(name="nickname")
    private String nickname;
    private String password;

    public URLInformation(String url, String email, String nickName, String password) {
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return id == that.id && url.equals(that.url) && email.equals(that.email) && Objects.equals(nickname, that.nickname) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, email, nickname, password);
    }

    @Override
    public String toString() {
        return "URLInformation{" +
                "URL='" + url + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
