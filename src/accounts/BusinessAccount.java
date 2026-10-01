package accounts;

import person.AccountOwner;

public class BusinessAccount extends BankAccount {
    public BusinessAccount(AccountOwner accountOwner, String accountNumber, String uuid) {
        super(accountOwner, accountNumber, uuid);
    }

    public BusinessAccount(AccountOwner accountOwner, String accountNumber, String uuid, double balance) {
        super(accountOwner, accountNumber, uuid, balance);
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
