package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

//@EqualsAndHashCode
//@ToString
//@Getter
//@Setter
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Builder
@Entity
@Table(name = "verbs")
public class Verbs {
    public Verbs() {
    }

    public Verbs(String infinitive, String infinitiveTranscription, String pastSimple, String pastSimpleTranscription, String pastParticiple, String pastParticipleTranscription, String translate) {
        this.infinitive = infinitive;
        this.infinitiveTranscription = infinitiveTranscription;
        this.pastSimple = pastSimple;
        this.pastSimpleTranscription = pastSimpleTranscription;
        this.pastParticiple = pastParticiple;
        this.pastParticipleTranscription = pastParticipleTranscription;
        this.translate = translate;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getInfinitiveTranscription() {
        return infinitiveTranscription;
    }

    public void setInfinitiveTranscription(String infinitiveTranscription) {
        this.infinitiveTranscription = infinitiveTranscription;
    }

    public String getPastSimple() {
        return pastSimple;
    }

    public void setPastSimple(String pastSimple) {
        this.pastSimple = pastSimple;
    }

    public String getPastSimpleTranscription() {
        return pastSimpleTranscription;
    }

    public void setPastSimpleTranscription(String pastSimpleTranscription) {
        this.pastSimpleTranscription = pastSimpleTranscription;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public String getPastParticipleTranscription() {
        return pastParticipleTranscription;
    }

    public void setPastParticipleTranscription(String pastParticipleTranscription) {
        this.pastParticipleTranscription = pastParticipleTranscription;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Verbs verbs = (Verbs) o;
        return Objects.equals(id, verbs.id) &&
                Objects.equals(infinitive, verbs.infinitive) &&
                Objects.equals(infinitiveTranscription, verbs.infinitiveTranscription) &&
                Objects.equals(pastSimple, verbs.pastSimple) &&
                Objects.equals(pastSimpleTranscription, verbs.pastSimpleTranscription) &&
                Objects.equals(pastParticiple, verbs.pastParticiple) &&
                Objects.equals(pastParticipleTranscription, verbs.pastParticipleTranscription) &&
                Objects.equals(translate, verbs.translate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infinitive, infinitiveTranscription, pastSimple, pastSimpleTranscription, pastParticiple, pastParticipleTranscription, translate);
    }

    @Override
    public String toString() {
        return "Verbs{" +
                "id=" + id +
                ", infinitive='" + infinitive + '\'' +
                ", infinitiveTranscription='" + infinitiveTranscription + '\'' +
                ", pastSimple='" + pastSimple + '\'' +
                ", pastSimpleTranscription='" + pastSimpleTranscription + '\'' +
                ", pastParticiple='" + pastParticiple + '\'' +
                ", pastParticipleTranscription='" + pastParticipleTranscription + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
