package com.example.englishstarter.controller;

import com.example.englishstarter.model.*;
import com.example.englishstarter.repository.StudyTableVerbsRepository;
import com.example.englishstarter.repository.UserRepository;
import com.example.englishstarter.repository.VerbsRepository;
import com.example.englishstarter.security.UserDetailsImpl;
import com.example.englishstarter.service.DownloadVerbs;
import com.example.englishstarter.service.DownloadVocabulary;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class _1_StudyVerbsController {

    private final DownloadVocabulary downloadVocabulary;
    private final UserRepository userRepository;
    private final VerbsRepository verbsRepository;
    private final StudyTableVerbsRepository studyTableVerbsRepository;
    private final DownloadVerbs downloadVerbs;
    private final  LocalDate DATE_NOW = LocalDate.now();
    private final int SIZE_LIST_WORDS = 5;
    private final int DATE_ONE = 3;
    private final int DATE_TWO = 7;
    private final int DATE_THREE = 12;
    private final int DATE_LOST = 13;

    @GetMapping("/downloadVerbs")
    public String downloadVerbs(Model model) {
        model.addAttribute("title", "IVAN and FATHER");
        downloadVerbs.saveVerbsToData();
        return "mine-page";
    }

    @Transactional
    @GetMapping("/user-study-verbs")
    public String userStudyVerbs(Authentication authentication, Model model) {
        List<Verbs> verbList = getStudyVerbs(authentication);
        Long countLearnedVerbs = countLearnedVerbs(authentication);
        model.addAttribute("verbList", verbList);
        model.addAttribute("countLearnedVerbs", countLearnedVerbs);
        return "user-study-verbs";
    }

    private List<Verbs> getStudyVerbs(Authentication authentication) {
        List<Verbs> wordRepositoryAll = verbsRepository.findAll();
        List<Integer> idListVerbs = getIdVerbsForToday(authentication);
        return wordRepositoryAll.stream()
                .filter(all -> !idListVerbs.contains(all.getId()))
                .limit(SIZE_LIST_WORDS)
                .collect(Collectors.toList());
    }

    private List<Integer> getIdVerbsForToday(Authentication authentication) {
        return studyTableVerbsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .map(StudyTableVerbs::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }

    private Long countLearnedVerbs(Authentication authentication) {
        return studyTableVerbsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(el -> el.getLearned().equals(true))
                .map(StudyTableVerbs::getLearned)
                .count();
    }

    @GetMapping("/user-study-verb/{id}/learned")
    public String userSaveWordToStudyTable(@PathVariable(value = "id") Integer id, Authentication authentication) {
        StudyTableVerbs studyTableVerbs = new StudyTableVerbs();
        studyTableVerbs.setIdWord(Math.toIntExact(id));
        studyTableVerbs.setLoginUser(getLoginUser(authentication));
        studyTableVerbs.setDateOne(DATE_NOW.plusDays(DATE_ONE));
        studyTableVerbs.setDateTwo(DATE_NOW.plusDays(DATE_TWO));
        studyTableVerbs.setDateThree(DATE_NOW.plusDays(DATE_THREE));
        studyTableVerbs.setLearned(false);
        studyTableVerbs.setCount(0);
        studyTableVerbs.setDateCount(DATE_NOW);
        System.out.println(studyTableVerbs);
        studyTableVerbsRepository.save(studyTableVerbs);
        return "redirect:/user-study-verbs";
    }

    @Transactional
    @GetMapping("/user-study-verbs/repeat")
    public String userRepeatVerbs(Authentication authentication, Model model) {
        List<Verbs> repeatVerbs = getRepeatVerbs(authentication);
        Long countLearnedVerbs = countLearnedVerbs(authentication);
        model.addAttribute("repeatVerbs", repeatVerbs);
        model.addAttribute("countLearnedVerbs", countLearnedVerbs);
        return "user-repeat-verbs";
    }

    private List<Verbs> getRepeatVerbs(Authentication authentication) {
        List<Integer> filteredListIdOfWords = getFilteredListIdOfVerbs(authentication);
        if (filteredListIdOfWords.size() == 0) {
            List<Integer> filteredListIdOfWordsIfLostTime = getFilteredListIdOfVerbsIfLostTime(authentication);
            return findWord(filteredListIdOfWordsIfLostTime);
        } else {
            return findWord(filteredListIdOfWords);
        }
    }

    private List<Verbs> findWord(List<Integer> filteredListIdOfWords) {
        List<Verbs> convertToWord = new ArrayList<>();
        for (Integer filteredListIdOfWord : filteredListIdOfWords) {
            Optional<Verbs> byId = verbsRepository.findById(filteredListIdOfWord);
            byId.ifPresent(convertToWord::add);
        }
        return convertToWord;
    }

    @GetMapping("/user-study-verb/{id}/repeat")
    public String userSaveRepeat(@PathVariable(value = "id") Integer id) {
        Optional<StudyTableVerbs> byId = studyTableVerbsRepository.findByIdWord(id);
        if (byId.isPresent()) {
            StudyTableVerbs studyTableVerbs = byId.get();
            if (studyTableVerbs.getCount() == 3) {
                studyTableVerbs.setCount(studyTableVerbs.getCount() + 1);
                studyTableVerbs.setLearned(true);
                studyTableVerbsRepository.save(studyTableVerbs);
                System.out.println(studyTableVerbs);
            }
            studyTableVerbs.setDateCount(DATE_NOW);
            studyTableVerbs.setCount(studyTableVerbs.getCount() + 1);
            System.out.println(studyTableVerbs);
            studyTableVerbsRepository.save(studyTableVerbs);
        }
        return "redirect:/user-study-verbs/repeat";
    }

    private List<Integer> getFilteredListIdOfVerbs(Authentication authentication) {
        return studyTableVerbsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(d1 -> d1.getDateOne().equals(DATE_NOW)
                        || d1.getDateTwo().equals(DATE_NOW)
                        || d1.getDateThree().equals(DATE_NOW))
                .filter(learned -> learned.getLearned().equals(false))
                .filter(countDate -> !countDate.getDateCount().equals(DATE_NOW))
                .filter(count -> count.getCount() <= 3)
                .map(StudyTableVerbs::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }


    private String getLoginUser(Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> oneByLogin = userRepository.findOneByLogin(details.getUsername());
        return oneByLogin.map(User::getLogin).orElse(null);
    }

    private List<Integer>  getFilteredListIdOfVerbsIfLostTime(Authentication authentication) {
        return studyTableVerbsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(learned -> learned.getLearned().equals(false))
                .filter(count -> count.getCount() <= 3)
                .filter(day -> DATE_NOW.isAfter(day.getDateCount().plusDays(DATE_LOST)))
                .map(StudyTableVerbs::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }


}
