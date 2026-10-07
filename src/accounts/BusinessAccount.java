package accounts;

import person.AccountOwner;

// Poplatky řeší FeeService, účet je proto jen běžný BankAccount.
public class BusinessAccount extends BankAccount {
    public BusinessAccount(AccountOwner accountOwner, String accountNumber, String uuid) {
        super(accountOwner, accountNumber, uuid);
    }

    public BusinessAccount(AccountOwner accountOwner, String accountNumber, String uuid, double balance) {
        super(accountOwner, accountNumber, uuid, balance);
    }
}