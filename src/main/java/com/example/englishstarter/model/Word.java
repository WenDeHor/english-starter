package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(id, word.id) &&
                Objects.equals(english, word.english) &&
                Objects.equals(translate, word.translate) &&
                Objects.equals(transcription, word.transcription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, english, translate, transcription);
    }
}
