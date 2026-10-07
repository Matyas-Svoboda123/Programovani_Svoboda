package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;
import service.FeeService;

public class AccountTransferService {

    private final FeeService feeService = new FeeService();

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Source and target accounts must not be null.");
        }

        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("Cannot transfer funds to the same account.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be bigger than zero.");
        }

        double totalDeduction = feeService.getTotalDeductionAmount(fromAccount, amount);

        // Kontrola povoleného limitu
        double minAllowedBalance = getMinAllowedBalance(fromAccount);
        if (fromAccount.getBalance() - totalDeduction < minAllowedBalance) {
            throw new IllegalArgumentException("Insufficient funds for transfer. Account limit exceeded.");
        }

        double fee = feeService.getTransferFee(fromAccount, amount);
        double amountToDeposit = amount - fee;

        fromAccount.sub(amount);
        toAccount.add(amountToDeposit);
    }

    private double getMinAllowedBalance(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000.0;
        }
        return 0.0;
    }
}