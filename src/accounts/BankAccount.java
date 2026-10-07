package accounts;

import notifier.ConsoleNotifierService;
import notifier.NotifierService;
import person.AccountOwner;

public class BankAccount implements InterestPoints {
    private String uuid;
    private AccountOwner accountOwner;
    private String accountNumber;
    protected double balance;
    private NotifierService notifierService = new ConsoleNotifierService();

    public BankAccount(AccountOwner accountOwner, String accountNumber, String uuid) {
        this.uuid = uuid;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(AccountOwner accountOwner, String accountNumber, String uuid, double balance) {
        this(accountOwner, accountNumber, uuid);
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    @Override
    public void calculateInterest() {
        double interest = balance * getInterest();
        this.add(interest);
    }

    public float getInterest() {
        return 0;
    }

    public void add(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.balance += amount;
    }

    public void sub(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}