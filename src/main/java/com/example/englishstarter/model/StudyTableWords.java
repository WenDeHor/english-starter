package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

//@EqualsAndHashCode
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@Table(name = "study_table_words")
public class StudyTableWords {
    public StudyTableWords() {
    }

    public StudyTableWords(Integer idWord, String loginUser, LocalDate dateOne, LocalDate dateTwo, LocalDate dateThree, Boolean learned, Integer count, LocalDate dateCount) {
        this.idWord = idWord;
        this.loginUser = loginUser;
        this.dateOne = dateOne;
        this.dateTwo = dateTwo;
        this.dateThree = dateThree;
        this.learned = learned;
        this.count = count;
        this.dateCount = dateCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_word")
    private Integer idWord;

    @Column(name = "login_user")
    private String loginUser;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_one")
    private LocalDate dateOne;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_two")
    private LocalDate dateTwo;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_three")
    private LocalDate dateThree;

    @Column(name = "learned")
    private Boolean learned;

    @Column(name = "count")
    private Integer count;

    @Column(name = "date_count")
    private LocalDate dateCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdWord() {
        return idWord;
    }

    public void setIdWord(Integer idWord) {
        this.idWord = idWord;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public LocalDate getDateOne() {
        return dateOne;
    }

    public void setDateOne(LocalDate dateOne) {
        this.dateOne = dateOne;
    }

    public LocalDate getDateTwo() {
        return dateTwo;
    }

    public void setDateTwo(LocalDate dateTwo) {
        this.dateTwo = dateTwo;
    }

    public LocalDate getDateThree() {
        return dateThree;
    }

    public void setDateThree(LocalDate dateThree) {
        this.dateThree = dateThree;
    }

    public Boolean getLearned() {
        return learned;
    }

    public void setLearned(Boolean learned) {
        this.learned = learned;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDate getDateCount() {
        return dateCount;
    }

    public void setDateCount(LocalDate dateCount) {
        this.dateCount = dateCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyTableWords that = (StudyTableWords) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idWord, that.idWord) &&
                Objects.equals(loginUser, that.loginUser) &&
                Objects.equals(dateOne, that.dateOne) &&
                Objects.equals(dateTwo, that.dateTwo) &&
                Objects.equals(dateThree, that.dateThree) &&
                Objects.equals(learned, that.learned) &&
                Objects.equals(count, that.count) &&
                Objects.equals(dateCount, that.dateCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idWord, loginUser, dateOne, dateTwo, dateThree, learned, count, dateCount);
    }

    @Override
    public String toString() {
        return "StudyTableWords{" +
                "id=" + id +
                ", idWord=" + idWord +
                ", loginUser='" + loginUser + '\'' +
                ", dateOne=" + dateOne +
                ", dateTwo=" + dateTwo +
                ", dateThree=" + dateThree +
                ", learned=" + learned +
                ", count=" + count +
                ", dateCount=" + dateCount +
                '}';
    }
}
