package com.example.englishstarter.repository;

import com.example.englishstarter.model.StudyTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTableRepository extends JpaRepository<StudyTable, Long> {

   Optional<StudyTable> findByIdWord(Integer id);
}
