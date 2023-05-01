package com.example.imageboard.service;

import com.example.imageboard.function.NicknameGenerator;
import org.springframework.stereotype.Service;

@Service
public class NicknameGeneratorService {

    public String generateNickname() {
        NicknameGenerator nicknameGenerator = NicknameGenerator.Instance();
        return nicknameGenerator.generateNickname();
    }
}
