package account;

import person.AccountOwner;

public class BusinessAccount extends BankAccount {
    public BusinessAccount(AccountOwner accountOwner, String accountNumber) {
        super(accountOwner, accountNumber);
    }

    public BusinessAccount(AccountOwner accountOwner, String accountNumber, double balance) {
        super(accountOwner, accountNumber, balance);
    }

    @Override
    public void sub(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        double totalToDeduct = amount * 1.01; // částka + 1% poplatek
        super.sub(totalToDeduct);
    }
}
