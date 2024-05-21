package br.com.cdb.digitalbank.service.util;

import java.util.Random;

public class GenerateNumber {

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        cardNumber.append((random.nextInt(9) + 1));

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }

    public static long generateAccountNumber() {
        Random random = new Random();
        long min = 10000000L;
        long max = 99999999L;
        return Math.abs(random.nextLong() % (max - min + 1)) + min;
    }

    public static int generateAgencyNumber() {
        int min = 1000;
        int max = 9999;
        return new Random().nextInt(max - min + 1) + min;
    }
}
