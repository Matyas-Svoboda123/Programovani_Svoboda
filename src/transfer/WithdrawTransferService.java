package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;
import service.FeeService;
import service.Transaction;
import service.TransactionFactory;

public class WithdrawTransferService {

    private final FeeService feeService = new FeeService();
    private final TransactionFactory transactionFactory = new TransactionFactory();
    private final TransferLoggerService loggerService;

    public WithdrawTransferService(TransferLoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public void withdraw(BankAccount account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be bigger than zero.");
        }

        double serviceFee = feeService.getWithdrawFee(account, amount);
        double newBalance = account.getBalance() - amount - serviceFee;

        if (newBalance < getWithdrawLimit(account)) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal.");
        }

        account.setBalance(newBalance);

        Transaction transaction = transactionFactory.createWithdrawTransaction(
                account.getAccountNumber(),
                amount,
                serviceFee
        );
        loggerService.logTransaction(transaction);
    }

    private double getWithdrawLimit(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000;
        }
        return 0;
    }
}