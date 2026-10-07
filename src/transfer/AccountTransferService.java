package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;
import service.FeeService;
import service.Transaction;
import service.TransactionFactory;

public class AccountTransferService {

    private final FeeService feeService = new FeeService();
    private final TransactionFactory transactionFactory = new TransactionFactory();
    private final TransferLoggerService loggerService;

    public AccountTransferService(TransferLoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Source and target accounts must not be null.");
        }

        if (fromAccount == toAccount) {
            throw new IllegalArgumentException("Cannot transfer funds to the same account.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be bigger than zero.");
        }

        if (fromAccount.getBalance() - amount < getMinAllowedBalance(fromAccount)) {
            throw new IllegalArgumentException("Insufficient funds for transfer. Account limit exceeded.");
        }

        double fee = feeService.getTransferFee(fromAccount, amount);
        double amountToDeposit = amount - fee;

        fromAccount.sub(amount);
        toAccount.add(amountToDeposit);

        Transaction transaction = transactionFactory.createTransferTransaction(
                fromAccount.getAccountNumber(),
                toAccount.getAccountNumber(),
                amount,
                fee
        );
        loggerService.logTransaction(transaction);
    }

    private double getMinAllowedBalance(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000.0;
        }
        return 0.0;
    }
}