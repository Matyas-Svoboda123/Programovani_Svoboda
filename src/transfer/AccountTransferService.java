package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;

public class AccountTransferService {

    private static final double BUSINESS_TRANSFER_FEE = 0.003; // 0.3% fee

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

        double amountToDeposit = amount;

        if (fromAccount instanceof BusinessAccount) {
            double fee = amount * BUSINESS_TRANSFER_FEE;
            amountToDeposit = amount - fee;
        }

        fromAccount.sub(amount);
        toAccount.add(amountToDeposit);
    }
}