package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String english;
    private String translate;
    private String transcription;

    @ManyToOne
    @JoinColumn(name = "users_id", updatable = false)
    private User user;

}
