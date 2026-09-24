package account;

import person.AccountOwner;

public class SavingsAccount extends BankAccount{
    private static final float BONUS_RATE = 0.05f;

    public SavingsAccount(AccountOwner accountOwner, String accountNumber) {
        super(accountOwner, accountNumber);
    }

    public SavingsAccount(AccountOwner accountOwner, String accountNumber, double balance) {
        super(accountOwner, accountNumber, balance);
    }

    @Override
    public void add(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        double newAmount = amount * 1.005;
        super.add(newAmount);
    }

}
