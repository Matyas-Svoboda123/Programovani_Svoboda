package service;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {

    public Transaction createTransferTransaction(String sourceAccountNumber, String targetAccountNumber,
                                                 double amount, double fee) {
        requireAccountNumber(sourceAccountNumber, "Source");
        requireAccountNumber(targetAccountNumber, "Target");
        return create(TransactionType.TRANSFER, sourceAccountNumber, targetAccountNumber, amount, fee, 0.0);
    }

    public Transaction createDepositTransaction(String targetAccountNumber, double amount, double bonus) {
        requireAccountNumber(targetAccountNumber, "Target");
        return create(TransactionType.DEPOSIT, null, targetAccountNumber, amount, 0.0, bonus);
    }

    public Transaction createDepositTransaction(String targetAccountNumber, double amount) {
        return createDepositTransaction(targetAccountNumber, amount, 0.0);
    }

    public Transaction createWithdrawTransaction(String sourceAccountNumber, double amount, double fee) {
        requireAccountNumber(sourceAccountNumber, "Source");
        return create(TransactionType.WITHDRAW, sourceAccountNumber, null, amount, fee, 0.0);
    }

    private Transaction create(TransactionType type, String source, String target,
                               double amount, double fee, double bonus) {
        return new Transaction(
                UUID.randomUUID().toString(),
                type,
                source,
                target,
                amount,
                fee,
                bonus,
                LocalDateTime.now()
        );
    }

    private void requireAccountNumber(String accountNumber, String role) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException(role + " account number must not be empty.");
        }
    }
}