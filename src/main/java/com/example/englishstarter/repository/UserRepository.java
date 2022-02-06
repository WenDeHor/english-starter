package com.example.englishstarter.repository;

import com.example.englishstarter.model.Role;
import com.example.englishstarter.model.User;
import com.example.englishstarter.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByRole(Role role);
//    Optional<User> findAllByAddress(String addressUser);
    Optional<User> findOneByIdUser(Long email);
    Optional<User> findOneByLogin(String login);
    List<Word>findAllByIdUser(Long idUser);
}
