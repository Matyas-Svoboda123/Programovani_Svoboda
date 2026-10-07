package service;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;

public class FeeService {

    private static final double BUSINESS_TRANSFER_FEE_RATE = 0.003;
    private static final double BUSINESS_WITHDRAW_FEE_RATE = 0.01;
    private static final double STUDENT_DEPOSIT_BONUS_RATE = 0.005;

    public double getTransferFee(BankAccount fromAccount, double amount) {
        if (fromAccount instanceof BusinessAccount) {
            return amount * BUSINESS_TRANSFER_FEE_RATE;
        }
        return 0.0;
    }

    public double getWithdrawFee(BankAccount account, double amount) {
        if (account instanceof BusinessAccount) {
            return amount * BUSINESS_WITHDRAW_FEE_RATE;
        }
        return 0.0;
    }

    public double getDepositBonus(BankAccount account, double amount) {
        if (account instanceof StudentAccount) {
            return amount * STUDENT_DEPOSIT_BONUS_RATE;
        }
        return 0.0;
    }
}