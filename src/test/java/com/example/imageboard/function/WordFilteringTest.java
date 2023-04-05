package com.example.imageboard.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordFilteringTest {

    @Test
    public void banWordTest() {
        WordFiltering wordFiltering = WordFiltering.Instance();

        System.out.println(wordFiltering.getBanWords());

        String text = "Я гуляю по Москве сука камон го";

        assertTrue(WordFiltering.containsBanWord(text));
    }
}