package accounts;

import person.AccountOwner;
import service.AccountNumberGenerator;

import java.util.UUID;

public class StudentAccountFactory {
    public StudentAccount createStudentAccount(AccountOwner accountOwner, String schoolName) {
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();

        return new StudentAccount(accountOwner, accountNumber, schoolName, uuid);
    }

    public StudentAccount createStudentAccountWithBalance(String schoolName, AccountOwner accountOwner, double balance) {
        String uuid = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generateAccountNumber();

        return new StudentAccount(accountOwner, accountNumber, balance, schoolName, uuid);
    }
}
