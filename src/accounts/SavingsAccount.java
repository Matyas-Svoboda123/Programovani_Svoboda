package accounts;

import person.AccountOwner;

public class SavingsAccount extends BankAccount{
    private static final float BONUS_RATE = 0.05f;

    public SavingsAccount(AccountOwner accountOwner, String accountNumber, String uuid)
    {
        super(accountOwner, accountNumber, uuid);
    }

    public SavingsAccount(AccountOwner accountOwner, String accountNumber, String uuid, double balance) {
        super(accountOwner, accountNumber, uuid, balance);
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
