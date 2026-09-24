package account;

import notifier.ConsoleNotifierService;
import notifier.NotifierService;
import person.AccountOwner;

import java.util.UUID;

public class BankAccount {
    private String uuid;
    private AccountOwner accountOwner;
    private String accountNumber;
    protected double balance;
    private NotifierService notifierService = new ConsoleNotifierService();


    public BankAccount(AccountOwner accountOwner, String accountNumber) {
        this.uuid = UUID.randomUUID().toString();
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(AccountOwner accountOwner, String accountNumber, double balance){
        this(accountOwner, accountNumber);
        this.balance = balance;
    }

    public BankAccount() {
    }
    public String getUuid() {
        return uuid;
    }

    public AccountOwner getAccountOwner() {
        return accountOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }
    public void calculateInterest(){
        double interest = balance * getInterest();
        this.add(interest);
    }
    public float getInterest(){
        return 0;
    }

    public void add(double amount){
        if(amount < 0){
            throw new IllegalArgumentException("Ammount cannot be negative");
        }

        this.balance += amount;
    }
    public void sub(double amount){
        double newBalance = balance - amount;
        if(newBalance < 0){
            throw new IllegalArgumentException("Cannot substract negative amount");
        }
        this.balance -= amount;

    }
}
