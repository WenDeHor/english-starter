package com.example.englishstarter.repository;

import com.example.englishstarter.model.StudyTableVerbs;
import com.example.englishstarter.model.StudyTableWords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTableVerbsRepository extends JpaRepository<StudyTableVerbs, Long> {

   Optional<StudyTableVerbs> findByIdWord(Integer id);
}
