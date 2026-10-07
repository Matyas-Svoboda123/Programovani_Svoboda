package service;

import java.time.LocalDateTime;

/**
 * Nemenny (immutable) zaznam o jedne transakci.
 *
 * Co ukladame a proc:
 *  - id                  jednoznacna identifikace zaznamu
 *  - type                druh operace (prevod / vklad / vyber)
 *  - sourceAccountNumber odkud penize odesly (u vkladu null)
 *  - targetAccountNumber kam penize prisly (u vyberu null)
 *  - amount              puvodni castka operace
 *  - fee                 poplatek, ktery si banka vzala (prevod / vyber z firemniho uctu)
 *  - bonus               bonus, ktery klient dostal navic (vklad na studentsky ucet)
 *  - timestamp           kdy k operaci doslo
 */
public class Transaction {

    private final String id;
    private final TransactionType type;
    private final String sourceAccountNumber;
    private final String targetAccountNumber;
    private final double amount;
    private final double fee;
    private final double bonus;
    private final LocalDateTime timestamp;

    public Transaction(String id, TransactionType type, String sourceAccountNumber, String targetAccountNumber,
                       double amount, double fee, double bonus, LocalDateTime timestamp) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Transaction id must not be empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Transaction type must not be null.");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("Transaction timestamp must not be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be bigger than zero.");
        }
        if (fee < 0 || bonus < 0) {
            throw new IllegalArgumentException("Fee and bonus must not be negative.");
        }
        this.id = id;
        this.type = type;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
        this.fee = fee;
        this.bonus = bonus;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public double getBonus() {
        return bonus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", source=" + (sourceAccountNumber == null ? "-" : sourceAccountNumber) +
                ", target=" + (targetAccountNumber == null ? "-" : targetAccountNumber) +
                ", amount=" + amount +
                ", fee=" + fee +
                ", bonus=" + bonus +
                ", timestamp=" + timestamp +
                '}';
    }
}