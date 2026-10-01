package service;

import java.util.Random;

public class AccountNumberGenerator {
    private static final Random random = new Random();

    public static String generateAccountNumber() {
        // Vygeneruje náhodné 9místné číslo
        long number = 100_000_000L + (long)(random.nextDouble() * 900_000_000L);
        return String.valueOf(number);
    }
}