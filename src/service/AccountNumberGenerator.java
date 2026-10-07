package service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountNumberGenerator {
    private static final Random random = new Random();
    private static final Set<String> usedNumbers = new HashSet<>();

    // Vygeneruje unikátní náhodné 9místné číslo
    public static synchronized String generateAccountNumber() {
        String number;
        do {
            number = String.valueOf(100_000_000L + (long) (random.nextDouble() * 900_000_000L));
        } while (!usedNumbers.add(number));
        return number;
    }
}