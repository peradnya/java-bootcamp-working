package com.peradnya.helper;

import java.util.Random;

public class Generator {
    public static String randomNumber(int length) {
        Random random = new Random();

        return random.ints(0, 10)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
