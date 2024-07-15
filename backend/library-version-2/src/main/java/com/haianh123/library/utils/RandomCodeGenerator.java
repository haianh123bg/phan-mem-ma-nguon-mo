package com.haianh123.library.utils;

import java.util.Random;

public class RandomCodeGenerator {
    public static int generateVerifyCode() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        return randomNumber;
    }
}
