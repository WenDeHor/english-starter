package com.example.englishstarter.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "study_table")
public class StudyTable {
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
}
