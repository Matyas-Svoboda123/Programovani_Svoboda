package transfer;

import accounts.BankAccount;
import service.FeeService;
import service.Transaction;
import service.TransactionFactory;

public class DepositTransferService {

    private final FeeService feeService = new FeeService();
    private final TransactionFactory transactionFactory = new TransactionFactory();
    private final TransferLoggerService loggerService;

    public DepositTransferService(TransferLoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public void deposit(BankAccount bankAccount, double amount) {
        if (bankAccount == null) {
            throw new IllegalArgumentException("Account must not be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be bigger than zero.");
        }

        double depositBonus = feeService.getDepositBonus(bankAccount, amount);
        bankAccount.setBalance(bankAccount.getBalance() + amount + depositBonus);

        Transaction transaction = transactionFactory.createDepositTransaction(
                bankAccount.getAccountNumber(),
                amount,
                depositBonus
        );
        loggerService.logTransaction(transaction);
    }
}