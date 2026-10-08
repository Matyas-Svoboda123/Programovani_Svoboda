package service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Generuje česká čísla účtů ve formátu [předčíslí-]číslo/kód banky,
 * např. 19-2000145399/0800 nebo 1234567890/0800.
 *
 * Předčíslí (až 6 číslic) i základní číslo (až 10 číslic) splňují kontrolu modulo 11,
 * kterou používají skutečné banky:
 *   součet (číslice * váha) musí být dělitelný 11.
 * Váhy se přiřazují zprava, poslední číslice má vždy váhu 1:
 *   předčíslí: 10, 5, 8, 4, 2, 1
 *   číslo:     6, 3, 7, 9, 10, 5, 8, 4, 2, 1
 */
public class AccountNumberGenerator {

    // Kód naší banky (0800 = Česká spořitelna). Případně změň na svůj.
    private static final String BANK_CODE = "0800";

    private static final int[] PREFIX_WEIGHTS = {10, 5, 8, 4, 2, 1};
    private static final int[] NUMBER_WEIGHTS = {6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

    private static final Random random = new Random();
    private static final Set<String> usedNumbers = new HashSet<>();

    // Vygeneruje unikátní číslo účtu, např. "19-2000145399/0800"
    public static synchronized String generateAccountNumber() {
        String accountNumber;
        do {
            String prefix = random.nextInt(100) < 40 ? generatePrefix() : null;
            String number = generateNumber();
            accountNumber = (prefix == null ? "" : prefix + "-") + number + "/" + BANK_CODE;
        } while (!usedNumbers.add(accountNumber));
        return accountNumber;
    }

    // Předčíslí: 2 až 6 číslic, platné podle modulo 11
    private static String generatePrefix() {
        int length = 2 + random.nextInt(5);
        return generateValidDigits(length, PREFIX_WEIGHTS);
    }

    // Základní číslo: 10 číslic, první nenulová, platné podle modulo 11
    private static String generateNumber() {
        return generateValidDigits(10, NUMBER_WEIGHTS);
    }

    private static String generateValidDigits(int length, int[] weights) {
        while (true) {
            int[] digits = new int[length];
            digits[0] = 1 + random.nextInt(9);
            for (int i = 1; i < length; i++) {
                digits[i] = random.nextInt(10);
            }
            if (checksum(digits, weights) % 11 == 0) {
                StringBuilder sb = new StringBuilder();
                for (int d : digits) {
                    sb.append(d);
                }
                return sb.toString();
            }
            // zkusíme opravit poslední číslici, aby součet vyšel
            int base = checksum(digits, weights) - digits[length - 1] * weights[weights.length - 1];
            for (int last = 0; last <= 9; last++) {
                if ((base + last * weights[weights.length - 1]) % 11 == 0) {
                    digits[length - 1] = last;
                    StringBuilder sb = new StringBuilder();
                    for (int d : digits) {
                        sb.append(d);
                    }
                    return sb.toString();
                }
            }
        }
    }

    // Číslice se zarovnají zprava k vahám
    private static int checksum(int[] digits, int[] weights) {
        int sum = 0;
        int offset = weights.length - digits.length;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i] * weights[offset + i];
        }
        return sum;
    }

    // Kontrola formátu a modulo 11 (hodí se do testů nebo validace vstupu)
    public static boolean isValid(String accountNumber) {
        if (accountNumber == null || !accountNumber.matches("(\\d{2,6}-)?\\d{2,10}/\\d{4}")) {
            return false;
        }
        String[] bankSplit = accountNumber.split("/");
        String[] numberSplit = bankSplit[0].split("-");
        String number = numberSplit[numberSplit.length - 1];
        String prefix = numberSplit.length == 2 ? numberSplit[0] : null;
        return isMod11(number, NUMBER_WEIGHTS) && (prefix == null || isMod11(prefix, PREFIX_WEIGHTS));
    }

    private static boolean isMod11(String digits, int[] weights) {
        int[] d = new int[digits.length()];
        for (int i = 0; i < d.length; i++) {
            d[i] = digits.charAt(i) - '0';
        }
        return checksum(d, weights) % 11 == 0;
    }
}