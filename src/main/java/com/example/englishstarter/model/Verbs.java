package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "verbs")
public class Verbs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String infinitive;
    private String infinitiveTranscription;
    private String pastSimple;
    private String pastSimpleTranscription;
    private String pastParticiple;
    private String pastParticipleTranscription;
    private String translate;
}
