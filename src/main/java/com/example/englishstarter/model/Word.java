package com.example.englishstarter.model;

import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

//@EqualsAndHashCode
//@ToString
//@AllArgsConstructor
//@RequiredArgsConstructor
//@EnableMBeanExport
@Entity
@Table(name = "word")
public class Word {
    public Word() {
    }

    public Word(String english, String translate, String transcription) {
        this.english = english;
        this.translate = translate;
        this.transcription = transcription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String english;
    private String translate;
    private String transcription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

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

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", english='" + english + '\'' +
                ", translate='" + translate + '\'' +
                ", transcription='" + transcription + '\'' +
                '}';
    }

    //    @ManyToOne
//    @JoinColumn(name = "users_id", updatable = false)
//    private User user;

}
