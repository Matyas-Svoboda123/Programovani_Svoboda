package accounts;

import person.AccountOwner;

public class CurrentAccount  extends BankAccount{
    public CurrentAccount(AccountOwner accountOwner, String accountNumber, String uuid) {

        super(accountOwner, accountNumber, uuid);
    }

    public CurrentAccount(AccountOwner accountOwner, String accountNumber, String uuid, double balance) {
        super(accountOwner, accountNumber, uuid, balance);
    }
}
