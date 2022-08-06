package com.example.englishstarter.controller;

import com.example.englishstarter.model.StudyTable;
import com.example.englishstarter.model.User;
import com.example.englishstarter.model.Word;
import com.example.englishstarter.repository.StudyTableRepository;
import com.example.englishstarter.repository.UserRepository;
import com.example.englishstarter.repository.WordRepository;
import com.example.englishstarter.security.UserDetailsImpl;
import com.example.englishstarter.service.DownloadWords;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class _0_StudyWordControllers {
    private final DownloadWords downloadWords;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final StudyTableRepository studyTableRepository;

    private final int SIZE_LIST_WORDS = 10;
    private final int DATE_ONE = 3;
    private final int DATE_TWO = 7;
    private final int DATE_THREE = 12;

    @GetMapping("/downloadWords")
    public String downloadWords(Model model) {
        model.addAttribute("title", "IVAN and FATHER");
        downloadWords.downloadWorlds();
        return "mine-page";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "IVAN and FATHER");
        return "mine-page";
    }

    @Transactional
    @GetMapping("/user-page")
    public String userSecondLoad(Authentication authentication, Model model, MultipartFile file) {
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
        return studyTableRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .map(StudyTable::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }

    private Long countLearnedWords(Authentication authentication) {
        return studyTableRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(el -> el.getLearned().equals(true))
                .map(StudyTable::getLearned)
                .count();
    }

    @GetMapping("/user-study-word/{id}/learned")
    public String userSaveWordToStudyTable(@PathVariable(value = "id") Integer id, Authentication authentication) {
        LocalDate date = LocalDate.now();
        StudyTable studyTableNew = new StudyTable();
        studyTableNew.setIdWord(Math.toIntExact(id));
        studyTableNew.setLoginUser(getLoginUser(authentication));
        studyTableNew.setDateOne(date.plusDays(DATE_ONE));
        studyTableNew.setDateTwo(date.plusDays(DATE_TWO));
        studyTableNew.setDateThree(date.plusDays(DATE_THREE));
        studyTableNew.setLearned(false);
        studyTableNew.setCount(0);
        studyTableNew.setDateCount(date);
        System.out.println(studyTableNew);
        studyTableRepository.save(studyTableNew);
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
        List<Word> convertToWord = new ArrayList<>();
        for (Integer filteredListIdOfWord : filteredListIdOfWords) {
            Optional<Word> byId = wordRepository.findById(filteredListIdOfWord);
            byId.ifPresent(convertToWord::add);
        }
        return convertToWord;
    }

    @GetMapping("/user-study-word/{id}/repeat")
    public String userSaveRepeat(@PathVariable(value = "id") Integer id) {
        Optional<StudyTable> byId = studyTableRepository.findByIdWord(id);
        LocalDate date = LocalDate.now();

        if (byId.isPresent()) {
            StudyTable studyTable = byId.get();
            if (studyTable.getCount() == 3) {
                studyTable.setCount(studyTable.getCount() + 1);
                studyTable.setLearned(true);
                studyTableRepository.save(studyTable);
                System.out.println(studyTable);
            }
            studyTable.setDateCount(date);
            studyTable.setCount(studyTable.getCount() + 1);
            System.out.println(studyTable);
            studyTableRepository.save(studyTable);
        }
        return "redirect:/user-study-word/repeat";
    }


    private List<Integer> getFilteredListIdOfWords(Authentication authentication) {
        LocalDate date = LocalDate.now();
        return studyTableRepository.findAll().stream()
                .filter(userId -> userId.getLoginUser().equals(getLoginUser(authentication)))
                .filter(d1 -> d1.getDateOne().equals(date)
                        || d1.getDateTwo().equals(date)
                        || d1.getDateThree().equals(date))
                .filter(learned -> learned.getLearned().equals(false))
                .filter(countDate -> !countDate.getDateCount().equals(date))
                .map(StudyTable::getIdWord)
                .sorted()
                .collect(Collectors.toList());
    }

    private String getLoginUser(Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> oneByLogin = userRepository.findOneByLogin(details.getUsername());
        return oneByLogin.map(User::getLogin).orElse(null);
    }
}
