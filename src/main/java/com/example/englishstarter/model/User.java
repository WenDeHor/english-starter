package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long idUser;

    @Column(name = "login")
    private String login;//REGISTRATION PAGE

    @Column(name = "password")
    private String password;//REGISTRATION PAGE
    @Enumerated(value = EnumType.STRING)

    @Column(name = "role")
    private Role role;

    @Column(name = "studied")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Word> studied;

    @Column(name = "studying")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Word> studying;
}
