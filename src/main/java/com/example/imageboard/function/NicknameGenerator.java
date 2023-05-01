package com.example.imageboard.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Supposed to be used when user creates new Thread or leaves a comment to existing one.
 * It iterates through the given .csv file, adds every word to List of Strings.
 */
public class NicknameGenerator {
    private static NicknameGenerator instance = null;
    private final String FILENAME = "words.csv";

    private static List<String> words;

    protected NicknameGenerator() {
        this.words = getWordsFromFile();
    }

    public List<String> getWords() {
        return words;
    }

    /**
     * Проверяет, создана ли instance
     * @return новую instance, или уже созданную ранее
     */
    public static NicknameGenerator Instance() {
        if (instance == null) {
            instance = new NicknameGenerator();
        }
        return instance;
    }

    /**
     * @return List of words
     */
    private List<String> getWordsFromFile() {
        List<String> listOfWords = new ArrayList<>();
        Path pathToFile = Paths.get(FILENAME);

        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                listOfWords.add(attributes[1]);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfWords;
    }

    public static String generateNickname() {
        String nickname;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        int numberOfWords = random.nextInt(6 - 1) + 1;

        for (int i = 0; i < numberOfWords; i++) {
            int index = random.nextInt(words.size());
            String randomWord = words.get(index);
            String randomWordUpperLetter = randomWord.substring(0, 1).toUpperCase() + randomWord.substring(1);
            stringBuilder.append(randomWordUpperLetter);
        }
        nickname = stringBuilder.toString();

        return nickname;
    }
}
