package com.example.englishstarter.service;

import com.example.englishstarter.model.Verbs;
import com.example.englishstarter.repository.VerbsRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DownloadVerbs {

    private VerbsRepository verbsRepository;

    public DownloadVerbs(VerbsRepository verbsRepository) {
        this.verbsRepository = verbsRepository;
    }

    public void saveVerbsToData() {
        List<String> listE = getListE();
        List<String> listTT = getListTT();

        for (int k = 0; k < listE.size(); k += 3) {
            Verbs verbs = new Verbs();
            verbs.setInfinitive(listE.get(k));
            verbs.setPastSimple(listE.get(k + 1));
            verbs.setPastParticiple(listE.get(k + 2));
            verbsRepository.save(verbs);
        }

        int count = 1;
        for (int j = 0; j < listTT.size(); j += 4) {
            Optional<Verbs> optionalVerbs = verbsRepository.findById(count);
            if (optionalVerbs.isPresent()) {
                Verbs verbs = optionalVerbs.get();
                verbs.setInfinitiveTranscription(listTT.get(j));
                verbs.setPastSimpleTranscription(listTT.get(j + 1));
                verbs.setPastParticipleTranscription(listTT.get(j + 2));
                verbs.setTranslate(listTT.get(j + 3));
                verbsRepository.save(verbs);
            }
            count++;
        }
    }

    private List<String> getListE() {
        List<String> listURL = getListURL();
        List<String> listE = new ArrayList<>();
        for (String s : listURL) {
            Document doc = getDocument(s);
            Elements links = doc.body().select(".js-speech").select("p");
            List<String> arrayList = links.stream().map(Element::text).collect(Collectors.toList());
            listE.addAll(arrayList);
        }
        return listE;
    }

    private List<String> getListTT() {
        List<String> listURL = getListURL();
        List<String> listTT = new ArrayList<>();
        for (String s : listURL) {
            Document doc = getDocument(s);
            Elements linksT = doc.body().select(".tc-ru");
            List<String> arrayListT = linksT.stream().map(Element::text).collect(Collectors.toList());
            listTT.addAll(arrayListT);
        }
        return listTT;
    }

    private List<String> getListURL() {
        List<String> listUrl = new ArrayList<>();
        listUrl.add("https://grammarway.com/ua/irregular-verbs");
        return listUrl;
    }

    private Document getDocument(String s) {
        Document doc = null;
        try {
            doc = Jsoup.connect(s).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
