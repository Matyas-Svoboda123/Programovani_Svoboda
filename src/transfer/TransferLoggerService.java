package transfer;

import service.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransferLoggerService {

    private final List<Transaction> transactions = new ArrayList<>();

    public void logTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsForAccount(String accountNumber) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (accountNumber.equals(t.getSourceAccountNumber())
                    || accountNumber.equals(t.getTargetAccountNumber())) {
                result.add(t);
            }
        }
        return result;
    }

    public void printAllTransactions() {
        System.out.println("--- HISTORIE TRANSAKCÍ ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}