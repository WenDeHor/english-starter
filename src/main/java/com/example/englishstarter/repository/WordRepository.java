package com.example.englishstarter.repository;

import com.example.englishstarter.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
}
