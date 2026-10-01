package accounts;

import person.AccountOwner;
import service.AccountNumberGenerator;

import java.util.UUID;

public class CurrentAccountFactory {
    public CurrentAccount createCurrentAccount(AccountOwner accountOwner){
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();



        return new CurrentAccount(accountOwner, accountNumber, uuid);
    }

    public CurrentAccount createCurrentAccountWithBalance(AccountOwner accountOwner, double balance){
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();



        return new CurrentAccount(accountOwner, accountNumber, uuid, balance);
    }
}
