package com.example.englishstarter;

import com.example.englishstarter.model.Word;
import com.example.englishstarter.repository.WordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@AllArgsConstructor
public class EnglishStarterApplication {
    private static WordRepository wordRepository;

    @Autowired
    public EnglishStarterApplication(WordRepository wordRepository) {
        EnglishStarterApplication.wordRepository = wordRepository;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EnglishStarterApplication.class, args);
        getListWord(getListStringWords()).forEach(System.out::println);

    }

    public static List<Word> getListWord(List<String> collect) {
        List<Word> wordList = new ArrayList<>();
        int count = 1;

        for (int i = 0; i < 4404 - 1; i += 4) {
            Word word = new Word();
            word.setId(count++);

            word.setEnglish(collect.get(i));
            word.setTranslate(collect.get(i + 1));
            word.setTranscription(collect.get(i + 2));
            wordList.add(word);
            wordRepository.save(word);
            System.out.println(word);
        }
        for (int i = 4403; i < collect.size() - 1; i += 4) {
            Word word = new Word();
            word.setId(count++);
            word.setEnglish(collect.get(i));
            word.setTranslate(collect.get(i + 1));
            word.setTranscription(collect.get(i + 2));
            wordList.add(word);
            wordRepository.save(word);
            System.out.println(word);
        }
        return wordList;
    }

    public static List<String> getListStringWords() throws IOException {
        List<String> listURL = getListURL();

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < listURL.size(); i++) {
            Document doc = Jsoup.connect(listURL.get(i)).get();
            Elements links = doc.body().select("#customers").select("tr").select("td");
            List<String> arrayList = links.stream().map(Element::text).collect(Collectors.toList());

            for (int j = 5; j < arrayList.size(); j++) {
                stringList.add(arrayList.get(j));
            }
            stringList.remove(stringList.size() - 1);
        }
        return stringList;
    }

    private static List<String> getListURL() {
        List<String> listUrl = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2015/02/2000-" + i + ".html");
        }
        for (int i = 3; i < 16; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2015/02/3000-" + i + ".html");
        }
        listUrl.add("http://en-to-ua.blogspot.com/2016/03/3000-" + "16_1" + ".html");
        for (int i = 17; i < 28; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2016/03/3000-" + i + ".html");
        }
        listUrl.add("http://en-to-ua.blogspot.com/2016/03/3000-" + "28_1" + ".html");
        for (int i = 29; i < 31; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2016/03/3000-" + i + ".html");
        }
        return listUrl;
    }
}
