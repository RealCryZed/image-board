package com.example.imageboard.function;

import java.util.Base64;

public class ImageProcessor {

    public static String getBase64Image(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
