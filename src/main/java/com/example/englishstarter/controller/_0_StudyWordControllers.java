package com.example.englishstarter.controller;

import com.example.englishstarter.model.StudyTableWords;
import com.example.englishstarter.model.User;
import com.example.englishstarter.model.Word;
import com.example.englishstarter.repository.StudyTableWordsRepository;
import com.example.englishstarter.repository.UserRepository;
import com.example.englishstarter.repository.WordRepository;
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
public class _0_StudyWordControllers {
    private final DownloadVocabulary downloadVocabulary;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final StudyTableWordsRepository studyTableWordsRepository;
    private final DownloadVerbs downloadVerbs;
    private final LoaderPhoto loaderPhoto;
    private final LocalDate DATE_NOW = LocalDate.now();
    private final int SIZE_LIST_WORDS = 5;
    private final int DATE_ONE = 3;
    private final int DATE_TWO = 7;
    private final int DATE_THREE = 12;
    private final int DATE_LOST = 13;

    @GetMapping("/downloadWords")
    public String downloadWords(Model model) {
        model.addAttribute("title", "IVAN and FATHER");
        downloadVocabulary.downloadWorlds();
        return "mine-page";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("image", loaderPhoto.getImage());
        model.addAttribute("title", "IVAN and FATHER");
        return "mine-page";
    }

    @Transactional
    @GetMapping("/user-page")
    public String userSecondLoad(Authentication authentication, Model model) {
        String loginUser = getLoginUser(authentication);
        if (loginUser != null) {
            return "redirect:/user-study-word";
        }
        model.addAttribute("title", "Mine Page");
        return "redirect:/";
    }

    @Transactional
    @GetMapping("/user-study-word")
    public String userStudyWord(Authentication authentication, Model model) {
        List<Word> wordList = getStudyWords(authentication);
        Long countLearnedWords = countLearnedWords(authentication);
        model.addAttribute("wordList", wordList);
        model.addAttribute("countLearnedWords", countLearnedWords);
        return "user-study-word";
    }

    private List<Word> getStudyWords(Authentication authentication) {
        List<Word> wordRepositoryAll = wordRepository.findAll();
        List<Integer> idListWords = getIdWordsForToday(authentication);
        return wordRepositoryAll.stream()
                .filter(all -> !idListWords.contains(all.getId()))
                .limit(SIZE_LIST_WORDS)
                .collect(Collectors.toList());
    }

    private List<Integer> getIdWordsForToday(Authentication authentication) {
        return studyTableWordsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .map(StudyTableWords::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }

    private Long countLearnedWords(Authentication authentication) {
        return studyTableWordsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(el -> el.getLearned().equals(true))
                .map(StudyTableWords::getLearned)
                .count();
    }

    @GetMapping("/user-study-word/{id}/learned")
    public String userSaveWordToStudyTable(@PathVariable(value = "id") Integer id, Authentication authentication) {
        StudyTableWords studyTableWordsNew = new StudyTableWords();
        studyTableWordsNew.setIdWord(Math.toIntExact(id));
        studyTableWordsNew.setLoginUser(getLoginUser(authentication));
        studyTableWordsNew.setDateOne(DATE_NOW.plusDays(DATE_ONE));
        studyTableWordsNew.setDateTwo(DATE_NOW.plusDays(DATE_TWO));
        studyTableWordsNew.setDateThree(DATE_NOW.plusDays(DATE_THREE));
        studyTableWordsNew.setLearned(false);
        studyTableWordsNew.setCount(0);
        studyTableWordsNew.setDateCount(DATE_NOW);
        System.out.println(studyTableWordsNew);
        studyTableWordsRepository.save(studyTableWordsNew);
        return "redirect:/user-study-word";
    }

    @Transactional
    @GetMapping("/user-study-word/repeat")
    public String userRepeatWord(Authentication authentication, Model model) {
        List<Word> wordList = getRepeatWords(authentication);
        Long countLearnedWords = countLearnedWords(authentication);
        model.addAttribute("wordList", wordList);
        model.addAttribute("countLearnedWords", countLearnedWords);
        return "user-repeat-word";
    }

    private List<Word> getRepeatWords(Authentication authentication) {
        List<Integer> filteredListIdOfWords = getFilteredListIdOfWords(authentication);
        if (filteredListIdOfWords.size() == 0) {
            List<Integer> filteredListIdOfWordsIfLostTime = getFilteredListIdOfWordsIfLostTime(authentication);
            return findWord(filteredListIdOfWordsIfLostTime);
        } else {
            return findWord(filteredListIdOfWords);
        }
    }

    private List<Word> findWord(List<Integer> filteredListIdOfWords) {
        List<Word> convertToWord = new ArrayList<>();
        for (Integer filteredListIdOfWord : filteredListIdOfWords) {
            Optional<Word> byId = wordRepository.findById(filteredListIdOfWord);
            byId.ifPresent(convertToWord::add);
        }
        return convertToWord;
    }

    @GetMapping("/user-study-word/{id}/repeat")
    public String userSaveRepeat(@PathVariable(value = "id") Integer id) {
        Optional<StudyTableWords> byId = studyTableWordsRepository.findByIdWord(id);
        if (byId.isPresent()) {
            StudyTableWords studyTableWords = byId.get();
            if (studyTableWords.getCount() == 3) {
                studyTableWords.setCount(studyTableWords.getCount() + 1);
                studyTableWords.setLearned(true);
                studyTableWordsRepository.save(studyTableWords);
                System.out.println(studyTableWords);
            }
            studyTableWords.setDateCount(DATE_NOW);
            studyTableWords.setCount(studyTableWords.getCount() + 1);
            System.out.println(studyTableWords);
            studyTableWordsRepository.save(studyTableWords);
        }
        return "redirect:/user-study-word/repeat";
    }


    private List<Integer> getFilteredListIdOfWords(Authentication authentication) {
        return studyTableWordsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(d1 -> d1.getDateOne().equals(DATE_NOW)
                        || d1.getDateTwo().equals(DATE_NOW)
                        || d1.getDateThree().equals(DATE_NOW))
                .filter(learned -> learned.getLearned().equals(false))
                .filter(countDate -> !countDate.getDateCount().equals(DATE_NOW))
                .filter(count -> count.getCount() <= 3)
                .map(StudyTableWords::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }

    private List<Integer> getFilteredListIdOfWordsIfLostTime(Authentication authentication) {
        return studyTableWordsRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(learned -> learned.getLearned().equals(false))
                .filter(count -> count.getCount() <= 3)
                .filter(day -> DATE_NOW.isAfter(day.getDateCount().plusDays(DATE_LOST)))
                .map(StudyTableWords::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }


    private String getLoginUser(Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> oneByLogin = userRepository.findOneByLogin(details.getUsername());
        return oneByLogin.map(User::getLogin).orElse(null);
    }
}
