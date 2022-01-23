package com.example.englishstarter.service;

import com.example.englishstarter.model.Word;
import com.example.englishstarter.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WordService {
    private final WordRepository wordRepository;

    public List<String> getListStringWords(String url) throws IOException {
        List<String> listURL = getListURL();

//        List<String> arrayList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i <listURL.size() ; i++) {
            Document doc = Jsoup.connect(listURL.get(i)).get();
            Elements links = doc.body().select("#customers").select("tr").select("td");
            List<String> arrayList = links.stream().map(Element::text).collect(Collectors.toList());
//            for (Element element : links) {
//                arrayList.add(element.text());
//            }
            for (int j = 5; j < arrayList.size(); j++) {
                stringList.add(arrayList.get(j));
            }
            for (int k = 3; k < stringList.size(); k += 3) {
                stringList.remove(k);
            }
            stringList.remove(stringList.size() - 1);
        }
//        stringList.forEach(System.out::println);
        return stringList;
    }

    private List<String> getListURL() {
        List<String> listUrl = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2015/02/2000-" + i + ".html");
        }
        for (int i = 3; i < 15; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2015/02/3000-" + i + ".html");
        }
        for (int i = 16; i < 30; i++) {
            listUrl.add("http://en-to-ua.blogspot.com/2016/03/3000-" + i + ".html");
        }

//        http://en-to-ua.blogspot.com/2015/02/2000-1.html
//        http://en-to-ua.blogspot.com/2015/02/2000-2.html
//        http://en-to-ua.blogspot.com/2015/02/3000-3.html
//        http://en-to-ua.blogspot.com/2015/02/3000-4.html
//        http://en-to-ua.blogspot.com/2015/02/3000-5.html
//        http://en-to-ua.blogspot.com/2015/02/3000-12.html
//        http://en-to-ua.blogspot.com/2016/03/3000-16_1.html
        return listUrl;
    }
}
