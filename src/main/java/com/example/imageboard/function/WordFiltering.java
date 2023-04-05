package com.example.imageboard.function;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordFiltering {
    private static WordFiltering instance = null;
    private final String FILENAME = "ban-words.csv";

    public List<String> banWords;

    public List<String> getBanWords() {
        return banWords;
    }

    protected WordFiltering() {
        banWords = getWordsFromFile();
    }

    public static WordFiltering Instance() {
        if (instance == null) {
            instance = new WordFiltering();
        }
        return instance;
    }

    private List<String> getWordsFromFile() {
        List<String> listOfBanWords = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(FILENAME);
            DataInputStream in = new DataInputStream(fileInputStream);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"windows-1251"))) {
                String line = bufferedReader.readLine();

                while (line != null) {
                    String[] attributes = line.split(",");
                    listOfBanWords.add(attributes[0]);

                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listOfBanWords;
    }

    public static boolean containsBanWord(String text) {
        WordFiltering wordFiltering = WordFiltering.Instance();
        String lowerCaseText = text.toLowerCase();
        for (String banword : wordFiltering.getBanWords()) {
            if (lowerCaseText.contains(banword)) return true;
        }

        return false;
    }
}
