package transfer;

import accounts.BankAccount;
import service.FeeService;

public class DepositTransferService {

    private final FeeService feeService = new FeeService();

    public void deposit(BankAccount bankAccount, double amount) {
        double depositBonus = feeService.getDepositBonus(bankAccount, amount);
        double newBalance = bankAccount.getBalance() + amount + depositBonus;

        bankAccount.setBalance(newBalance);
    }
}