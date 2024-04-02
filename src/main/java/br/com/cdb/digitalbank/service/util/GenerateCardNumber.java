package br.com.cdb.digitalbank.service.util;

import java.util.Random;

public class GenerateCardNumber {

    public static String execute() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        cardNumber.append((random.nextInt(9) + 1));

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }
}
