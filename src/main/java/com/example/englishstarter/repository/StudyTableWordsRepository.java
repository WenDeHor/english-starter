package com.example.englishstarter.repository;

import com.example.englishstarter.model.StudyTableWords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTableWordsRepository extends JpaRepository<StudyTableWords, Long> {

   Optional<StudyTableWords> findByIdWord(Integer id);
}
