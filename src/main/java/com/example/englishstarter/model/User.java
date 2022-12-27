package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@EqualsAndHashCode
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer idUser;

    @Column(name = "login")
    private String login;//REGISTRATION PAGE

    @Column(name = "password")
    private String password;//REGISTRATION PAGE

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(idUser, user.idUser) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    //    @Column(name = "studied")
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Word> studied;
//
//    @Column(name = "studying")
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Word> studying;
}
