package com.example.imageboard.function;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NicknameGeneratorTest {

    @Test
    void instance() {
        NicknameGenerator instance1 = NicknameGenerator.Instance();
        NicknameGenerator instance2 = NicknameGenerator.Instance();

        assertSame(instance1, instance2);
    }

    @Test
    void generateNickname() {
        NicknameGenerator nicknameGenerator = NicknameGenerator.Instance();
        assertEquals(5000, nicknameGenerator.getWords().size());

        String generatedNickname = nicknameGenerator.generateNickname();
        System.out.println(generatedNickname);
        assertFalse(generatedNickname.isEmpty());
    }
}