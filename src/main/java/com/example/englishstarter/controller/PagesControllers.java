package com.example.englishstarter.controller;

import com.example.englishstarter.model.User;
import com.example.englishstarter.model.Word;
import com.example.englishstarter.repository.UserRepository;
import com.example.englishstarter.repository.WordRepository;
import com.example.englishstarter.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PagesControllers {
    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    public PagesControllers(UserRepository userRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "IVAN and FATHER");
        return "mine-page";
    }

    @Transactional
    @GetMapping("/user-page")
    public String userSecondLoad(Authentication authentication, Model model, MultipartFile file) throws IOException {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        String login = details.getUsername();
        Optional<User> oneByLogin = userRepository.findOneByLogin(login);
        List<Word> allWord = wordRepository.findAll();
        //TODO++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(oneByLogin.isPresent()){
            Long idUser = oneByLogin.get().getIdUser();

            List<Word> listStudying = userRepository.findAllByIdUser(idUser);
            model.addAttribute("listStudying", listStudying);
        }
        model.addAttribute("title", "Mine Page");
        return "userPage";
    }


}
