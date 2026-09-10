import account.BankAccount;
import account.CurrentAccount;
import account.StudentAccount;
import person.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        AccountOwner accountOwner = new AccountOwner("Matyáš", "Svoboda" );
        accountOwner.setLastName("Svoboda");

        BankAccount bankAccount = new CurrentAccount(accountOwner, "123", 125);
        BankAccount studentAccount = new StudentAccount(accountOwner, "123", 125, "Delta");

        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(bankAccount);
        bankAccounts.add(studentAccount);

        for(BankAccount account: bankAccounts){

            if(account instanceof StudentAccount){
                StudentAccount stdAccount = (StudentAccount) account;
                System.out.println("school: " + stdAccount.getSchoolName());
            }
        }

        bankAccount.add(400);
        bankAccount.add(100);
        bankAccount.add(300);

        printBalance(bankAccount);

        bankAccount.sub(200);
        bankAccount.sub(50);
        bankAccount.sub(20);

        printBalance(bankAccount);


    }
    private static void printBalance(BankAccount bankAccount){
        System.out.println("balance: " + bankAccount.getBalance());
    }
}