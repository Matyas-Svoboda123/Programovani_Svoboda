package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;
import service.FeeService;

public class WithdrawTransferService {

    private final FeeService feeService = new FeeService();

    public void withdraw(BankAccount account, double amount) {
        double serviceFee = feeService.getWithdrawFee(account, amount);
        double newBalance = account.getBalance() - amount - serviceFee;

        if (newBalance < getWithdrawLimit(account)) {
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }

        account.setBalance(newBalance);
    }

    private int getWithdrawLimit(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000;
        }
        return 0;
    }
}