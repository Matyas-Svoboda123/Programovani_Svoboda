package service;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;

public class FeeService {

    private static final double BUSINESS_TRANSFER_FEE_RATE = 0.003; // 0.3% poplatek z převodu
    private static final double BUSINESS_WITHDRAW_FEE_RATE = 0.01;  // 1% poplatek z výběru/sub
    private static final double STUDENT_DEPOSIT_BONUS_RATE = 0.005; // 0.5% bonus k vkladu

    // Poplatek při převodu z firemního účtu (odečítá se z vkládané částky příjemci)
    public double getTransferFee(BankAccount fromAccount, double amount) {
        if (fromAccount instanceof BusinessAccount) {
            return amount * BUSINESS_TRANSFER_FEE_RATE;
        }
        return 0.0;
    }

    // Vrací celkovou částku, která se odečte z účtu (včetně 1% poplatku u BusinessAccount)
    public double getTotalDeductionAmount(BankAccount account, double amount) {
        if (account instanceof BusinessAccount) {
            return amount + (amount * BUSINESS_WITHDRAW_FEE_RATE);
        }
        return amount;
    }

    // Bonus při vkladu na studentský účet
    public double getDepositBonus(BankAccount account, double amount) {
        if (account instanceof StudentAccount) {
            return amount * STUDENT_DEPOSIT_BONUS_RATE;
        }
        return 0.0;
    }
}