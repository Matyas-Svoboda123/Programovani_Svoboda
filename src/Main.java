import accounts.*;
import person.AccountOwner;
import person.AccountOwnerFactory;
import transfer.AccountTransferService;

public class Main {

    public static void main(String[] args) {

        AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();

        AccountOwner accountOwner1 = accountOwnerFactory.createAccountOwner("Matyáš", "Svoboda");
        AccountOwner accountOwner2 = accountOwnerFactory.createAccountOwner("Jan", "Novak");

        BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory();
        CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory();

        BankAccount businessAccount = businessAccountFactory.createBusinessAccountWithBalance(accountOwner1, 450000);
        BankAccount currentAccount2 = currentAccountFactory.createCurrentAccountWithBalance(accountOwner2, 100000);

        System.out.println("BusinessAccount balance: " + businessAccount.getBalance());
        System.out.println("CurrentAccount2 balance: " + currentAccount2.getBalance());

        AccountTransferService accountTransferService = new AccountTransferService();
        accountTransferService.transfer(businessAccount, currentAccount2, 25000);

        System.out.println("BusinessAccount balance: " + businessAccount.getBalance());
        System.out.println("CurrentAccount2 balance: " + currentAccount2.getBalance());
    }
}