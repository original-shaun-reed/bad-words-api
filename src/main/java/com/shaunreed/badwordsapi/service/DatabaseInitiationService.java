package com.shaunreed.badwordsapi.service;

import com.google.gson.Gson;
import com.shaunreed.badwordsapi.dto.BadWordsList;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class DatabaseInitiationService {

    public BadWordsList getListOfBadWords() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("profanity_list.txt");
        String data = readFromInputStream(inputStream);

        Gson gson = new Gson();
        BadWordsList list = gson.fromJson(data, BadWordsList.class);

        return list;
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("{").append("\n");
        resultStringBuilder.append("words: ").append("\n");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        resultStringBuilder.append("}");
        return resultStringBuilder.toString();
    }
}
