package com.example.englishstarter.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class LoaderPhoto {
    public String getImage()  {
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File("src\\main\\resources\\static\\images\\hardest-part-learning-english.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
