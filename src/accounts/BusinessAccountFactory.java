package accounts;

import person.AccountOwner;
import service.AccountNumberGenerator;

import java.util.UUID;

public class BusinessAccountFactory {
    public BusinessAccount createBusinessAccount(AccountOwner accountOwner){
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();


        return new BusinessAccount(accountOwner, accountNumber, uuid);
    }

    public BusinessAccount createBusinessAccountWithBalance(AccountOwner accountOwner, double balance){
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();



        return new BusinessAccount(accountOwner, accountNumber, uuid, balance);
    }
}
