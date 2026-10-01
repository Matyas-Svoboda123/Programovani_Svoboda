package accounts;

import person.AccountOwner;
import service.AccountNumberGenerator;

import java.util.UUID;

public class SavingsAccountFactory {
    public SavingsAccount createSavingsAccount(AccountOwner accountOwner){
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();


        return new SavingsAccount(accountOwner, accountNumber, uuid);
    }

    public SavingsAccount createSavingsWithBalance(AccountOwner accountOwner, double balance){
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();


        return new SavingsAccount(accountOwner, accountNumber, uuid, balance);
    }
}
